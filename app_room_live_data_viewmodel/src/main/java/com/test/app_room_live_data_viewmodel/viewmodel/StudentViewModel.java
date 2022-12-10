package com.test.app_room_live_data_viewmodel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.test.app_room_live_data_viewmodel.database.MyDatabase;
import com.test.app_room_live_data_viewmodel.entity.Student;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private MyDatabase myDatabase;
    private LiveData<List<Student>> liveDataStudents;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        myDatabase = MyDatabase.getInstance(application);
        liveDataStudents = myDatabase.studentDao().queryAll();
    }
    public LiveData<List<Student>> getLiveDataStudent(){
        return liveDataStudents;
    }

    public MyDatabase getMyDatabase(){
        return myDatabase;
    }
}
