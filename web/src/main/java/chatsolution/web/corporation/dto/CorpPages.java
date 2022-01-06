package chatsolution.web.corporation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CorpPages {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int startPage;
    private int endPage;

    public CorpPages(int size, int corpPage) {
        this.pageNumber = corpPage;
        this.pageSize = 15;
        this.totalPages = size;
        this.startPage = 0;
        this.endPage = size - 1;
    }
}
