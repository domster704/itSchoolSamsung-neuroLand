package ru.gisupov.neuroland.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import ru.gisupov.neuroland.R;

public class GPSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);
    }

    public void goToMap(View view) {
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);
    }
}