package demo.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class pageItem {

    private int number;
    private boolean current;

    public pageItem(int number, boolean current) {
        super();
        this.number = number;
        this.current = current;
    }
}
