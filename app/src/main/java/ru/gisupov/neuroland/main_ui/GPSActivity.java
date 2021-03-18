package ru.gisupov.neuroland.main_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ru.gisupov.neuroland.R;

public class GPSActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);
    }


    public void goToMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void goToCadastMap(View view) {
        Intent intent = new Intent(this, MapsCadastActivity.class);
        startActivity(intent);
    }
}