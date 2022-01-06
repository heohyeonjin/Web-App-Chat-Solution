package chatsolution.web.counselor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CounPages {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int startPage;
    private int endPage;

    public CounPages(int size, int counPage) {
        this.pageNumber = counPage;
        this.pageSize = 15;
        this.totalPages = size;
        this.startPage = 0;
        this.endPage = size - 1;
    }
}
