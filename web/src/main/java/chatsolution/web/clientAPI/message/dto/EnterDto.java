package chatsolution.web.clientAPI.message.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EnterDto {
    private Long roomNo;
    private String corpName;
    private Long corpNo;

    public EnterDto(Long roomNo, String corpName, Long corpNo){
        this.roomNo = roomNo;
        this.corpName = corpName;
        this.corpNo = corpNo;
    }
}
