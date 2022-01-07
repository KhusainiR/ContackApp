package com.example.myapplication.database.db;

import android.app.Application;

import androidx.room.Room;

public class MyApp extends Application {

//    public static AppDatabase db;
//
//    @Override
//    public void onCreate(){
//        super.onCreate();
//        db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class,"mahasiswa").allowMainThreadQueries().build();
//    }

    private static MyApp INSTANCE;
    public static AppDatabase db;
    public static MyApp getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(this,
                AppDatabase.class,"database_kontak")
                .allowMainThreadQueries().build();
        INSTANCE = this;
    }
    public  AppDatabase getDatabase(){
        return db;
    }

}
