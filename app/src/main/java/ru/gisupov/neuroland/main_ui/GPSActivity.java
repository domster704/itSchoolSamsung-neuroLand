package ru.gisupov.neuroland.main_ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import ru.gisupov.neuroland.R;


/**
 * Активность позволяющая переходить на активности с картами
 */
public class GPSActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps);

        changeStatusBarColor();
    }

    /**
     * Меняет цвет строки состояния (строка уведомлений)
     */
    private void changeStatusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_app_color));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }


    /**
     * Переход на Maps активность
     */
    public void goToMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    /**
     * Переход на MapsCadast активность
     */
    public void goToCadastMap(View view) {
        Intent intent = new Intent(this, MapsCadastActivity.class);
        startActivity(intent);
    }
}