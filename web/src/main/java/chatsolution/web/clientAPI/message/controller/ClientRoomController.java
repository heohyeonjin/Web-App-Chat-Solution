package chatsolution.web.clientAPI.message.controller;


import chatsolution.web.client.model.Client;
import chatsolution.web.clientAPI.message.dto.ClientRoomListDto;
import chatsolution.web.clientAPI.message.service.ClientMessageService;
import chatsolution.web.message.model.Room;
import chatsolution.web.message.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientRoomController {
    private final ClientMessageService messageClientService;
    private final RoomRepository roomRepository;

    //채팅방 리스트
    @GetMapping("/rooms")
    public List<ClientRoomListDto> roomList(HttpServletRequest request){
        Client client = messageClientService.getClient(request.getSession());
        List<Room> room =roomRepository.findAllByClient_ClientNoOrderByModifiedAtDesc(client.getClientNo());
        return room.stream()
                .map(o -> new ClientRoomListDto(o))
                .collect(Collectors.toList());
    }

}
