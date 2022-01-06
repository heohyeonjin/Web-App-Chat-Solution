package chatsolution.web.message.controller;

import chatsolution.web.message.dto.MessageDto;
import chatsolution.web.message.dto.RoomListDto;
import chatsolution.web.message.model.Message;
import chatsolution.web.message.repository.MessageRepository;
import chatsolution.web.message.repository.RoomRepository;
import chatsolution.web.message.service.MessageWebService;
import chatsolution.web.message.service.PollingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/poll")
@RequiredArgsConstructor
public class PollingController {

    private final PollingService pollingService;
    private final MessageWebService messageWebService;
    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;

    // 채팅방 내 마지막 메세지 확인
    @GetMapping("/chat/lastMsg")
    public @ResponseBody Long checkLastMsg(@RequestParam("roomNo") Long roomNo) {
        return pollingService.checkLastMsg(roomNo);
    }

    //채팅방 폴링
    @GetMapping("/chat")
    public @ResponseBody List<MessageDto> newMessages(@RequestParam("lastMsg") Long lastMsg){
        return pollingService.updateMessage(lastMsg);
    }

    // 채팅방 리스트 내 마지막 채팅방 확인
    @GetMapping("/room/lastRoom")
    public @ResponseBody Long checkLastRoom(HttpServletRequest servletRequest) {
        Long enter = (Long)servletRequest.getSession().getAttribute("counNo");
        return pollingService.checklastRoom(enter);
    }

    // 채팅방 리스트 폴링
    @GetMapping("/room")
    public @ResponseBody List<RoomListDto> newRooms(@RequestParam("lastRoom") Long lastRoom) {
        Long enter = roomRepository.findById(lastRoom).get().getCounselor().getCounNo();
        return messageWebService.roomList(enter);

    }
}
