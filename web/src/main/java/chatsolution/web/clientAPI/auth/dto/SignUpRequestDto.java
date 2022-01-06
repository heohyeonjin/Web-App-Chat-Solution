package chatsolution.web.clientAPI.auth.dto;

import chatsolution.web.client.model.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private int gender;
    private String tel;

    public SignUpRequestDto(Client client){
        this.email = client.getClientEmail();
        this.password = client.getClientPw();
        this.name = client.getClientName();
        this.gender = client.getClientGender();
        this.tel = client.getClientPhone();
    }
}
