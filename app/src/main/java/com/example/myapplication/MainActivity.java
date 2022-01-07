package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.common.DataListListener;
import com.example.myapplication.database.db.MyApp;
import com.example.myapplication.database.entity.Kontak;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView myRecycleview;
    RecyclerViewAdapter recyclerAdapter;
    List<Kontak> listKontak = new ArrayList<>();
    FloatingActionButton fabAdd, fabLogout;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecycleview = findViewById(R.id.myRecyclerview);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        fabAdd = findViewById(R.id.fabAdd);
        fabLogout = findViewById(R.id.fabLogout);

        adapter = new RecyclerViewAdapter();
        myRecycleview.setAdapter(adapter);

        adapter.setRemoveListener(new DataListListener() {
            @Override
            public void onRemoveClick(Kontak kontak) {
                adapter.removeData(kontak);
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahKontakActivity.class));
            }
        });

        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                sharedPrefManager.saveIsLogin(false);
                Toast.makeText(MainActivity.this, "Berhasil Logout", Toast.LENGTH_SHORT).show();
                finishAffinity();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Kontak> datas = MyApp.getInstance().getDatabase().userDao().getAll();
        adapter.setData(datas);
    }

}