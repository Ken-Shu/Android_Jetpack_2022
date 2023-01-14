package com.test.app_paging_2.api;


import com.test.app_paging_2.model.BookApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET(value = "search/{query}/{page}")
    Call<BookApiResponse> getBooks(
        @Path("query")String query, @Path("page")int page
    );
}
