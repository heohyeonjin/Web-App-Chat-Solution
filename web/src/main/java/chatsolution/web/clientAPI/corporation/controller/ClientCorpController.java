package chatsolution.web.clientAPI.corporation.controller;


import chatsolution.web.clientAPI.corporation.dto.ClientCorpListDto;
import chatsolution.web.clientAPI.corporation.dto.CorpDetailDto;
import chatsolution.web.clientAPI.corporation.service.ClientCorpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientCorpController {
    private final ClientCorpService corpService;

    // 기업 리스트
    @GetMapping("/api/corporations")
    public List<ClientCorpListDto> corpList(){
        List<ClientCorpListDto> corps = corpService.corpList();
        return corps;
    }

    //기업 상세정보
    @GetMapping("/api/corporation/{corpNo}")
    public CorpDetailDto corpDetail(@PathVariable Long corpNo){
         CorpDetailDto corpDetail = corpService.corpDetail(corpNo);
         return corpDetail;
    }
}
