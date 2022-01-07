package com.example.myapplication.database.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.database.dao.KontakDao;
import com.example.myapplication.database.entity.Kontak;


@Database(entities = {Kontak.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase {

    public abstract KontakDao userDao();


}



