package chatsolution.web.clientAPI.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SignInRequestDto {
    private String email;
    private String password;
}
