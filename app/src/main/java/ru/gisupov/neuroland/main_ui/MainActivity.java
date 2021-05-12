package ru.gisupov.neuroland.main_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.google.android.material.tabs.TabLayout;

import ru.gisupov.neuroland.ClientServer;
import ru.gisupov.neuroland.MyRequest;
import ru.gisupov.neuroland.MyResponse;
import ru.gisupov.neuroland.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeStatusBarColor();

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

    private void changeStatusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.home_screen_color1));
    }

    public void AddRequestView(String link, String cost) {

        ViewGroup viewGroup = findViewById(R.id.request_layout);

        View child = LayoutInflater.from(this).inflate(R.layout.request, null);

        viewGroup.addView(child);

        TextView linkTV = child.findViewById(R.id.link);
        linkTV.setText(link);

        TextView linkCostTV = child.findViewById(R.id.link_cost);
        linkCostTV.setText(cost);
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

                try {
                    String[] data = response.data.split(" ");
                    AddRequestView(data[0].substring(0, 28) + "...", data[1]);
                } catch (Exception ignore) { }

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

