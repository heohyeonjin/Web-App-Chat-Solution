package chatsolution.web.corporation.dto;


import chatsolution.web.corporation.model.Corporation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter@Setter
@NoArgsConstructor
public class CorpListDto {
    private Long corp_no;
    private String corp_name;
    private String corp_phone;
    private String corp_admin;
    private String created_at;
    private String corp_status;

    public CorpListDto(Corporation corporation){
        this.corp_no = corporation.getCorpNo();
        this.corp_name = corporation.getCorpName();
        this.corp_phone = corporation.getCorpPhone();
        this.corp_admin = corporation.getCorpAdmin();
        this.created_at = corporation.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        int corp = corporation.getCorpStatus();
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
