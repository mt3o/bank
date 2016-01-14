package pl.training.bank.transferobject;

import java.util.List;

public class PageTO<T> {

    private int totalPages;
    private List<T> data;

    public PageTO() {
    }

    public PageTO(int totalPages, List<T> data) {
        this.totalPages = totalPages;
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
