package com.example.myproject_neuroland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RegActivity extends AppCompatActivity {

    public final static String REG_ACTIVITY_USER_LOGIN = "com.example.myproject_neuroland.RegActivity.LOGIN";
    public final static String REG_ACTIVITY_USER_PASSWORD = "com.example.myproject_neuroland.RegActivity.PASSWORD";

    public final static String FILE_NAME = "personData.txt";

    public static String userLoginFromFile = "";
    public static String userPasswordFromFile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void goToLoginAfterReg(View view) throws IOException {
        // getting data from register activity
        EditText userName = (EditText) findViewById(R.id.BeginLogin);
        EditText userPassword1 = (EditText) findViewById(R.id.BeginPassword1);
        EditText userPassword2 = (EditText) findViewById(R.id.BeginPassword2);

        String login = userName.getText().toString();
        String pass1 = userPassword1.getText().toString();
        String pass2 = userPassword2.getText().toString();

        if (pass1.equals(pass2)) {
            userLoginFromFile = login;
            userPasswordFromFile = pass1;

            Intent answer = new Intent();
            answer.putExtra(REG_ACTIVITY_USER_LOGIN, login);
            answer.putExtra(REG_ACTIVITY_USER_PASSWORD, pass1);
            setResult(RESULT_OK, answer);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
        }
    }
}