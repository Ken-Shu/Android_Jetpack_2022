package com.homework.app_stock_fastapi.api;

import com.homework.app_stock_fastapi.model.Stock;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET(value = "bfp/get/{symbol}")
    Call<Stock> getSymbol(@Path("symbol")String symbol);

    @GET(value = "bfp/query/all")
    Call<List<Stock>> queryAll();

}
