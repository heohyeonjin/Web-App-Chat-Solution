package chatsolution.web.counselor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CounEditDto {
    private int counStatus;
    private String counName;
    private int counGender;
    private String counPhone;
    private String counEmail;
    private String counImage;
}
