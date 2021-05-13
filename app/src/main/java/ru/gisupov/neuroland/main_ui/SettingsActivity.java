package ru.gisupov.neuroland.main_ui;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceFragmentCompat;

import ru.gisupov.neuroland.R;

public class SettingsActivity extends AppCompatActivity {

//    public static String ip = "https://neuroland-server.herokuapp.com/";
    public static String ip = "http://100.73.153.28:80/";
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
        ipEditText.setText(ip);

        changeStatusBarColor();
    }

    private void changeStatusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_app_color));
    }


    public void saveSettings(View view) {
        String newIP = ipEditText.getText().toString();
        ip = "http://" + newIP + ":80";
        Toast.makeText(this, ip, Toast.LENGTH_LONG).show();
    }
}