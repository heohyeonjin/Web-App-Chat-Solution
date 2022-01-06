package chatsolution.web.clientAPI.auth.service;

import chatsolution.web.clientAPI.auth.dto.IdDoubleCheckDto;
import chatsolution.web.clientAPI.auth.dto.SignInRequestDto;
import chatsolution.web.clientAPI.auth.dto.SignUpRequestDto;
import chatsolution.web.client.model.Client;
import chatsolution.web.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientAuthService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    // 아이디 중복체크
    public boolean idCheck(IdDoubleCheckDto idDoubleCheckDto) {

        String requestId = idDoubleCheckDto.getEmail();
        Client exist = clientRepository.findByClientEmail(requestId);

        // 존재하면 false
        return exist == null;
    }

    // 회원가입
    @Transactional
    public String registerClient(SignUpRequestDto requestDto) {

        // 패스워드 암호화
        String encoding = passwordEncoder.encode(requestDto.getPassword());
        requestDto.setPassword(encoding);

        Client newClient = new Client(requestDto);
        clientRepository.save(newClient);
        newClient.setClientStatus(1); // 회원가입 시 상태 1

        return newClient.getClientEmail();
    }

    //로그인
    public Client loginCheck(SignInRequestDto signInRequestDto){
        String ClientEmail = signInRequestDto.getEmail();
        Client findClient = clientRepository.findByClientEmail(ClientEmail);
        if(findClient!=null) {
            if (passwordEncoder.matches(signInRequestDto.getPassword(), findClient.getClientPw())) {
                return findClient;
            }
            else{
                log.info("패스워드 오류");
                return null;
            }
        }
       return null;
        }
}
