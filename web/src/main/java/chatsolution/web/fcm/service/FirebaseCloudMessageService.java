package chatsolution.web.fcm.service;
import chatsolution.web.fcm.FcmMessage;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FirebaseCloudMessageService {
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/chatsolution-6e594/messages:send";
    private final ObjectMapper objectMapper;

    // 매개변수로 전달받은 targetToken에 해당하는 device로 FCM 푸시알림 전송 요청
    public void sendMessageTo(String targetToken, String title, String body, String roomId, String msgNo) throws IOException {
        String message = makeMessage(targetToken, title, body, roomId,msgNo);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE,"application/json; UTF-8")
                .build();

        Response response = client.newCall(request)
                        .execute();
        System.out.println(response.body().string());
    }

    private String makeMessage(String targetToken, String title, String body, String roomId, String msgNo) throws JacksonException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                .token(targetToken)
//                        .notification(FcmMessage.Notification.builder()
//                                .image(null)
//                                .build()
//                        )
                        .data(FcmMessage.Data.builder()
                                .title(title)
                                .body(body)
                                .roomId(roomId)
                                .msgNo(msgNo)
                                .build()

                        )
                        .build()
                )
                .validate_only(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/firebase_service_key.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired(); // accessToken 생성
        return googleCredentials.getAccessToken().getTokenValue(); // 토큰 값 최종적 얻어옴 (서버 토큰인듯..?)

    }
}
