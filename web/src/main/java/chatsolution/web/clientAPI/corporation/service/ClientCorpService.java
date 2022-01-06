package chatsolution.web.clientAPI.corporation.service;


import chatsolution.web.clientAPI.corporation.dto.ClientCorpListDto;
import chatsolution.web.clientAPI.corporation.dto.CorpDetailDto;
import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.corporation.repository.CorpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientCorpService {

    private final CorpRepository corpRepository;

    public List<ClientCorpListDto> corpList(){
        //기업 리스트
        List<Corporation> corp = corpRepository.findAll();
        return corp.stream()
                .map(o -> new ClientCorpListDto(o))
                .collect(Collectors.toList());
    }

    public CorpDetailDto corpDetail(Long corpNo){
        Optional<Corporation> findcorp = corpRepository.findById(corpNo);
        Corporation corp = findcorp.get();
        CorpDetailDto detail = new CorpDetailDto(corp);
        return detail;
    }

    public Corporation corpFind(Long corpNo){
        Optional<Corporation> findcorp = corpRepository.findById(corpNo);
        Corporation corp = findcorp.get();
        return corp;
    }
}
