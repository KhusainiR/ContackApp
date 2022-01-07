package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.database.dao.KontakDao;
import com.example.myapplication.database.db.MyApp;
import com.example.myapplication.database.entity.Kontak;

public class TambahKontakActivity extends AppCompatActivity implements View.OnClickListener{
    public final static String TAG_DATA_INTENT = "data_mahasiswa";
    TextView judul;
    Button btnSimpan;
    EditText etNama, etNomor;
    Kontak kontak;
    KontakDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kontak);

        dao = MyApp.getInstance().getDatabase().userDao();

        if (getIntent() != null) {
            int id = getIntent().getIntExtra(TAG_DATA_INTENT, 0);
            kontak = dao.findById(id);
        }

        btnSimpan = findViewById(R.id.btnSimpan);
        etNama = findViewById(R.id.etNama);
        etNomor = findViewById(R.id.etNomor);
        judul = findViewById(R.id.judul);

        if (kontak != null){
            etNama.setText(kontak.getNama());
            etNomor.setText(kontak.getNomor());


            btnSimpan.setText("Update Data");
            judul.setText("Update Data Kontak");
        }
        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        final String nama = etNama.getText().toString();
        final String nomor = etNomor.getText().toString();
        if (nama.isEmpty() && nomor.isEmpty()) {
            Toast.makeText(TambahKontakActivity.this, "Mohon Isi Data Dengan Lengkap", Toast.LENGTH_SHORT).show();
        } else if(nama.isEmpty()) {
            Toast.makeText(TambahKontakActivity.this, "Mohon Isi Nama Kontak !", Toast.LENGTH_SHORT).show();
        } else if (nomor.isEmpty()) {
            Toast.makeText(TambahKontakActivity.this, "Mohon Isi Nomor Telepon !", Toast.LENGTH_SHORT).show();
        } else {
            addOrUpdate();
            if (kontak.getId() == 0){
                dao.insertData(kontak);
                Toast.makeText(this, "Berhasil Tambah Kontak", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                dao.update(kontak);
                Toast.makeText(this, "Berhasil Update Kontak", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    private void addOrUpdate(){
        if (kontak == null ){
            kontak = new Kontak();
        }
        kontak.setNama(etNama.getText().toString());
        kontak.setNomor(etNomor.getText().toString());
    }

}