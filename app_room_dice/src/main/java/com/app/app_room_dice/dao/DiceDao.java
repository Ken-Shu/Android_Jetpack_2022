package com.app.app_room_dice.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.app_room_dice.entity.Dice;

import java.util.List;

@Dao
public interface DiceDao {

    @Insert
    public long insert(Dice dice);

    @Query(value = "select id, d1, d2, d3, sum from dice d")
    public List<Dice> queryAll();

    @Query(value = "select id, d1, d2, d3, sum from dice d where id = :id")
    public Dice getDice(long id);

    @Update(onConflict = OnConflictStrategy.REPLACE) // 若資料發生衝突 則 Replace
    public void update(Dice dice);

    @Delete
    public void delete(Dice dice);
}
