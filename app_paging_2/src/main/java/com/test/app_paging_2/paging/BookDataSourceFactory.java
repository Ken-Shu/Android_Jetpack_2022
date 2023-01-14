package com.test.app_paging_2.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.test.app_paging_2.model.Book;

public class BookDataSourceFactory extends DataSource.Factory {

    //將數據原轉換成具有分頁功能的物件
    private MutableLiveData<PageKeyedDataSource<Integer, Book>> bookPageKeyedDataSource = new MutableLiveData<>();
    @Override
    public DataSource create() {
        BookDataSource bookDataSource = new BookDataSource();
        bookPageKeyedDataSource.postValue(bookDataSource);
        return bookDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Book>> getBookPageKeyedDataSource(){
        return bookPageKeyedDataSource;
    }
}
