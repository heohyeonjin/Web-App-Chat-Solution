package chatsolution.web.fcm;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Builder
@AllArgsConstructor
@Getter
// 알림 요청 메시지 만들기
// Push 알림을 보내기 위해 준수해야 하는 Request Body
public class FcmMessage {
    private boolean validate_only;
    private Message message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message { // Message에 포함될 데이터
//        private Notification notification; // 모든 mobile 공통 notify
        private Data data;
        private String token; // 특정 device에 알림
    }

//    @Builder
//    @AllArgsConstructor
//    @Getter
//    public static class Notification {
//        private String title;
//        private String body;
//        private String image;
//    }
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Data {
        private String title;
        private String body;
        private String roomId;
        private String msgNo;
    }

}
