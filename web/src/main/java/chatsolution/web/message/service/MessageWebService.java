package chatsolution.web.message.service;

import chatsolution.web.counselor.model.Counselor;
import chatsolution.web.counselor.repository.CounselorRepository;
import chatsolution.web.message.dto.*;
import chatsolution.web.message.model.Message;
import chatsolution.web.message.model.Room;
import chatsolution.web.message.repository.MessageRepository;
import chatsolution.web.message.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageWebService {

    private final CounselorRepository counselorRepository;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    // 채팅방 리스트 띄우기
    public List<RoomListDto> roomList(Long counNo){
        List<Room> existRoom = roomRepository.findAllByCounselor_CounNoOrderByModifiedAtDesc(counNo);
        return existRoom.stream()
                .map(o -> new RoomListDto(o))
                .collect(Collectors.toList());
    }

    // 상담원 이름 얻기
    public Optional<Counselor> getCounName(Long counNo) {
        return counselorRepository.findById(counNo);
    }

    // 채팅방 내 상담원/방 정보 얻기
    public RoomInfoDto getRoomInfo(Long roomNo) {
        Optional<Room> room = roomRepository.findById(roomNo);

        return new RoomInfoDto(room.get().getClient().getClientName(), roomNo);
    }

    // 채팅방 내 존재하는 메세지 띄우기
    @Transactional
    public List<MessageListDto> msgList(Long counNo, Long roomNo){

        // 먼저 counNo로 counselor 찾기 -> counselor room에서 roomNo 찾기
        Optional<Counselor> coun = counselorRepository.findById(counNo);
        if (coun.isEmpty()) return null;

        Room room = new Room();
        List<Room> rooms = coun.get().getRooms();
        for (Room value : rooms) {
            if (value.getRoomNo() == roomNo)
                room = value;
        }
        
        List<Message> existMsg = room.getMessages();

        // 상담원 읽음 처리
        for (Message message : existMsg) {
            message.setCounRead(1);
        }

        return existMsg.stream()
                .map(o -> new MessageListDto(o))
                .collect(Collectors.toList());
    }

    // 상담원이 전송한 메세지 저장
    @Transactional
    public void saveMsg(NewMessageDto newMessageDto, Long roomNo) {
        Room room = roomRepository.findById(roomNo).orElseThrow(
                ()->new NullPointerException("접근 오류"));
        Message newMsg = new Message(newMessageDto, room);
        messageRepository.save(newMsg);

        List<Message> newMessages = room.getMessages();
        newMessages.add(newMsg);
        room.setMessages(newMessages);
        room.setMsgSize(room.getMsgSize() + 1);   // DB modifiedAt 변경용

        log.info("상담원 메세지 저장 성공");
    }

    public Room getRoom(Long roomNo){
        Optional<Room> findRoom = roomRepository.findById(roomNo);
        Room room = findRoom.get();
      return room;
    }

    // 방금 보낸 메세지 번호
    public Long getLastMsgNo() {
        Message message = messageRepository.findTopByOrderByMsgNoDesc();
        Long msgNo = message.getMsgNo();
        return msgNo;
    }

    // 상담원과 매칭된 고객 수 줄이기
    @Transactional
    public void decreaingMatching(Long counNo){
        Optional<Counselor> counselor = counselorRepository.findById(counNo);
        counselor.get().setMatching(counselor.get().getMatching() - 1);
    }

}
