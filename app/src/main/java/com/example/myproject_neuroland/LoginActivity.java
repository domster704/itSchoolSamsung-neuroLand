package com.example.myproject_neuroland;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {

    public final static int RESULT_FROM_REG_ACTIVITY = 10;
    public final static String FILE_NAME = "personData.txt";
    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }


    public void goToReg(View view) {
        Intent intent = new Intent(this, RegActivity.class);
        startActivityForResult(intent, RESULT_FROM_REG_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FROM_REG_ACTIVITY) {
            EditText loginView = (EditText) findViewById(R.id.EndLogin);
            EditText passView = (EditText) findViewById(R.id.EndPassword);
            if (resultCode == RESULT_OK) {
                assert data != null;
                String userLogin = data.getStringExtra(RegActivity.REG_ACTIVITY_USER_LOGIN);
                String userPassword = data.getStringExtra(RegActivity.REG_ACTIVITY_USER_PASSWORD);
                loginView.setText(userLogin);
                passView.setText(userPassword);
            }
        }
    }

    public void goToHomeAfterLogin(View view) throws IOException {
        EditText login = (EditText) findViewById(R.id.EndLogin);
        EditText pass = (EditText) findViewById(R.id.EndPassword);

        if (login.getText().toString().equals(RegActivity.userLoginFromFile) && pass.getText().toString().equals(RegActivity.userPasswordFromFile)) {
            Toast.makeText(getApplicationContext(), "Вход был успешным", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Неверно логин или пароль!", Toast.LENGTH_SHORT).show();
        }

    }
}