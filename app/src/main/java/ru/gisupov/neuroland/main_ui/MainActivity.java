package ru.gisupov.neuroland.main_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import ru.gisupov.neuroland.ClientServer;
import ru.gisupov.neuroland.MyRequest;
import ru.gisupov.neuroland.MyResponse;
import ru.gisupov.neuroland.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDataToLastRequest();

        Button ar = (Button) findViewById(R.id.ar_but);
        ar.setOnClickListener(this::goToAr);

        Button web = (Button) findViewById(R.id.web_but);
        web.setOnClickListener(this::goToWeb);

        Button gps = (Button) findViewById(R.id.gps_but);
        gps.setOnClickListener(this::goToGPS);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("NeuroLand");
        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setOnMenuItemClickListener(item -> {
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
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDataToLastRequest();
    }

    private void addDataToLastRequest() {
        if (!RegActivity.userLoginFromFile.isEmpty() && !RegActivity.userPasswordFromFile.isEmpty()) {
            ClientServer server = new ClientServer();
            MyRequest myRequest = new MyRequest("getContent", new String[] {RegActivity.userLoginFromFile, RegActivity.userPasswordFromFile});
            try {
                server.makeRequest(myRequest);
                MyResponse response = server.getResponse();

                TextView tv = findViewById(R.id.lastReq);
                tv.setText(response.data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public void goToGPS(View view) {
        Intent intent = new Intent(this, GPSActivity.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToAr(View view) {
        Intent intent = new Intent(this, ARActivity.class);
        startActivity(intent);
    }

    public void goToWeb(View view) {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }
}

