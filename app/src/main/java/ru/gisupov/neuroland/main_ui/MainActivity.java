package ru.gisupov.neuroland.main_ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;

import ru.gisupov.neuroland.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ar = (Button) findViewById(R.id.ar_but);
        ar.setOnClickListener(this::goToAr);

        Button web = (Button) findViewById(R.id.web_but);
        web.setOnClickListener(this::goToWeb);

        Button gps = (Button) findViewById(R.id.gps_but);
        gps.setOnClickListener(this::goToGPS);

//        ImageButton login = (ImageButton) findViewById(R.id.loginButton);
//        login.setOnClickListener(this::goToLogin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("NeuroLand");
        toolbar.inflateMenu(R.menu.menu_toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        toolbar.setOnMenuItemClickListener(new To);

        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                Toast.makeText(getApplicationContext(), "Натсройки", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.just:
                Toast.makeText(getApplicationContext(), "Просто", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

