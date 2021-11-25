package chatsolution.web.clientAPI.message.controller;


import chatsolution.web.clientAPI.auth.model.Client;
import chatsolution.web.clientAPI.message.dto.RoomListDto;
import chatsolution.web.clientAPI.message.service.ClientMessageService;
import chatsolution.web.clientAPI.message.service.RoomService;
import chatsolution.web.message.model.Message;
import chatsolution.web.message.model.Room;
import chatsolution.web.message.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientRoomController {
    private final ClientMessageService messageClientService;
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    //채팅방 리스트
    @GetMapping("/rooms")
    public List<RoomListDto> roomList(HttpServletRequest request){
        Client client = messageClientService.getClient(request.getSession());
        List<Room> room =roomRepository.findAllByClient_ClientNoOrderByModifiedAtDesc(client.getClientNo());
        return room.stream()
                .map(o -> new RoomListDto(o))
                .collect(Collectors.toList());
    }

//    // 특정 기업의 채팅방 들어가기
//    @GetMapping("/{roomNo}")
//    public String enterRoom(@PathVariable Long roomNo){
//    Optional<Room> findRoom = roomRepository.findById(roomNo);
//    Room room = findRoom.get();
//    roomService.msgReadProcess(room); // 고객 읽음 처리
//    return "true";
//    }

}
