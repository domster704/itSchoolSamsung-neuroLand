package ru.gisupov.neuroland.main_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.*;

import ru.gisupov.neuroland.R;

public class WebChooseActivity extends AppCompatActivity {

    static class HttpService {

        private final OkHttpClient httpClient = new OkHttpClient();

        public HttpService() {}

        private String sendPOST(String url, String data) throws IOException {

            // json formatted data
            String json = "{\"data\": \"" + data + "\"}";

            // json request body
            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    json
            );

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("User-Agent", "OkHttp Bot")
                    .post(body)
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                // Get response body
                assert response.body() != null;
                return response.body().string();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
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