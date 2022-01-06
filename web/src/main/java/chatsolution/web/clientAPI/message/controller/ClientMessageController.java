package chatsolution.web.clientAPI.message.controller;


import chatsolution.web.client.model.Client;
import chatsolution.web.clientAPI.corporation.service.ClientCorpService;
import chatsolution.web.clientAPI.message.dto.EnterDto;
import chatsolution.web.clientAPI.message.dto.MessageSendDto;
import chatsolution.web.clientAPI.message.service.ClientMessageService;
import chatsolution.web.clientAPI.message.service.ClientRoomService;
import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.counselor.model.Counselor;
import chatsolution.web.message.dto.MessageListDto;
import chatsolution.web.message.model.Room;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientMessageController {

    private final ClientMessageService clientMessageService;
    private final ClientRoomService clientRoomService;
    private final ClientCorpService clientCorpService;

    @GetMapping("/messages/{roomNo}") // 채팅 방 안 메시지 내역
    public List<MessageListDto> messageList(@PathVariable Long roomNo){
        List<MessageListDto> messages = clientMessageService.messageList(roomNo);
        Room room = clientRoomService.findRoom(roomNo);
        clientMessageService.msgReadProcess(room); // 읽음 처리
        return messages;
    }

   @GetMapping("/corporation/enter/{corpNo}") // 방 생성 유무 확인 (채팅 리스트 띄울 용도)
   public EnterDto enterCorp(@PathVariable Long corpNo, HttpServletRequest request){
        Client client = clientMessageService.getClient(request.getSession());
        Long roomNo = clientMessageService.checkRoom(client,corpNo);
        String corpName = clientMessageService.getCorpName(corpNo);
        //roomNo랑 corpName 반환
        if(roomNo!=0L){
            return new EnterDto(roomNo,corpName,corpNo);
        }
        else{
            return new EnterDto(0L,corpName,corpNo);
        }
   }

    // 기업 선택 -> 메시지 하나 보내면 방 생성
    @PostMapping("/message/{corpNo}") // 메시지 전송 및 방 생성
    public Long sendMsg(@RequestBody MessageSendDto clientMessageSendDto , @PathVariable Long corpNo, HttpServletRequest request){
        Client client = clientMessageService.getClient(request.getSession()); //고객 정보
        Long checkRoom = clientMessageService.checkRoom(client,corpNo); //방 존재 유무 확인

        if(checkRoom!=0L){ // 존재 하면 해당 방 안에 메시지 리스트 추가
            clientMessageService.addMessage(checkRoom,clientMessageSendDto);
            log.info(clientMessageSendDto.getContent());
            return checkRoom;
        }
        else{
            Corporation corp = clientCorpService.corpFind(corpNo); // 기업에 상담원이 존재하지 않는 경우
            if(corp.getCounselors().size()==0){
                return 0L;
            }
            Counselor counselor = clientMessageService.CounselorMatching(corpNo);

            Long roomNo = clientRoomService.createRoom(client, counselor); // 방 생성
            clientMessageService.addMessage(roomNo,clientMessageSendDto); // 메시지 추가

            log.info(clientMessageSendDto.getContent()+"방 생성");
            return roomNo;
        }
    }
}
