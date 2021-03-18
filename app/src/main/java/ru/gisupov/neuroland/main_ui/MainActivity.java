package ru.gisupov.neuroland.main_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

        ImageButton login = (ImageButton) findViewById(R.id.loginButton);
        login.setOnClickListener(this::goToLogin);
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

