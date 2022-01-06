package chatsolution.web.client.dto;


import chatsolution.web.message.model.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter @Setter
@NoArgsConstructor
public class CounContentsDto {
    private String content;
    private int sender;
    private String date;
    private String time;

    public CounContentsDto(Message message){
        this.content = message.getMsgContent();
        this.sender = message.getMsgSender();
        this.date = message.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.time = message.getCreatedAt().getHour() + ":" + message.getCreatedAt().getMinute();
    }
}
