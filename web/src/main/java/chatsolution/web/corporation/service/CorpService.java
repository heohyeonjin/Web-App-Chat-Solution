package chatsolution.web.corporation.service;


import chatsolution.web.corporation.dto.*;
import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.corporation.repository.CorpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CorpService {

    private final CorpRepository corpRepository;

    //기업 리스트
    public List<CorpListDto> corplist(){
       List<Corporation> corp = corpRepository.findAll();
       return corp.stream()
               .map(o -> new CorpListDto(o))
               .collect(Collectors.toList());
    }

    // 기업 리스트 페이징
    public List<CorpListDto> getCorpListPage(int page) {
        Page<Corporation> corpPage = corpRepository.findAll(PageRequest.of(page, 15, Sort.by("corpNo").ascending()));
        List<Corporation> corporations = corpPage.getContent();
        return corporations.stream()
                .map(o -> new CorpListDto(o))
                .collect(Collectors.toList());
    }

    // 페이지 개수 찾기
    public CorpPages getCorpPages(int corpPage) {
        int total = corpRepository.findAll().size();
        int size = 0;

        if (total % 15 == 0) size = total / 15;
        else size = total / 15 + 1;
        return new CorpPages(size, corpPage);
    }

    // 기업 검색
    public List<CorpListDto> search(String keyword, int page) {
        Page<Corporation> find = corpRepository.findAllByCorpNameContaining(keyword, PageRequest.of(page, 15, Sort.by("corpNo").ascending()));
        List<Corporation> corporations = find.getContent();
        return corporations.stream()
                .map(o -> new CorpListDto(o))
                .collect(Collectors.toList());
    }

    // 기업 검색 페이지 개수
    public CorpPages getSearchPages(int corpPage, String keyword) {
        int total = corpRepository.findByCorpNameContaining(keyword).size();
        int size = 0;

        if (total % 15 == 0) size = total / 15;
        else size = total / 15 + 1;
        return new CorpPages(size, corpPage);
    }

    //기업 상세정보
    public CorpInfoDto corpinfo(Long corpId){
        Corporation corp = corpRepository.findById(corpId).orElseThrow(
                ()->new NullPointerException("접근 오류"));
        log.info(corp.getCorpName());
        return new CorpInfoDto(corp);
    }

    // 기업 로고 등록
//    public void imageUpload(MultipartFile logo) {
//        String uploadFolder = "/home/ubuntu/image";
//        String uploadFileName = logo.getOriginalFilename();
//        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
//
//        File saveFile = new File(uploadFolder, uploadFileName);
//        try {
//            logo.transferTo(saveFile);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }

    //기업 등록
    public CorpInfoDto saveCorp(CorpRegDto corpRegDto) {
        Corporation newCorp = new Corporation(corpRegDto);
        corpRepository.save(newCorp);

        return new CorpInfoDto(newCorp);
    }

    public boolean idCheck(String id) {
        return corpRepository.existsByCorpId(id);
    }

    //기업 수정
    @Transactional
    public void updateCorp(Long corpId, CorpEditDto corpEditDto){
        Corporation corp = corpRepository.findById(corpId).orElseThrow(
                ()->new NullPointerException("접근 오류"));
        log.info(corpEditDto.getCorp_name());

        corp.setCorpStatus(corpEditDto.getCorp_status());
        corp.setCorpName(corpEditDto.getCorp_name());
        corp.setCorpAdmin(corpEditDto.getCorp_admin());
        corp.setCorpPhone(corpEditDto.getCorp_phone());
        corp.setCorpEmail(corpEditDto.getCorp_email());
        corp.setCorpDesc(corpEditDto.getCorp_desc());
    }
}
