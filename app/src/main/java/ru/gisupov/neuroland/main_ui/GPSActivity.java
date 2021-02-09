package ru.gisupov.neuroland.main_ui;

import android.app.Activity;
import android.os.Bundle;

import ru.gisupov.neuroland.R;

public class GPSActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);
    }


//    public void goToMap(View view) {
////        Intent intent = new Intent(this, MapsActivity.class);
////        startActivity(intent);
//    }
}