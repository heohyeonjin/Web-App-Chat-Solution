package chatsolution.web.message.dto;

import chatsolution.web.message.model.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter @Setter
@NoArgsConstructor
public class MessageDto {
    private Long no;
    private String content;
    private String date;
    private String time;
    private int sender;

    private String timePattern = "HH:mm";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern, new Locale("ko", "KR"));

    public MessageDto(Message message) {
        this.no = message.getMsgNo();
        this.content = message.getMsgContent();
        this.date = message.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.time = message.getCreatedAt().getHour() + ":" + message.getCreatedAt().getMinute();
        this.sender = message.getMsgSender();
    }
}