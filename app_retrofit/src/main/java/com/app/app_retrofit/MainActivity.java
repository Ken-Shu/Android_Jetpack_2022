package com.app.app_retrofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.app_retrofit.adapter.PostAdapter;
import com.app.app_retrofit.api.Api;
import com.app.app_retrofit.api.RetrofitClient;
import com.app.app_retrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Context context;
    private PostAdapter postAdapter;
    private ListView listView;
    private Api api;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = RetrofitClient.getInstance().getApi();
        context = this;
        listView = findViewById(R.id.list_view);
        postAdapter = new PostAdapter(context);
        listView.setAdapter(postAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
                try {
                    api.getPost((int) id).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            Post post = response.body();
                            // 對話視窗設定/建構
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("修改 title");
                            Log.i("retrofit", post.toString());
                            // 自訂 EditText
                            EditText et = new EditText(context);
                            et.setText(post.title);
                            builder.setView(et);
                            builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    post.title = et.getText().toString();
                                    try {
                                        api.updatePost(post.id, post).enqueue(new Callback<Post>() {
                                            @Override
                                            public void onResponse(Call<Post> call, Response<Post> response) {
                                                refresh();
                                                Toast.makeText(context, "Update OK", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(Call<Post> call, Throwable t) {
                                                Toast.makeText(context, "Update Error", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } catch (Exception e) {

                                    }
                                }
                            });
                            builder.setNegativeButton("離開", null);
                            // 建立對話視窗
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });

                } catch (Exception e) {
                }
                return true;
            }
        });


        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            refresh();
        });
    }
    private void refresh(){
        api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                runOnUiThread(() ->{
                    List<Post> postList = response.body();
                    postAdapter.setPostList(postList);
                    postAdapter.notifyDataSetChanged();
                });
                swipeRefreshLayout.setRefreshing(false); // 將旋轉圖示關閉
                Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false); // 將旋轉圖示關閉
            }
        });
    }
}