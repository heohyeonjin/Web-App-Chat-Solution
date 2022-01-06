package chatsolution.web.counselor.dto;

import chatsolution.web.corporation.model.Corporation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CounRegDto {
    private int coun_gender;
    private Long embeded_corp;
    private String coun_name;
    private String coun_id;
    private String coun_pw;
    private String coun_phone;
    private String coun_email;

}
