package chatsolution.web.message.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class RoomInfoDto {
    private String name;
    private Long roomNo;

    public RoomInfoDto(String name, Long roomNo) {
        this.name = name;
        this.roomNo = roomNo;
    }
}
