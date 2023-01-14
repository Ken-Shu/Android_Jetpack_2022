package com.test.app_paging_2.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.test.app_paging_2.api.RetrofitClient;
import com.test.app_paging_2.model.Book;
import com.test.app_paging_2.model.BookApiResponse;
import com.test.app_paging_2.model.Query;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 當頁 Book 的資料集
public class BookDataSource extends PageKeyedDataSource<Integer, Book> {

    public static final int FIRST_PAGE = 1;

    // 載入 Book 的資料集要做的事情
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Book> callback) {
        // 初始化
        RetrofitClient.getApiClient().getApiInterface().getBooks(Query.getSearchQueryStr(), FIRST_PAGE)
                .enqueue(new Callback<BookApiResponse>() {
                    @Override
                    public void onResponse(Call<BookApiResponse> call, Response<BookApiResponse> response) {
                        // 成功
                        if(response.body() != null){
                            callback.onResult(response.body().getBooks(), null,FIRST_PAGE+1);
                            Log.i("BookDataSource loadInitial","FIRST_PAGE = " + FIRST_PAGE);
                        }
                    }

                    @Override
                    public void onFailure(Call<BookApiResponse> call, Throwable t) {
                        // 失敗
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Book> callback) {
        // 載入前要做的事情
        // nothing
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Book> callback) {
        RetrofitClient.getApiClient().getApiInterface().getBooks(Query.getSearchQueryStr(),params.key)// query, page
                .enqueue(new Callback<BookApiResponse>() {
                    @Override
                    public void onResponse(Call<BookApiResponse> call, Response<BookApiResponse> response) {
                        if(response.body() != null){
                            int keyPage = params.key+1; // 指到下一頁
                            // 將數據返回給 PageList
                            // PagedList = null 表示沒有下一頁 數據請求完畢
                            callback.onResult(response.body().getBooks(), keyPage);
                            Log.i("BookDataSource loadAfter","params.key = " + params.key);
                        }
                    }

                    @Override
                    public void onFailure(Call<BookApiResponse> call, Throwable t) {

                    }
                });
    }
}
