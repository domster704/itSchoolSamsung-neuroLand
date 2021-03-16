package ru.gisupov.neuroland.main_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

import ru.gisupov.neuroland.R;

public class WebChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
    }

//    private static String getHTML(String urlToRead) throws Exception {
//        StringBuilder result = new StringBuilder();
//        URL url = new URL(urlToRead);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
//            String line;
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//        }
//        return result.toString();
//    }
//
//    private static String putHTML(String urlToRead, String data) throws Exception {
//        String boundary = Long.toHexString(System.currentTimeMillis());
//        URL url = new URL(urlToRead);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setUseCaches(false);
//        conn.setDoOutput(true);
//        conn.setDoInput(true);
//
//        StringJoiner sj = new StringJoiner("&");
//        sj.add(URLEncoder.encode(data, String.valueOf(StandardCharsets.UTF_8)));
//        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
//        int length = out.length;
//
//        conn.setFixedLengthStreamingMode(length);
//        try(OutputStream os = conn.getOutputStream()) {
//            os.write(out);
//        }
//
//        return getHTML(urlToRead);
//    }

    public void getDataFromLink(View view) throws Exception {
//        Process p = Runtime.getRuntime().exec("python app/src/main/java/ru/gisupov/neuroland/main_ui/python_neuro/neuroland/server-flask.py");

//        EditText et = (EditText) findViewById(R.id.textLink);
//        TextView tv = (TextView) findViewById(R.id.textCost);
//
//        String urlData = et.getText().toString();
//        String cost = putHTML("http://localhost:5000/", urlData);
//        String cost = getHTML("http://127.0.0.1:3000/");
//
//        tv.setText(cost);
        Toast.makeText(this, "Кнопка нажата", Toast.LENGTH_SHORT).show();
    }
}