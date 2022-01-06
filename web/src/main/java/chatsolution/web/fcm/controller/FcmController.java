package chatsolution.web.fcm.controller;


import chatsolution.web.client.model.Client;
import chatsolution.web.clientAPI.message.service.ClientMessageService;
import chatsolution.web.fcm.dto.MessageReceiveCheckDto;
import chatsolution.web.fcm.dto.TokenDto;
import chatsolution.web.message.model.Message;
import com.google.api.Http;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FcmController {

    private final ClientMessageService messageClientService;
    @PostMapping("/getToken")
    public String tokenUpdate(@RequestBody TokenDto token, HttpServletRequest request){
        Client client = messageClientService.getClient(request.getSession()); //고객 정보
        log.info("토큰 받았다");
        log.info(token.getToken());
        messageClientService.saveToken(client,token.getToken());
        log.info("토큰 저장");
        return"true";
    }

    //메시지 수신 확인 및 업데이트
    @PostMapping("/message/Check")
    public void checkMessageReceive(@RequestBody MessageReceiveCheckDto msgDto){
        messageClientService.updateMsgStatus(msgDto.getMsgNo());
    }

    // 스플래쉬 실행 시 미 수신 마지막 메시지 재전송
    @PostMapping("/splash/message/reSend")
    public void resendMessage(HttpServletRequest request) throws IOException {
        Client client = messageClientService.getClient(request.getSession());
        messageClientService.reSendMessage(client);
    }

}
