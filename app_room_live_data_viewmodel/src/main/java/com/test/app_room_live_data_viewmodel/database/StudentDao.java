package com.test.app_room_live_data_viewmodel.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.test.app_room_live_data_viewmodel.entity.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query(value = "select id, name ,age FROM student WHERE id=:id")
    Student getById(int id);

    @Query(value = "select id, name ,age FROM student order by id desc")
    LiveData<List<Student>> queryAll();
}
