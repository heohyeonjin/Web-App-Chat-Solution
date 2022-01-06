package chatsolution.web.message.repository;

import chatsolution.web.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findTopByRoom_RoomNoOrderByMsgNoDesc(Long roomNo);
    Message findTopByOrderByMsgNoDesc(); // 가장 최근 메세지
}
