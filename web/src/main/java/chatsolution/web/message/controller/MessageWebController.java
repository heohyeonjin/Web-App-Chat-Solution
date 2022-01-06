package chatsolution.web.message.controller;

import chatsolution.web.client.model.Client;
import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.counselor.model.Counselor;
import chatsolution.web.fcm.service.FirebaseCloudMessageService;
import chatsolution.web.message.dto.*;
import chatsolution.web.message.model.Message;
import chatsolution.web.message.model.Room;
import chatsolution.web.message.service.MessageWebService;
import com.google.api.Http;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/counseling")
@RequiredArgsConstructor
public class MessageWebController {

    private final MessageWebService messageWebService;
    private final FirebaseCloudMessageService fcmService;

    // 채팅방 목록
    @GetMapping
    public String roomList(Model model, HttpServletRequest request) {

        Long enter = (Long)request.getSession().getAttribute("counNo");
        log.info("상담원 페이지 진입");

        Optional<Counselor> counselor = messageWebService.getCounName(enter);
        List<RoomListDto> rooms = messageWebService.roomList(enter);
        model.addAttribute("counNo", enter);
        model.addAttribute("counName", counselor.get().getCounName());
        model.addAttribute("rooms", rooms);
        return "chat/chat_list";
    }

    // 채팅방 입장
    @GetMapping("/{roomNo}")
    public String enterRoom(@PathVariable Long roomNo, Model model, HttpServletRequest request) {

        Long enter = (Long)request.getSession().getAttribute("counNo");
        RoomInfoDto info = messageWebService.getRoomInfo(roomNo);
        List<MessageListDto> messages = messageWebService.msgList(enter, roomNo);

        model.addAttribute("info", info);
        model.addAttribute("messages", messages);
        return "chat/chat_room";
    }

    // web에서 메세지 전송
    @PostMapping("/{roomNo}/send")
    public @ResponseBody String msgSend(@PathVariable Long roomNo, NewMessageDto newMessageDto) throws IOException {
        // token, title, content , roomNo, msgNo

        log.info("전달받은 메세지: " + newMessageDto.getMsg()); // 메세지 내용
        messageWebService.saveMsg(newMessageDto, roomNo);

        Room room = messageWebService.getRoom(roomNo); // 방 번호
        Client client = room.getClient();
        String token = client.getFcmToken(); // 클라이언트 토큰 값

        Corporation corporation = room.getCounselor().getCorporation();
        String title = corporation.getCorpName(); // 기업 이름

        Long msgNo = messageWebService.getLastMsgNo();
        log.info("최근 메세지 번호 : "+msgNo);
        fcmService.sendMessageTo(token, title, newMessageDto.getMsg(), roomNo.toString(), msgNo.toString());
        return "success";
    }

    @PostMapping("/matching")
    public @ResponseBody String decMatching(HttpServletRequest request) {
        Long counselor = (Long)request.getSession().getAttribute("counNo");
        messageWebService.decreaingMatching(counselor);

        return "success";
    }
}
