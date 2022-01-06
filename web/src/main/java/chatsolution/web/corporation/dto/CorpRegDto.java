package chatsolution.web.corporation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class CorpRegDto {
    private String corp_name;
    private String corp_admin;
    private String corp_id;
    private String corp_pw;
    private String corp_phone;
    private String corp_email;
    private String corp_logo;
    private String corp_descrip;

}
