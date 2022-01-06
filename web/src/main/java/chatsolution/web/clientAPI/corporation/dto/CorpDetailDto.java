package chatsolution.web.clientAPI.corporation.dto;


import chatsolution.web.corporation.model.Corporation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CorpDetailDto {
    private String corpName;
    private String corpAdmin;
    private String corpDesc;
    private String corpEmail;
    private String corpTel;

    public CorpDetailDto(Corporation corporation){
        this.corpName = corporation.getCorpName();
        this.corpAdmin = corporation.getCorpAdmin();
        this.corpDesc = corporation.getCorpDesc();
        this.corpTel = corporation.getCorpPhone();
        this.corpEmail = corporation.getCorpEmail();
    }

}
