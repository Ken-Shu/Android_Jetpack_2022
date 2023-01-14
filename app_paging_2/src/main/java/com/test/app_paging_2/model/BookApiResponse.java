package com.test.app_paging_2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookApiResponse {
    @Expose
    @SerializedName(value = "error")
    private String error;

    @Expose
    @SerializedName(value = "total")
    private String total;

    @Expose
    @SerializedName(value = "page")
    private String page;

    @Expose
    @SerializedName(value = "books")
    private List<Book> books;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
