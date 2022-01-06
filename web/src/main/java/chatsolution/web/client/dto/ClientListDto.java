package chatsolution.web.client.dto;

import chatsolution.web.client.model.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter@Setter
@NoArgsConstructor
public class ClientListDto {
    private Long client_no;
    private String client_name;
    private String client_email;
    private String client_gender;
    private String date;
    private String client_status;

    public ClientListDto(Client client){
        this.client_no = client.getClientNo();
        this.client_name = client.getClientName();
        this.client_email = client.getClientEmail();
        int gender = client.getClientGender();
        if(gender==1){
            this.client_gender="여";
        }
        else{
            this.client_gender="남";
        }
        this.date = client.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        int status = client.getClientStatus();
        if(status==1){
            this.client_status="사용 중";
        }
        else{
            this.client_status="사용 정지";
        }

    }
}
