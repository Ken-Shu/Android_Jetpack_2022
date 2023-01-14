package com.test.app_paging_boundry.paging;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.test.app_paging_boundry.db.UserDataBase;
import com.test.app_paging_boundry.model.User;

public class UserViewModel extends AndroidViewModel {
    private UserDataBase dataBase;
    public LiveData<PagedList<User>> userPagedList;

    public UserViewModel(@NonNull Application application) {
        super(application);
        dataBase = UserDataBase.getInstance(application);
        int pageSize = UserBoundaryCallback.PER_PAGE;
        userPagedList = new LivePagedListBuilder<>(dataBase.userDao().getUserList(), pageSize)
                .setBoundaryCallback(new UserBoundaryCallback(application))
                .build();
    }
    // 刷新數據
    public void refresh(){
        AsyncTask.execute(()->{
            //清除資料表內容
            dataBase.userDao().clear();
        });
    }
}
