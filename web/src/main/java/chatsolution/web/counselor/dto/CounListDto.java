package chatsolution.web.counselor.dto;

import chatsolution.web.counselor.model.Counselor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter @Setter
@NoArgsConstructor
public class CounListDto {
    private Long counNo;
    private String counCorp;
    private String counName;
    private String counPhone;
    private String counGender;
    private String counCreatedAt;
    private String counStatus;

    public CounListDto(Counselor counselor) {
        this.counNo = counselor.getCounNo();
        this.counCorp = counselor.getCorporation().getCorpName();
        this.counName = counselor.getCounName();
        this.counPhone = counselor.getCounPhone();
        int gender = counselor.getCounGender();
        this.counCreatedAt = counselor.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        int status = counselor.getCounStatus();

        if (gender == 0) {
            this.counGender = "남";
        } else {
            this.counGender = "여";
        }

        if (status == 1) {
            this.counStatus = "사용 중";
        } else if (status == 0) {
            this.counStatus = "사용 정지";
        } else {
            this.counStatus = "확인 불가";
        }
    }


}
