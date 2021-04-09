package ru.gisupov.neuroland.main_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.*;

import ru.gisupov.neuroland.HttpService;
import ru.gisupov.neuroland.R;

public class WebChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
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
                cost = httpService.sendPOST("http://100.80.127.91:8000/url", urlData);
                tv.setText(cost);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getDataFromLink(View view) throws Exception {
        AsyncRequest asyncRequest = new AsyncRequest();
        asyncRequest.start();
        Toast.makeText(getApplicationContext(), "Кнопка нажата", Toast.LENGTH_SHORT).show();
    }
}