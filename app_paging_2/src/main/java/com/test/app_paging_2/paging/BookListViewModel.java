package com.test.app_paging_2.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.test.app_paging_2.model.Book;
import com.test.app_paging_2.model.Query;

public class BookListViewModel extends ViewModel {
    private LiveData<PagedList<Book>> bookPageList;

    public BookListViewModel(){
        // 資料工廠
        BookDataSourceFactory dataSourceFactory = new BookDataSourceFactory();
        // PageList.Config 的設定配置
        PagedList.Config  config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true) // 佔位區間(預設 true)
                .setPageSize(10)
                .setPrefetchDistance(5) // 離底端 5 筆時要跑下一頁
                .build();
        bookPageList = new LivePagedListBuilder<>(dataSourceFactory,config).build();
    }

    public LiveData<PagedList<Book>> getBookPageList() {
        return bookPageList;
    }

    // 將查詢內容存放到 Query 類別屬性中
    public void setSearchQuery(String searchQuery){
        Query.setSearchQueryStr(searchQuery);
    }
}
