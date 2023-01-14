package com.test.app_paging_boundry.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.test.app_paging_boundry.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "user_db";

    private static UserDataBase dataBaseInstance;

    public static synchronized UserDataBase getInstance(Context context){
        if(dataBaseInstance ==null){
            dataBaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), UserDataBase.class, DATABASE_NAME)
                    .build();
        }
        return dataBaseInstance;
    }

    public abstract UserDao userDao();
}
