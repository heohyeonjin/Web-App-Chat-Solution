package chatsolution.web.corporation.dto;

import chatsolution.web.corporation.model.Corporation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CorpInfoDto {
    private Long corp_no;
    private String corp_status; //상태
    private String corp_name; //기업명
    private String corp_admin; //대표자
    private String corp_id; //아이디
    private String corp_phone; //연락처
    private String corp_email;
    private String corp_desc;
    private String corp_logo;


    public CorpInfoDto(Corporation corporation){
        int corp = corporation.getCorpStatus();
        this.corp_no = corporation.getCorpNo();
        this.corp_name = corporation.getCorpName();
        this.corp_admin = corporation.getCorpAdmin();
        this.corp_id = corporation.getCorpId();
        this.corp_phone = corporation.getCorpPhone();
        this.corp_email = corporation.getCorpEmail();
        this.corp_desc = corporation.getCorpDesc();
        this.corp_logo = corporation.getCorpLogo();

        if(corp==1) {
            this.corp_status = "사용 중";
        }
        else if(corp==0){
            this.corp_status ="사용 정지";
        }
        else{
            this.corp_status="확인 불가";
        }


    }

}

