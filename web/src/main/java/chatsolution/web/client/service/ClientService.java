package chatsolution.web.client.service;

import chatsolution.web.client.dto.*;
import chatsolution.web.client.model.Client;
import chatsolution.web.client.repository.ClientRepository;
import chatsolution.web.corporation.dto.CorpListDto;
import chatsolution.web.corporation.dto.CorpPages;
import chatsolution.web.counselor.dto.CounListDto;
import chatsolution.web.counselor.model.Counselor;
import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.message.model.Message;
import chatsolution.web.message.model.Room;
import chatsolution.web.message.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
private final ClientRepository clientRepository;
private final RoomRepository roomRepository;

    //고객 리스트
    public List<ClientListDto> clientList(){
        List<Client> client = clientRepository.findAll();
        return client.stream()
                .map(ClientListDto::new)
                .collect(Collectors.toList());
    }

    // 고객 리스트 페이징
    public List<ClientListDto> getClientListPage(int page, Long corpNo) {
        if(corpNo!=0L){
            log.info("corpNo가 0L이 아닌 경우");
            List<Room> rooms = roomRepository.findAllByCounselor_Corporation_CorpNo(corpNo);
            List<Client> clients = new ArrayList<>();
            log.info("룸사이즈 : "+ rooms.size());
            for(int i = 0 ; i< rooms.size();i++){
                clients.add(rooms.get(i).getClient());
            }
            return clients.stream()
                    .map(o -> new ClientListDto(o))
                    .collect(Collectors.toList());
        }
        Page<Client> clientPage = clientRepository.findAll(PageRequest.of(page, 15, Sort.by("clientNo").ascending()));
        List<Client> clients = clientPage.getContent();
        return clients.stream()
                .map(o -> new ClientListDto(o))
                .collect(Collectors.toList());
    }

    // 페이지 개수 찾기
    public ClientPages getClientPages(int clientPage) {
        int total = clientRepository.findAll().size();
        int size = 0;

        if (total % 15 == 0) size = total / 15;
        else size = total / 15 + 1;
        return new ClientPages(size, clientPage);
    }

    // 고객 검색
    public List<ClientListDto> search(String keyword, int page) {
        Page<Client> find = clientRepository.findAllByClientNameContaining(keyword, PageRequest.of(page, 15, Sort.by("clientNo").ascending()));
        List<Client> clients = find.getContent();
        return clients.stream()
                .map(o -> new ClientListDto(o))
                .collect(Collectors.toList());
    }

    // 고객 검색 페이지 개수
    public ClientPages getSearchPages(int clientPage, String keyword) {
        int total = clientRepository.findByClientNameContaining(keyword).size();
        int size = 0;

        if (total % 15 == 0) size = total / 15;
        else size = total / 15 + 1;
        return new ClientPages(size, clientPage);
    }

    //고객 상세정보
    public ClientInfoDto clientInfo(Long clientNo){
        Optional<Client> findClient = clientRepository.findById(clientNo);
        Client client = findClient.get();
        return new ClientInfoDto(client);
    }

    //고객 상담리스트
    public List<ClientCounListDto> counselingList(Long clientNo){
        Optional<Client> findClient = clientRepository.findById(clientNo);
        Client client = findClient.get();
        List<Room> clientList = client.getRooms();
        return clientList.stream()
                .map(o->new ClientCounListDto(o))
                .collect(Collectors.toList());
    }

    //고객 상담내역
    public List<CounContentsDto> counContents(Long roomNo){
        Optional<Room> findRoom = roomRepository.findById(roomNo);
        Room room = findRoom.get();
        List<Message> messages = room.getMessages();
        return messages.stream()
                .map(o-> new CounContentsDto(o))
                .collect(Collectors.toList());
    }

    //회사 이름
    public String getCorpName(Long roomNo){
        Optional<Room> findRoom = roomRepository.findById(roomNo);
        Room room = findRoom.get();
        String corpName = room.getCounselor().getCorporation().getCorpName();
        return corpName;
    }

    // 고객정보 수정
    @Transactional
    public void updateClient(Long clientNo, ClientEditDto editDto) {
        Client client = clientRepository.findById(clientNo).orElseThrow(
                ()->new NullPointerException("접근 오류"));

        client.setClientStatus(editDto.getClient_status());
    }
}
