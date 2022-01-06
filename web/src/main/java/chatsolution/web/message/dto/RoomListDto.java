package chatsolution.web.message.dto;

import chatsolution.web.message.model.Message;
import chatsolution.web.message.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Slf4j
@Getter @Setter
@NoArgsConstructor
public class RoomListDto {
    private Long roomNo;
    private String name;
    private String content;
    private String time;
    private int counRead;

    public RoomListDto(Room room) {
        int size = room.getMessages().size();
        Message last = room.getMessages().get(size - 1);

        this.roomNo = room.getRoomNo();
        this.name = room.getClient().getClientName();
        this.content = last.getMsgContent();

        // 오늘 날짜 구하기
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowDate = now.format(dateFormatter);

        // 다른 날짜일 경우
        if (!last.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE).equals(nowDate)) {
            this.time = last.getCreatedAt().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        else {
            int hour = last.getCreatedAt().getHour();
            if (hour >= 12) {
                if (hour == 12) hour = 12;
                else hour = hour - 12;
                this.time = "오후 " + hour + ":" + last.getCreatedAt().getMinute();
            } else {
                this.time = "오전 " + hour + ":" + last.getCreatedAt().getMinute();
            }
        }

        // 새로운 알림 있는지 확인
        this.counRead = room.getMessages().get(room.getMsgSize() - 1).getCounRead();
    }
}
