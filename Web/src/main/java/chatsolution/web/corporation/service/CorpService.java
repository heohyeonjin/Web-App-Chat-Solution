package chatsolution.web.corporation.service;


import chatsolution.web.corporation.dto.CorpInfoDto;
import chatsolution.web.corporation.dto.CorpListDto;
import chatsolution.web.corporation.dto.CorpRegDto;
import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.corporation.repository.CorpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CorpService {

    private final CorpRepository corpRepository;

    public List<CorpListDto> corplist(){
       List<Corporation> corp = corpRepository.findAll();
       return corp.stream()
               .map(o -> new CorpListDto(o))
               .collect(Collectors.toList());
    }

    public CorpInfoDto corpinfo(Long corpId){
        Corporation corp = corpRepository.findById(corpId).orElseThrow(
                ()->new NullPointerException("접근 오류"));
        return new CorpInfoDto(corp);
    }

    public CorpInfoDto saveCorp(CorpRegDto corpRegDto) {
        Corporation newCorp = new Corporation(corpRegDto);
        corpRepository.save(newCorp);

        return new CorpInfoDto(newCorp);
    }

    public boolean idCheck(String id) {
        return corpRepository.existsByCorpId(id);
    }
}
