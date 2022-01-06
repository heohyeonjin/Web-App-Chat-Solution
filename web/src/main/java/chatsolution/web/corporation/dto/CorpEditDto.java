package chatsolution.web.corporation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CorpEditDto {
    private int corp_status; //상태
    private String corp_name; //기업명
    private String corp_admin; //대표자
    private String corp_phone; //연락처
    private String corp_email;
    private String corp_desc;
}