package chatsolution.web.corporation.controller;


import chatsolution.web.corporation.dto.*;
import chatsolution.web.corporation.service.CorpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/corporation")
@RequiredArgsConstructor
public class CorpController {

    private final CorpService corpservice;

    // 기업 리스트 페이징
    @GetMapping
    public String corporationPaging(@RequestParam(value = "page", defaultValue = "0") int corpPage, Model model) {
        List<CorpListDto> corps = corpservice.getCorpListPage(corpPage);
        CorpPages pages = corpservice.getCorpPages(corpPage);
        model.addAttribute("corps", corps);
        model.addAttribute("pages", pages);
        model.addAttribute("nav", "corp");
        return "corporation/corp_list";
    }

    // 기업 검색
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, @RequestParam(value = "page", defaultValue = "0") int corpPage, Model model) {
        List<CorpListDto> corps = corpservice.search(keyword, corpPage);
        CorpPages pages = corpservice.getSearchPages(corpPage, keyword);
        model.addAttribute("corps", corps);
        model.addAttribute("pages", pages);
        model.addAttribute("nav", "corp");
        return "corporation/corp_list";
    }

    // 기업 등록 페이지
    @GetMapping("/add")
    public String addCorpForm(Model model) {
        model.addAttribute("nav", "corp");
        return "corporation/corp_new";
    }

    // 기업 등록
    @PostMapping("/add")
    public String addCorp(@ModelAttribute("corpReg") CorpRegDto corpRegDto, Model model) {
        log.info(corpRegDto.getCorp_name());

        //corpservice.imageUpload(corpRegDto.getCorp_logo());
        CorpInfoDto newCorp = corpservice.saveCorp(corpRegDto);
        model.addAttribute("corp", newCorp);
        return "redirect:/corporation/" + newCorp.getCorp_no();
    }

    //기업 상세정보
    @GetMapping("/{corpId}")
    public String corporation(@PathVariable long corpId, Model model) {
        CorpInfoDto corp = corpservice.corpinfo(corpId);
        model.addAttribute("corp", corp);
        model.addAttribute("nav", "corp");
        return "corporation/corp_info";
    }

    // 기업 아이디 중복확인
    @PostMapping("/idCheck")
    public @ResponseBody boolean idCheck(@RequestParam("id") String id) {
        log.info("아이디 중복체크 - 전달받은 id: " + id);
        boolean corpIdCheck = corpservice.idCheck(id);
        log.info("아이디 중복체크 - id 확인결과: " + corpIdCheck);
        return corpIdCheck;
    }

    // 기업 정보 수정 페이지
    @GetMapping("/{corpId}/edit")
    public String editForm(@PathVariable Long corpId, Model model){
        CorpInfoDto corp = corpservice.corpinfo(corpId);
        model.addAttribute("corp",corp);
        model.addAttribute("nav", "corp");
        return "corporation/corp_edit";
    }

    // 기업 정보 수정
    @PostMapping("/{corpId}/edit")
    public String edit(@PathVariable Long corpId, @ModelAttribute("corp") CorpEditDto corpEditDto){
        corpservice.updateCorp(corpId,corpEditDto);
        return "redirect:/corporation/{corpId}";
    }
}
