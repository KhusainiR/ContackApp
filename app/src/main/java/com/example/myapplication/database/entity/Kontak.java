package com.example.myapplication.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kontak {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "nama")
    private String nama = "";

    @ColumnInfo(name = "nomor")
    private String nomor = "";

//    public Kontak(int id, String nama, String nomor) {
//        this.id = id;
//        this.nama = nama;
//        this.nomor = nomor;
//    }


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNama() {return nama;}

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor() {return nomor;}

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }


}
