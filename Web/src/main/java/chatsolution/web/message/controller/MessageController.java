package chatsolution.web.message.controller;

import chatsolution.web.message.dto.MessageDto;
import chatsolution.web.message.dto.MessageListDto;
import chatsolution.web.message.dto.NewMessageDto;
import chatsolution.web.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/counseling")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public String roomList(Model model) {
        return "chat/chat_list";
    }

//    @GetMapping
//    public String chatList(Model model){
//        List<MessageListDto> messages = messageService.msgList();
//        model.addAttribute("messages", messages);
//        return "chat/chat_room";
//    }

    @PostMapping("/send")
    public @ResponseBody String msgSend(NewMessageDto newMessageDto) {
        log.info("전달받은 메세지: " + newMessageDto.getMsg());
        messageService.saveMsg(newMessageDto);
        String returnValue = "true";
        return returnValue;
    }

}
