package chatsolution.web.counselor.service;

import chatsolution.web.client.dto.ClientListDto;
import chatsolution.web.client.dto.ClientPages;
import chatsolution.web.client.model.Client;
import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.corporation.repository.CorpRepository;
import chatsolution.web.counselor.dto.*;
import chatsolution.web.counselor.model.Counselor;
import chatsolution.web.counselor.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CounselorService {

    private final CounselorRepository counselorRepository;
    private final CorpRepository corpRepository;

    // 상담원 리스트
    public List<CounListDto> counList(){
        List<Counselor> couns = counselorRepository.findAll();
        return couns.stream()
                .map(o -> new CounListDto(o))
                .collect(Collectors.toList());
    }

    // 상담원 리스트 페이징
    public List<CounListDto> getCounListPage(int page, Long corpNo) {
        if(corpNo!=0L){
            log.info("corpNo가 0L이 아닌 경우");
          List<Counselor> counselors = counselorRepository.findAllByCorporationCorpNo(corpNo);
            return counselors.stream()
                    .map(o -> new CounListDto(o))
                    .collect(Collectors.toList());
        }
        log.info("corpNo가 0L인경우");
        Page<Counselor> counPage = counselorRepository.findAll(PageRequest.of(page, 15, Sort.by("counNo").ascending()));
        List<Counselor> counselors = counPage.getContent();
        return counselors.stream()
                .map(o -> new CounListDto(o))
                .collect(Collectors.toList());
    }

    // 페이지 개수 찾기
    public CounPages getCounPages(int counPage) {
        int total = counselorRepository.findAll().size();
        int size = 0;

        if (total % 15 == 0)
            size = total / 15;
        else size = total / 15 + 1;
        return new CounPages(size, counPage);
    }

    // 상담원 검색
    public List<CounListDto> search(String keyword, int page) {
        Page<Counselor> find = counselorRepository.findAllByCounNameContaining(keyword, PageRequest.of(page, 15, Sort.by("counNo").ascending()));
        List<Counselor> counselors = find.getContent();
        return counselors.stream()
                .map(o -> new CounListDto(o))
                .collect(Collectors.toList());
    }

    // 상담원 검색 페이지 개수
    public CounPages getSearchPages(int counPage, String keyword) {
        int total = counselorRepository.findByCounNameContaining(keyword).size();
        int size = 0;

        if (total % 15 == 0) size = total / 15;
        else size = total / 15 + 1;
        return new CounPages(size, counPage);
    }

    // 소속 기업 리스트
    public List<EmbeddedCorpListDto> corpList(){
        List<Corporation> corps = corpRepository.findAll();
        return corps.stream()
                .map(o -> new EmbeddedCorpListDto(o))
                .collect(Collectors.toList());
    }

    //중복 체크
    public boolean idCheck(String id) {
        return counselorRepository.existsByCounId(id);
    }
  
    //상담원 등록
    public Boolean saveCoun(CounRegDto counRegDto){
        var corpid= counRegDto.getEmbeded_corp();
        Corporation corp = corpRepository.findById(corpid).orElseThrow(
                ()-> new NullPointerException("접근 오류"));
        Counselor newCoun = new Counselor(counRegDto,corp);
        counselorRepository.save(newCoun);
        return true;
    }

    // 상담원 상세정보
    public CounInfoDto counInfo(Long counNo) {
        Counselor coun = counselorRepository.findById(counNo).orElseThrow(
                ()->new NullPointerException("접근 오류"));
      
        return new CounInfoDto(coun);
    }

    // 상담원 수정
    @Transactional
    public void updateCoun(Long counNo, CounEditDto editDto) {
        Counselor coun = counselorRepository.findById(counNo).orElseThrow(
                ()->new NullPointerException("접근 오류"));
        log.info("찾은 상담원 ID: " + coun.getCounNo());

        coun.setCounStatus(editDto.getCounStatus());
        coun.setCounName(editDto.getCounName());
        coun.setCounGender(editDto.getCounGender());
        coun.setCounPhone(editDto.getCounPhone());
        coun.setCounEmail(editDto.getCounEmail());
        coun.setCounImage(editDto.getCounImage());
    }

    // 기업 No값
    public Long getCorpNo(HttpSession session){
        Long corpNo = (Long) session.getAttribute("corpNo");
        return corpNo;
    }

    // 권한 값
    public Long getAuthClassify(HttpSession session){
        Long classify = (Long) session.getAttribute("auth_classify");
        return classify;
    }
}
