package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private SharedPrefManager sharedPrefManager;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignIn;
    private ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getIsLogin()){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            finishAffinity();
            startActivity(i);
        }else {
//            sharedPrefManager = new SharedPrefManager(this);
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnSignIn = findViewById(R.id.btnSignIn);
            pbLogin = findViewById(R.id.pbLogin);
            login();
        }






    }

    private void login() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                pbLogin.setVisibility(View.VISIBLE);

                if (username.isEmpty() && password.isEmpty()){
                    pbLogin.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Mohon Isi Data Dengan Lengkap", Toast.LENGTH_SHORT).show();
                }else if(username.isEmpty()) {
                    pbLogin.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Mohon Isi Username Anda !", Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()) {
                    pbLogin.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Mohon Isi Password Anda !", Toast.LENGTH_SHORT).show();
                } else {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String spUsername = sharedPrefManager.getUsername();
                            String spPassword = sharedPrefManager.getPassword();

                            Log.d("Aplikasi", "user" + username);
                            Log.d("Aplikasi", "pass" + password);

                            if (username.equals(spUsername) && password.equals(spPassword)){
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                sharedPrefManager.saveIsLogin(true);
                                Toast.makeText(LoginActivity.this, "Berhasil Login !", Toast.LENGTH_SHORT).show();
                                finishAffinity();
                                startActivity(i);
                            }else if (username.equals(spUsername)){
                                pbLogin.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Password Salah !", Toast.LENGTH_SHORT).show();
                            }else{
                                pbLogin.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Username & Password Salah !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 2000);
                }
            }
        });
    }
}