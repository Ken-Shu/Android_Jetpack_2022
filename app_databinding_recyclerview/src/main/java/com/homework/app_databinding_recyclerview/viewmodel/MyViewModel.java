package com.homework.app_databinding_recyclerview.viewmodel;

import com.homework.app_databinding_recyclerview.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel {

    public List<Book> getBooks(){
        String bookpath = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQUFiRqO-ID4CL0l5CwEaM_8jbyLyhNIFPdmpPx_NDiiGmOA_nZpoeCxgXZB0Rgl0L8WlpvKCCecyPz5jIUpV0ceVPavIOK1BpGOdW3OD-1&usqp=CAc";
        List<Book> books = new ArrayList<>();
        for(int i =0 ; i<100 ; i++){
            Book book = new Book("Java" + i, "Art", bookpath);
            books.add(book);
        }
        return books;
    }
}
