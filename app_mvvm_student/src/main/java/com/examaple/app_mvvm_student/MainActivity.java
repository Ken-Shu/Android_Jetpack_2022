package com.examaple.app_mvvm_student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.examaple.app_mvvm_student.adapter.RecyclerViewAdapter;
import com.examaple.app_mvvm_student.databinding.ActivityMainBinding;
import com.examaple.app_mvvm_student.model.Student;
import com.examaple.app_mvvm_student.viewmodel.StudentViewModel;
import com.github.javafaker.Faker;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private List<Student> students;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Faker faker;
    private ActivityMainBinding activityMainBinding;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        context = this;
        faker = new Faker();
        students = new ArrayList<>();

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerView.setHasFixedSize(true);

        recyclerViewAdapter = new RecyclerViewAdapter(students);
        activityMainBinding.recyclerView.setAdapter(recyclerViewAdapter);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.setAdapter(recyclerViewAdapter);
        studentViewModel.queryLiveDataStudents().observe(this , (List<Student> studentList) -> {
            Log.i("MyLog", "observer 觀察到資料異動");
            students.clear();
            students.addAll(studentList);
            recyclerViewAdapter.notifyDataSetChanged();
        });

        final SwipeRefreshLayout swipeRefresh = activityMainBinding.swipeRefresh;
        swipeRefresh.setOnRefreshListener(() -> {
            studentViewModel.refresh();
            swipeRefresh.setRefreshing(false); // 將 swipRefresh 旋鈕關閉
        });

        // ItemClick 發生 -> Update
        recyclerViewAdapter.setMyItemClickListener((position, view) ->{
            Student student = students.get(position);
            student.name = faker.name().lastName();
            student.age = new Random().nextInt(20) + 20;
            studentViewModel.updateStudent(student);
        });

        // ItemClick 發生 -> Delete
        recyclerViewAdapter.setMyItemLongClickListener((position, view) ->{
            Student student = students.get(position);
            studentViewModel.deleteStudent(student);
            studentViewModel.refresh();
        });
    }

    // 新增按鈕
    public void addButton(View view){
        int resId = context.getResources().getIdentifier("s" + (students.size()%10),"drawable",context.getPackageName());

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100 , byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        Student student = new Student(
                faker.name().lastName(), new Random().nextInt(20)+20 ,imageBase64
        );
        Log.i("MyLog", "新增一筆資料");
        studentViewModel.createStudent(student);
    }
}