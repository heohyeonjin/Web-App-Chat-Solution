package chatsolution.web.message.repository;

import chatsolution.web.client.model.Client;
import chatsolution.web.message.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
  
    List<Room> findAllByCounselor_CounNoOrderByModifiedAtDesc(Long counNo);
    Room findTopByCounselor_CounNoOrderByRoomNoDesc(Long counNo);
    List<Room> findAllByClient_ClientNoOrderByModifiedAtDesc(Long clientNo);

    List<Room> findAllByCounselor_Corporation_CorpNo(Long corpNo);

}