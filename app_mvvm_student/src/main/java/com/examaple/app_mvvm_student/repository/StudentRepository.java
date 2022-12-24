package com.examaple.app_mvvm_student.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import com.examaple.app_mvvm_student.api.Api;
import com.examaple.app_mvvm_student.database.StudentDao;
import com.examaple.app_mvvm_student.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRepository {
    private StudentDao studentDao;

    private Api api;

    public StudentRepository(StudentDao studentDao,Api api){
        this.studentDao = studentDao;
        this.api = api;
    }

    public LiveData<Student> getStudent(int id){
        return studentDao.getById(id);
    }

    public LiveData<List<Student>> queryStudents(){
        return studentDao.queryAll();
    }

    public void addStudent(Student student){
        // 1. 透過 Api 遠端 insert
        api.addStudent(student).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                // 2. insert 成功之後 再將student insert 到 room 資料表中
                AsyncTask.execute(()->{
                    studentDao.insert(response.body());
                });
                Log.i("MyLog","新增成功" + this.getClass().getSimpleName());
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.i("MyLog","新增失敗" + this.getClass().getSimpleName());
            }
        });
    }

    public void updateStudent(Student student){
        api.updateStudent(student.id,student).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                // 2. insert 成功之後 再將student insert 到 room 資料表中
                AsyncTask.execute(()->{
                    studentDao.update(response.body());
                });
                Log.i("MyLog","修改成功" + this.getClass().getSimpleName());
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.i("MyLog","修改失敗" + this.getClass().getSimpleName());
            }
        });
    }

    public void deleteStudent(Student student){
        api.deleteStudent(student.id).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                // 2. insert 成功之後 再將student insert 到 room 資料表中
                AsyncTask.execute(()->{
                    studentDao.delete(response.body());
                });
                Log.i("MyLog","刪除成功" + this.getClass().getSimpleName());
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.i("MyLog","刪除失敗" + this.getClass().getSimpleName());
            }
        });
    }

    // 使用者下拉重整畫面時 更新 Swip
    public void refresh(){
        // 1. 透過 Api 遠端抓取最新資料
        api.queryStudents().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                AsyncTask.execute(()->{
                    // 2. 將最新資料複製給 room
                    for(Student student : students){
                        studentDao.insert(student);
                    }
                    // 3. 刪除遠端有 但是本地端沒有的資料(同步)
                    int [] ids = students.stream().mapToInt(student -> student.id).toArray(); // 遠端已經有的id
                    studentDao.deleteNotInId(ids);
                });
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.i("MyLog","查詢失敗" + this.getClass().getSimpleName());
            }
        });
    }
}
