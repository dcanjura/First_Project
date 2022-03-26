package demo.pagination;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class pageRender<T> {
    private String url;
    private Page<T> page;
    private int totalPages;
    private int elementsPage;
    private int currentPage;
    private List<pageItem> pages;

    public pageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<pageItem>();

        elementsPage = 5;
        totalPages = page.getTotalPages();
        currentPage = page.getNumber() + 1;

        int from, to;
        if(totalPages <= elementsPage){
            from = 1;
            to = totalPages;
        }
        else{
            if(currentPage <= elementsPage){
                from = 1;
                to = elementsPage;
            }
            else if(currentPage >= totalPages - (elementsPage / 2)){
                from = totalPages - elementsPage + 1;
                to = elementsPage;
            }
            else{
                from = currentPage - (elementsPage / 2);
                to = elementsPage;
            }
        }

        for(int i = 0; i < to; i++){
            pages.add(new pageItem(from + i, currentPage == from + i));
        }
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean itHasNext(){
        return page.hasNext();
    }

    public boolean itHasPrevious(){
        return page.hasPrevious();
    }
}
