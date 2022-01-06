package chatsolution.web.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ClientPages {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int startPage;
    private int endPage;

    public ClientPages(int size, int clientPage) {
        this.pageNumber = clientPage;
        this.pageSize = 15;
        this.totalPages = size;
        this.startPage = 0;
        this.endPage = size - 1;
    }
}
