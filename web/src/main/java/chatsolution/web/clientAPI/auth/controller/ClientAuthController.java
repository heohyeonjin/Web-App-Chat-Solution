package chatsolution.web.clientAPI.auth.controller;

import chatsolution.web.clientAPI.auth.dto.ClientMyPageDto;
import chatsolution.web.clientAPI.auth.dto.IdDoubleCheckDto;
import chatsolution.web.clientAPI.auth.dto.SignInRequestDto;
import chatsolution.web.clientAPI.auth.dto.SignUpRequestDto;
import chatsolution.web.client.model.Client;
import chatsolution.web.clientAPI.auth.service.ClientAuthService;
import chatsolution.web.clientAPI.message.service.ClientMessageService;
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
    private final ClientMessageService clientMessageService;

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
    public String Login(@RequestBody SignInRequestDto requestDto, HttpServletRequest servletRequest){
        Client client = clientAuthService.loginCheck(requestDto);
        if(client==null) {
            return "false";
        }
        else{
            servletRequest.getSession().setAttribute("clientNo",client.getClientNo());
            log.info("로그인 성공");
            return "true";
        }
    }

    // 마이페이지
    @GetMapping("/mypage")
    public ClientMyPageDto Mypage(HttpServletRequest request){
        Client client = clientMessageService.getClient(request.getSession());
        return new ClientMyPageDto(client);
    }
}
