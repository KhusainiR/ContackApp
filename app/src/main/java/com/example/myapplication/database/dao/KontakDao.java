package com.example.myapplication.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.database.entity.Kontak;

import java.util.List;
@Dao
public interface KontakDao {

    @Query("SELECT * FROM kontak")
    List<Kontak> getAll();

    @Insert
    void insertData(Kontak kontak);

    @Query("SELECT * FROM kontak WHERE id LIKE :kontakId LIMIT 1")
    Kontak findById(int kontakId);

    @Update
    void update(Kontak kontak);

    @Delete
    void delete(Kontak kontak);
}
