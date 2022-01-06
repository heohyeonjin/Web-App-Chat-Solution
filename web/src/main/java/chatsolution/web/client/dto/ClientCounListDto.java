package chatsolution.web.client.dto;


import chatsolution.web.message.model.Message;
import chatsolution.web.message.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter @Setter
@NoArgsConstructor
public class ClientCounListDto {
    private Long roomNo;
    private String date;
    private String corpName;
    private String content;

    public ClientCounListDto(Room room){
        this.roomNo = room.getRoomNo();
        int size = room.getMessages().size()-1;
        Message lastMsg = room.getMessages().get(size);
        this.date = lastMsg.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.corpName = room.getCounselor().getCorporation().getCorpName();
        this.content = lastMsg.getMsgContent();
    }
}
