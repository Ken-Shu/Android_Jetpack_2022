package com.examaple.app_mvvm_student.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.examaple.app_mvvm_student.adapter.RecyclerViewAdapter;
import com.examaple.app_mvvm_student.api.Api;
import com.examaple.app_mvvm_student.api.RetrofitClient;
import com.examaple.app_mvvm_student.database.MyDatabase;
import com.examaple.app_mvvm_student.database.StudentDao;
import com.examaple.app_mvvm_student.model.Student;
import com.examaple.app_mvvm_student.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private MyDatabase myDatabase;
    private LiveData<List<Student>> liveDataStudents;
    private StudentRepository studentRepository;
    private RecyclerViewAdapter adapter;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        myDatabase = MyDatabase.getInstance(application);
        StudentDao studentDao = myDatabase.studentDao();
        Api api = RetrofitClient.getInstance().getApi();
        studentRepository = new StudentRepository(studentDao, api);
        liveDataStudents = myDatabase.studentDao().queryAll();
    }
    public void refresh(){
        studentRepository.refresh();
    }
    public void setAdapter(RecyclerViewAdapter adapter){
        this.adapter = adapter;
    }

    //新增
    public void createStudent(Student student){
        studentRepository.addStudent(student);
    }

    //修改
    public void updateStudent(Student student){
        studentRepository.updateStudent(student);
    }

    //刪除
    public void deleteStudent(Student student){
        studentRepository.deleteStudent(student);
    }

    //查詢
    public LiveData<List<Student>> queryLiveDataStudents(){
        return liveDataStudents;
    }
}
