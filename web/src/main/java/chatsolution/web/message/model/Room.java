package chatsolution.web.message.model;


import chatsolution.web.client.model.Client;
import chatsolution.web.corporation.model.TimeStamped;
import chatsolution.web.counselor.model.Counselor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
@Entity
public class Room extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNo; // 방 일련번호

    @ManyToOne
    @JoinColumn
    private Client client; //고객 외래키

    @ManyToOne //상담원 외래키
    @JoinColumn
    private Counselor counselor;

    private int msgSize;

    @OneToMany(mappedBy="room")
    private List<Message> messages; //메시지들

    public Room(Client client, Counselor counselor){
        this.setClient(client);
        this.setCounselor(counselor);
        this.msgSize=0;
        this.messages = new ArrayList<>();
    }

}
