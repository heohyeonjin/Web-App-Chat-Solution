package chatsolution.web.clientAPI.auth.dto;


import chatsolution.web.client.model.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ClientMyPageDto {
    //이름 이메일, 전화번호,성별
    private String name;
    private String email;
    private String phone;
    private String gender;

    public ClientMyPageDto(Client client){
        this.name = client.getClientName();
        this.email = client.getClientEmail();
        this.phone = client.getClientPhone();
        int gender = client.getClientGender();
        if(gender==1){
            this.gender="여자";
        }
        else{
            this.gender="남자";
        }
    }
}
