package com.test.app_paging_2;

import static com.test.app_paging_2.MainActivity.SEARCH_TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.app_paging_2.paging.BookListViewModel;
import com.test.app_paging_2.paging.BookPageListAdapter;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookListViewModel bookListViewModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        context = this;

        //Title 中顯示 <- 返回箭頭
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // 取得MainActivity 傳來的資料
        String queryText = null;
        Intent intent = getIntent();
        if(intent.hasExtra(SEARCH_TAG)){
            // 取的傳來的參數
            queryText = intent.getStringExtra(SEARCH_TAG);

            Toast.makeText(context,queryText,Toast.LENGTH_LONG).show();
        }

        //配置 recyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        //適配器
        BookPageListAdapter adapter = new BookPageListAdapter(context);
        recyclerView.setAdapter(adapter);
        // ViewModel
        bookListViewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        bookListViewModel.setSearchQuery(queryText);
        bookListViewModel.getBookPageList().observe(this, (books)->{
            adapter.submitList(books);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}