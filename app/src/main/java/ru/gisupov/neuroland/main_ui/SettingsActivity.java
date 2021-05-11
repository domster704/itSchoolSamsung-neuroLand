package ru.gisupov.neuroland.main_ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import ru.gisupov.neuroland.R;

public class SettingsActivity extends AppCompatActivity {

    public static String ip = "https://neuroland-server.herokuapp.com/";
    private EditText ipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.settings, new SettingsFragment())
//                    .commit();
//        }

        ipEditText = findViewById(R.id.ipEditText);
        ipEditText.setHint(ip.substring(7, ip.length() - 5));
    }


    public void saveSettings(View view) {
        String newIP = ipEditText.getText().toString();
        ip = "http://" + newIP + ":80";
        Toast.makeText(this, ip, Toast.LENGTH_LONG).show();
    }
}