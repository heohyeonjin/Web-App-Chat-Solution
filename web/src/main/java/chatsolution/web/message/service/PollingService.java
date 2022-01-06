package chatsolution.web.message.service;

import chatsolution.web.counselor.model.Counselor;
import chatsolution.web.counselor.repository.CounselorRepository;
import chatsolution.web.message.dto.MessageDto;
import chatsolution.web.message.dto.MessageListDto;
import chatsolution.web.message.dto.RoomInfoDto;
import chatsolution.web.message.dto.RoomListDto;
import chatsolution.web.message.model.Message;
import chatsolution.web.message.model.Room;
import chatsolution.web.message.repository.MessageRepository;
import chatsolution.web.message.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PollingService {

    private final CounselorRepository counselorRepository;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    // 채팅방 내 마지막 메세지 확인
    public Long checkLastMsg(Long roomNo) {
        Message message = messageRepository.findTopByRoom_RoomNoOrderByMsgNoDesc(roomNo);
        return message.getMsgNo();
    }

    // polling 통해 비동기적으로 메세지 띄우기
    @Transactional
    public List<MessageDto> updateMessage(Long lastMsg) {
        List<MessageDto> messages = new ArrayList<>();

        // webLastMsg -> Room 정보 찾기
        Message webLastMsg = messageRepository.findById(lastMsg).orElseThrow(
                ()->new NullPointerException("접근 오류"));;
        Room room = roomRepository.findById(webLastMsg.getRoom().getRoomNo()).orElseThrow(
                ()->new NullPointerException("접근 오류"));;

        // webLastMsg vs DBLastMsg
        int size = room.getMessages().size();
        Message DBLastMsg = room.getMessages().get(size - 1);
        if (DBLastMsg == webLastMsg) {
            return null;
        }

        // webLastMsg idx 파악
        int idx = 0;
        for (int i=0; i<size; i++) {
            if (room.getMessages().get(i) == webLastMsg) {
                idx = i;
            }
        }

        // 최신 update 메세지 리스트 저장
        for (int i=idx+1; i<size; i++) {
            room.getMessages().get(i).setCounRead(1);
            MessageDto recent = new MessageDto(room.getMessages().get(i));
            messages.add(recent);
        }

        return messages;
    }

    // 채팅목록 내 마지막 방 확인
    public Long checklastRoom(Long counNo) {
        Room last = roomRepository.findTopByCounselor_CounNoOrderByRoomNoDesc(counNo);
        return last.getRoomNo();
    }

    // polling 통해 비동기적으로 채팅방 리스트 띄우기
    public List<RoomListDto> updateRoom(Long lastRoom) {
        List <RoomListDto> rooms = new ArrayList<>();

        // webLastRoom -> counselor 정보 찾기
        Room webLastRoom = roomRepository.findById(lastRoom).orElseThrow(
                ()->new NullPointerException("접근 오류"));
        Counselor counselor = counselorRepository.findById(webLastRoom.getCounselor().getCounNo()).orElseThrow(
                ()->new NullPointerException("접근 오류"));

        // webLastRoom vs DBLastRoom
        int size = counselor.getRooms().size();
        if (size == 0) return null;
        Room DBLastRoom = counselor.getRooms().get(size - 1);
        if (DBLastRoom == webLastRoom) return null;

        // webLastRoom idx 파악
        int idx = 0;
        for (int i=0; i<size; i++) {
            if (counselor.getRooms().get(i) == webLastRoom) {
                idx = i;
            }
        }

        // 최신 update room list 저장
        for (int i=size-1; i>idx; i--) {
            if (counselor.getRooms().get(i).getMessages().size() == 0) return null;
            RoomListDto recent = new RoomListDto(counselor.getRooms().get(i));
            rooms.add(recent);
        }

        return rooms;
    }
}
