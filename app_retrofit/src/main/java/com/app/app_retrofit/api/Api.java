package com.app.app_retrofit.api;

import com.app.app_retrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    //GET http://10.0.2.2:3000/posts/1  手機要連接電腦 要透過 10.0.2.2
    //GET http://10.0.2.2:3000/posts
    //PUT http://10.0.2.2:3000/posts/1 + Json
    @GET(value = "/posts/{postId}")
    Call<Post> getPost(@Path("postId") Integer postId);

    @GET(value = "/posts/")
    Call<List<Post>> getPosts();

    @PUT(value = "/posts/{postId}")
    Call<Post> updatePost(@Path("postId")Integer postId, @Body Post post);
}
