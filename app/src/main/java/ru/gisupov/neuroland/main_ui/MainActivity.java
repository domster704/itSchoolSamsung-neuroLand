package ru.gisupov.neuroland.main_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ru.gisupov.neuroland.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToGPS(View view) {
        Intent intent = new Intent(this, GPSActivity.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToAr(View view) {
        Intent intent = new Intent(this, ARActivity.class);
        startActivity(intent);
    }

    public void goToWeb(View view) {
        Intent intent = new Intent (this, WebChooseActivity.class);
        startActivity(intent);
    }
}