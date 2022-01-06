package chatsolution.web.clientAPI.corporation.dto;

import chatsolution.web.corporation.model.Corporation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ClientCorpListDto {
    private Long corpNo;
    private String corpName;
    private String corpDesc;

    public ClientCorpListDto(Corporation corporation){
        this.corpNo=corporation.getCorpNo();
        this.corpName = corporation.getCorpName();
        this.corpDesc = corporation.getCorpDesc();
    }
}
