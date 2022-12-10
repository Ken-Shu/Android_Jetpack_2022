package com.test.app_room_live_data_viewmodel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.github.javafaker.Faker;
import com.test.app_room_live_data_viewmodel.adapter.StudentAdapter;
import com.test.app_room_live_data_viewmodel.database.MyDatabase;
import com.test.app_room_live_data_viewmodel.entity.Student;
import com.test.app_room_live_data_viewmodel.viewmodel.StudentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //private MyDatabase myDatabase;
    private StudentViewModel studentViewModel;
    private List<Student> students;
    private StudentAdapter studentAdapter;
    private Context context;
    private Faker faker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        faker = new Faker();

        ListView listStudent = findViewById(R.id.listStudent);
        students = new ArrayList<>();
        studentAdapter = new StudentAdapter(context, students);
        listStudent.setAdapter(studentAdapter);

        //myDatabase = MyDatabase.getInstance(context);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getLiveDataStudent().observe(this, (students) ->{
            // 當student 資料表紀錄有變更時要做的事情
            this.students.clear();
            this.students.addAll(students);
            studentAdapter.notifyDataSetChanged();
        });
        // Add button
        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            /*
            Student student = new Student(faker.name().firstName(), new Random().nextInt(20)+20);
            //studentViewModel.getMyDatabase().studentDao().insert(student);
            new AccessRoomTask(student).execute("add");
            */

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            // Layout
            View dialogLayout = layoutInflater.inflate(R.layout.dialog_layout_student, null);
            // View items
            EditText etName = dialogLayout.findViewById(R.id.etName);
            EditText etAge = dialogLayout.findViewById(R.id.etAge);
            etName.setText(faker.name().firstName());
            etAge.setText(""+(new Random().nextInt(20)+20));

            // AlertDialog 設定
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Add Student");
            dialog.setView(dialogLayout);
            dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Student student = new Student();
                    student.name = etName.getText().toString();
                    student.age = Integer.valueOf(etAge.getText().toString());
                    new AccessRoomTask(student).execute("add");
                }
            });
            dialog.setNegativeButton("Cancel", null);
            dialog.create().show();
        });

        // update
        listStudent.setOnItemClickListener((adapterView, view, position, id) -> {
            /*
            Student student = students.get(position);
            student.name = faker.name().firstName();
            student.age = new Random().nextInt(20)+ 20;
            //studentViewModel.getMyDatabase().studentDao().update(student);
            new AccessRoomTask(student).execute("update");
            */
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            // Layout
            View dialogLayout = layoutInflater.inflate(R.layout.dialog_layout_student, null);
            // View items
            Student student = students.get(position);
            EditText etName = dialogLayout.findViewById(R.id.etName);
            EditText etAge = dialogLayout.findViewById(R.id.etAge);
            etName.setText(student.name);
            etAge.setText(""+(student.age));

            // AlertDialog 設定
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Update Student");
            dialog.setView(dialogLayout);
            dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Student student = students.get(position);
                    student.name = etName.getText().toString();
                    student.age = Integer.valueOf(etAge.getText().toString());
                    new AccessRoomTask(student).execute("update");
                }
            });
            dialog.setNegativeButton("Cancel", null);
            dialog.create().show();
        });

        // Delete
        listStudent.setOnItemLongClickListener((adapterView, view, position, id) -> {
            //studentViewModel.getMyDatabase().studentDao().delete(students.get(position));
            Student student = students.get(position);
            new AccessRoomTask(student).execute("delete");
            return true;
        });
    }

    //Add/Delete/Update Thread Task
    //AsyncTask<傳進參數, 進度狀態, 實作資料回傳(result)>
    private class AccessRoomTask extends AsyncTask<String, Void, Void> {
        private Student student;
        AccessRoomTask(Student student){
            this.student = student;
        }
        @Override
        protected Void doInBackground(String... mode) {
            switch (mode[0]){
                case "add":
                    studentViewModel.getMyDatabase().studentDao().insert(student);
                    break;
                case "update":
                    studentViewModel.getMyDatabase().studentDao().update(student);
                    break;
                case "delete":
                    studentViewModel.getMyDatabase().studentDao().delete(student);
                    break;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(context,"Room Access OK! ",Toast.LENGTH_SHORT).show();
        }
    }

}