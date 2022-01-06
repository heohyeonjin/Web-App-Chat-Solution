package chatsolution.web.counselor.model;

import chatsolution.web.corporation.model.Corporation;
import chatsolution.web.corporation.model.TimeStamped;
import chatsolution.web.counselor.dto.CounRegDto;
import chatsolution.web.message.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Counselor extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counNo;       // 상담원 일련번호

    @Column(nullable = false)
    private String counId;     // 상담원 아이디

    @Column(nullable = false)
    private String counPw;     // 상담원 비밀번호

    @Column(nullable = false)
    private int counStatus;    // 상담원 상태

    @Column(nullable = false)
    private String counName;   // 상담원 이름

    private String counPhone;  // 상담원 연락처

    private String counEmail;  // 상담원 이메일

    private String counImage;  // 상담원 사진

    private int counGender;    // 상담원 성별

    private int matching;      // 상담원과 현재 매칭된 고객 수

    @OneToMany(mappedBy = "counselor")
    private List<Room> rooms;

    @ManyToOne
    @JoinColumn
    private Corporation corporation;    // 상담원 소속

    // 연관관계 편의 메서드
    public void setCorporation(Corporation corporation) {
        if (this.corporation != null) {
            this.corporation.removeCounselor(this);
        }
        this.corporation = corporation;
        corporation.addCounselor(this);
    }

    public Counselor(CounRegDto counRegDto, Corporation corp){
        this.counGender = counRegDto.getCoun_gender();
        setCorporation(corp);
        this.counName = counRegDto.getCoun_name();
        this.counId = counRegDto.getCoun_id();
        this.counPw = counRegDto.getCoun_pw();
        this.counPhone = counRegDto.getCoun_phone();
        this.counEmail = counRegDto.getCoun_email();
        this.counStatus=1;
    }

}

