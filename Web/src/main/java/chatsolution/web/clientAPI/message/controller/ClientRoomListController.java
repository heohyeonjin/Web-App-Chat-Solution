package chatsolution.web.clientAPI.message.controller;


import chatsolution.web.clientAPI.auth.model.Client;
import chatsolution.web.clientAPI.message.dto.RoomListDto;
import chatsolution.web.corporation.repository.CorpRepository;
import chatsolution.web.clientAPI.message.service.ClientMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientRoomListController {
    private final ClientMessageService messageClientService;
    private final CorpRepository corpRepository;

    //채팅방 리스트
    @GetMapping("/api/rooms")
    public List<RoomListDto> roomList(HttpServletRequest request){
        Client client = messageClientService.getClient(request.getSession());
        return client.getRooms().stream()
                .map(o -> new RoomListDto(o))
                .collect(Collectors.toList());
    }

}