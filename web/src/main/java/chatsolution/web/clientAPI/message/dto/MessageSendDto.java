package chatsolution.web.clientAPI.message.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MessageSendDto {

    private String content; // 메시지 내용
    private int msgType; // 메시지 타입

}
