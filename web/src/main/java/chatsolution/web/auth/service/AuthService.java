package chatsolution.web.auth.service;

import chatsolution.web.auth.dto.LoginRequestDto;
import chatsolution.web.auth.model.Admin;
import chatsolution.web.auth.repository.AuthRepository;
import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.corporation.repository.CorpRepository;
import chatsolution.web.counselor.model.Counselor;
import chatsolution.web.counselor.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final CorpRepository corpRepository;
    private final CounselorRepository counselorRepository;

    public Long loginProcess(LoginRequestDto requestDto) {
        String id = requestDto.getLogin_id();
        String pw = requestDto.getLogin_pw();
        String auth = requestDto.getLogin_radio();
        Long loginNo = 0L;

        if (auth.equals("login_admin")) {
            log.info("admin 로그인 process 진입");
            Admin find = authRepository.findByAdminId(id);

            if (find == null) return 0L;
            if (!find.getAdminPw().equals(pw)) return 0L;

            loginNo = find.getAdminNo();
        }
        else if (auth.equals("login_corp")) {
            log.info("corporation 로그인 process 진입");
            Corporation find = corpRepository.findByCorpId(id);

            if (find == null) return 0L;
            if (!find.getCorpPw().equals(pw)) return 0L;

            loginNo = find.getCorpNo();
        }
        else if (auth.equals("login_coun")) {
            log.info("counselor 로그인 process 진입");
            Counselor find = counselorRepository.findByCounId(id);

            if (find == null) return 0L;
            if (!find.getCounPw().equals(pw)) return 0L;
            loginNo = find.getCounNo();
        }

        return loginNo;
    }
}
