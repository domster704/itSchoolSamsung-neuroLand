package ru.gisupov.neuroland.main_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.IOException;

import okhttp3.*;

import ru.gisupov.neuroland.HttpService;
import ru.gisupov.neuroland.R;

public class WebChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("NeuroLand");
        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.settings:
                        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.just:
                        Toast.makeText(getApplicationContext(), "Просто", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public void goToAdditionalMode(View view) {
        Intent intent = new Intent(this, WebAdditionalMode.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

   class AsyncRequest extends Thread {
        EditText et = (EditText) findViewById(R.id.textLink);
        TextView tv = (TextView) findViewById(R.id.textCost);

        @Override
        public void run() {
            String urlData = et.getText().toString();
            String cost;

            try {
                HttpService httpService = new HttpService();
                cost = httpService.sendPOST(urlData);
                tv.setText(cost);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getDataFromLink(View view) throws Exception {
        AsyncRequest asyncRequest = new AsyncRequest();
        asyncRequest.start();
        Toast.makeText(this, SettingsActivity.ip, Toast.LENGTH_LONG).show();
    }
}