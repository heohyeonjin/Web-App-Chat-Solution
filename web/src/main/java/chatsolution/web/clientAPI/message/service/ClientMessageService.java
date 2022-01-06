package chatsolution.web.clientAPI.message.service;


import chatsolution.web.client.model.Client;
import chatsolution.web.client.repository.ClientRepository;
import chatsolution.web.clientAPI.message.dto.MessageSendDto;
import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.corporation.repository.CorpRepository;
import chatsolution.web.fcm.service.FirebaseCloudMessageService;
import chatsolution.web.counselor.model.Counselor;
import chatsolution.web.counselor.repository.CounselorRepository;
import chatsolution.web.message.dto.MessageListDto;
import chatsolution.web.message.model.Message;
import chatsolution.web.message.model.Room;
import chatsolution.web.message.repository.MessageRepository;
import chatsolution.web.message.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientMessageService {

    private final RoomRepository roomRepository;
    private final ClientRepository clientRepository;
    private final MessageRepository messageRepository;
    private final CorpRepository corpRepository;
    private final FirebaseCloudMessageService fcmService;
    private final CounselorRepository counselorRepository;

    // 클라이언트 No 값
    public Client getClient(HttpSession session) {
        Long clientNo = (Long) session.getAttribute("clientNo");
        return clientRepository.findByClientNo(clientNo);
    }

    //채팅 리스트
    public List<MessageListDto> messageList(Long roomNo) {
        Optional<Room> findroom = roomRepository.findById(roomNo);
        Room room = findroom.get();

        return room.getMessages().stream()
                .map(o -> new MessageListDto(o))
                .collect(Collectors.toList());
    }


    // 메시지 추가
    @Transactional
    public String addMessage(Long roomNo, MessageSendDto clientMessageSendDto) {
        Optional<Room> findRoom = roomRepository.findById(roomNo);
        Room room = findRoom.get();
        Message newMsg = new Message(clientMessageSendDto, room);
        messageRepository.save(newMsg);
        room.getMessages().add(newMsg);
        room.setMsgSize(room.getMsgSize() + 1);
        return newMsg.getMsgContent();
    }

    // 메시지 읽음 처리
    @Transactional
    public void msgReadProcess(Room room) {
        List<Message> msg = room.getMessages();
        for (int i = 0; i < msg.size(); i++) {
            if (msg.get(i).getClientRead() == 0) {
                msg.get(i).setClientRead(1);
            }
        }
    }

    //토큰 저장
    @Transactional
    public void saveToken(Client client, String token) {
        client.setFcmToken(token);

    }


    // 방 존재 유무 체크
    public Long checkRoom(Client client, Long corpNo) {
        List<Room> room = client.getRooms();
        for (int i = 0; i < room.size(); i++) {
            if (corpNo == room.get(i).getCounselor().getCorporation().getCorpNo()) {
                room.get(i).getRoomNo();
                return room.get(i).getRoomNo();
            }
        }
        return 0L;
    }

    // 회사 이름 가져오기
    public String getCorpName(Long corpNo) {
        Optional<Corporation> findCorp = corpRepository.findById(corpNo);
        Corporation corp = findCorp.get();
        return corp.getCorpName();
    }

    // 메세지 수신 확인
    @Transactional
    public void updateMsgStatus(Long msgNo) {
        Optional<Message> findmsg = messageRepository.findById(msgNo);
        Message message = findmsg.get();
        message.setMsgStatus(1); // 수신 확인
    }

    //fcm 재전송
    public void reSendMessage(Client client) throws IOException {
        List<Room> rooms = client.getRooms();
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            int msgSize = room.getMsgSize();
            Message lastMsg = room.getMessages().get(msgSize - 1);
            log.info("메세지 수신 상태" + lastMsg.getMsgStatus());
            if (lastMsg.getMsgStatus() == 0) { // 미 수신 메세지라면
                String token = client.getFcmToken();
                Corporation corporation = room.getCounselor().getCorporation();
                String title = corporation.getCorpName();
                fcmService.sendMessageTo(token, title, lastMsg.getMsgContent(), room.getRoomNo().toString(), lastMsg.getMsgNo().toString());
            }
        }
    }

    // 상담원 매칭 시스템
    @Transactional
    public Counselor CounselorMatching(Long corpNo){
        List<Counselor> counselors = counselorRepository.findAllByCorporationCorpNoOrderByMatchingAsc(corpNo);

        for(int i = 0 ;i<counselors.size();i++){
            log.info(counselors.get(i).getCounName() + "의 상담 고객 수: " + counselors.get(i).getRooms().size());
            log.info(counselors.get(i).getCounName() + "의 방 사이즈: " + counselors.get(i).getMatching());
        }

        Counselor matchCoun = counselors.get(0);
        matchCoun.setMatching(matchCoun.getMatching() + 1);
        log.info("매칭된 상담원 :" + matchCoun.getCounName());
        return matchCoun;
    }
}
