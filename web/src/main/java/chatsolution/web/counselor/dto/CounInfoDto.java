package chatsolution.web.counselor.dto;

import chatsolution.web.counselor.model.Counselor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CounInfoDto {
    private Long counNo;
    private String counStatus;
    private String counCorp;
    private String counName;
    private String counId;
    private String counGender;
    private String counPhone;
    private String counEmail;
    private String counImage;

    public CounInfoDto(Counselor counselor) {
        this.counNo = counselor.getCounNo();
        int status = counselor.getCounStatus();
        this.counCorp = counselor.getCorporation().getCorpName();
        this.counName = counselor.getCounName();
        this.counId = counselor.getCounId();
        int gender = counselor.getCounGender();
        this.counPhone = counselor.getCounPhone();
        this.counEmail = counselor.getCounEmail();
        this.counImage = counselor.getCounImage();

        if (status == 1) {
            this.counStatus = "사용 중";
        }
        else if (status == 0) {
            this.counStatus = "사용 정지";
        }
        else {
            this.counStatus = "확인 불가";
        }

        if (gender == 0){
            this.counGender = "남";
        }
        else if (gender == 1) {
            this.counGender = "여";
        }
        else{
            this.counGender = "확인 불가";
        }
    }
}
