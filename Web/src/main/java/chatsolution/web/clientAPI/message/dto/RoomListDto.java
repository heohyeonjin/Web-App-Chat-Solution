package chatsolution.web.clientAPI.message.dto;


import chatsolution.web.message.model.Message;
import chatsolution.web.message.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class RoomListDto {
    private String CorpName; // 회사 명
    private String content;
    private String time;
    private int ClientRead; // 읽음 처리 ( 1: 읽음 0 : 안읽음 )

    public RoomListDto(Room room){
        this.CorpName = room.getCounselor().getCorporation().getCorpName();
        int size = room.getMessages().size()-1;
        Message lastMsg = room.getMessages().get(size);
        this.content = lastMsg.getMsgContent();
        this.ClientRead = room.getMessages().get(size).getClientRead();
        log.info(this.ClientRead+"");

        // 오늘 날짜
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowDate = now.format(dateTimeFormatter);

        if(!lastMsg.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE).equals(nowDate)){ //다른 날짜
            this.time = lastMsg.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        else{
            int hour = lastMsg.getCreatedAt().getHour();
            if(hour>=12){
                if(hour==12){
                    hour=12;
                }
                else{
                    hour = hour-12;
                }
                this.time = "오후 "+hour+":"+lastMsg.getCreatedAt().getMinute();
            }
            else{
                this.time="오전 "+ hour+ ":" + lastMsg.getCreatedAt().getMinute();
            }
        }
    }

}
