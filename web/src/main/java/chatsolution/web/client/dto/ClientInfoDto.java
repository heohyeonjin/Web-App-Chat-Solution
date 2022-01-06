package chatsolution.web.client.dto;

import chatsolution.web.client.model.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class ClientInfoDto {
    private Long client_no;
    private String client_status;
    private String client_name;
    private String client_gender;
    private String client_phone;
    private String client_email;

    public ClientInfoDto(Client client){
        this.client_no=client.getClientNo();
        int status = client.getClientStatus();
        if(status==1){
            this.client_status="사용 중";
        }
        else{
            this.client_status="사용 정지";
        }
        this.client_name = client.getClientName();
        int gender = client.getClientGender();
        if(gender==1){
            this.client_gender = "여자";
        }
        else{
            this.client_gender = "남자";
        }
        this.client_phone=client.getClientPhone();
        this.client_email=client.getClientEmail();
    }
}
