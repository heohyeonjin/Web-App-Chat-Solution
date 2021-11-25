package chatsolution.web.clientAPI.auth.controller;

import chatsolution.web.clientAPI.auth.dto.IdDoubleCheckDto;
import chatsolution.web.clientAPI.auth.dto.SignInRequestDto;
import chatsolution.web.clientAPI.auth.dto.SignUpRequestDto;
import chatsolution.web.clientAPI.auth.model.Client;
import chatsolution.web.clientAPI.auth.service.ClientAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClientAuthController {

    private final ClientAuthService clientAuthService;

    // 아이디 중복체크
    @PostMapping("/check")
    public String idDoubleCheck(@RequestBody IdDoubleCheckDto idDoubleCheckDto) {
        boolean exist = clientAuthService.idCheck(idDoubleCheckDto);
        if (!exist) return "false";
        return "true";
    }

    // 회원가입
    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDto requestDto){
        String savedClient = clientAuthService.registerClient(requestDto);

        if (!Objects.equals(savedClient, requestDto.getEmail())) {
            return "false";
        }
        return "true";
    }

    //로그인
    @PostMapping("/login")
    public SignUpRequestDto Login(@RequestBody SignInRequestDto requestDto, HttpServletRequest servletRequest){
        Client client = clientAuthService.loginCheck(requestDto);
        if(client==null) {
            return null;
        }
        else{
            SignUpRequestDto signUpRequestDto = new SignUpRequestDto(client); //값 넘겨주기
            servletRequest.getSession().setAttribute("clientNo",client.getClientNo());
            log.info("로그인 성공");
            return signUpRequestDto;
        }
    }
}