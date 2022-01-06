package chatsolution.web.counselor.controller;

import chatsolution.web.corporation.dto.CorpListDto;
import chatsolution.web.corporation.dto.CorpPages;
import chatsolution.web.counselor.dto.*;
import chatsolution.web.counselor.model.Counselor;
import chatsolution.web.counselor.repository.CounselorRepository;
import chatsolution.web.counselor.service.CounselorService;
import com.google.api.Http;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/counselor")
@RequiredArgsConstructor
public class CounselorController {

    private final CounselorService counselorService;
    private final CounselorRepository counselorRepository;

    // 상담원 리스트 페이징
    @GetMapping
    public String counselorPaging(@RequestParam(value = "page", defaultValue = "0") int counPage, Model model, HttpServletRequest request) {
        Long corpNo = counselorService.getCorpNo(request.getSession()); // 기업 정보
        Long auth_classify = counselorService.getAuthClassify(request.getSession()); // 권한 가져오기
        if(corpNo==null) corpNo=0L;
        List<CounListDto> couns = counselorService.getCounListPage(counPage,corpNo);
        CounPages pages = counselorService.getCounPages(counPage);
        model.addAttribute("couns", couns);
        model.addAttribute("pages", pages);
        model.addAttribute("nav", "coun");
        model.addAttribute("auth", auth_classify);
        return "counselor/coun_list";
    }

    // 상담원 검색
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, @RequestParam(value = "page", defaultValue = "0") int counPage, Model model) {
        List<CounListDto> couns = counselorService.search(keyword, counPage);
        CounPages pages = counselorService.getSearchPages(counPage, keyword);
        model.addAttribute("couns", couns);
        model.addAttribute("pages", pages);
        model.addAttribute("nav", "coun");
        return "counselor/coun_list";
    }

    // 상담원 등록 페이지
    @GetMapping("/add")
    public String addCounselorForm(Model model,HttpServletRequest request) {
        List<EmbeddedCorpListDto> EmbeddedCorp = counselorService.corpList();
        Long auth_classify = counselorService.getAuthClassify(request.getSession()); // 권한 가져오기
        model.addAttribute("corps", EmbeddedCorp);
        model.addAttribute("nav", "coun");
        model.addAttribute("auth",auth_classify);
        return "counselor/coun_new";
    }

    //아이디 중복체크
    @PostMapping("/idCheck")
    public @ResponseBody boolean idCheck(@RequestParam("id") String id) {
        log.info("아이디 중복체크 - 전달받은 id: " + id);
        boolean counIdCheck = counselorService.idCheck(id);
        log.info("아이디 중복체크 - id 확인결과: " + counIdCheck);
        return counIdCheck;
    }

    // 상담원 등록
    @PostMapping("/add")
    public String addCounselor(@ModelAttribute("counsReg") CounRegDto counRegDto) {
        log.info(counRegDto.getEmbeded_corp()+"corp");
        log.info(counRegDto.getCoun_id());
        log.info(counRegDto.getCoun_gender()+"gender");
        counselorService.saveCoun(counRegDto);
        return "redirect:/counselor";
    }
  
    // 상담원 정보 조회
    @GetMapping("/{counNo}")
    public String counselor(@PathVariable long counNo, Model model, HttpServletRequest request) {
        CounInfoDto coun = counselorService.counInfo(counNo);
        Long auth_classify = counselorService.getAuthClassify(request.getSession()); // 권한 가져오기
        model.addAttribute("coun", coun);
        model.addAttribute("nav", "coun");
        model.addAttribute("auth",auth_classify);
        return "counselor/coun_info";
    }

    // 상담원 정보 수정 페이지
    @GetMapping("/{counNo}/edit")
    public String editForm(@PathVariable Long counNo, Model model, HttpServletRequest request) {
        Long auth_classify = counselorService.getAuthClassify(request.getSession()); // 권한 가져오기
        CounInfoDto coun = counselorService.counInfo(counNo);
        model.addAttribute("coun", coun);
        model.addAttribute("nav", "coun");
        model.addAttribute("auth",auth_classify);
        return "counselor/coun_edit";
    }

    // 상담원 정보 수정
    @PostMapping("/{counNo}/edit")
    public String edit(@PathVariable Long counNo, @ModelAttribute("coun") CounEditDto editDto) {
        counselorService.updateCoun(counNo, editDto);
        return "redirect:/counselor/{counNo}";
    }
}
