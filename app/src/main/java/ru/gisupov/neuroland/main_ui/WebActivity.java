package ru.gisupov.neuroland.main_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import ru.gisupov.neuroland.ClientServer;
import ru.gisupov.neuroland.MyRequest;
import ru.gisupov.neuroland.MyResponse;
import ru.gisupov.neuroland.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        changeStatusBarColor();
    }

    private void changeStatusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_app_color));
    }

    public void goToAdditionalMode(View view) {
        Intent intent = new Intent(this, WebAdditionalModeActivity.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void getDataFromLink(View view) throws InterruptedException {
        EditText et = (EditText) findViewById(R.id.textLink);
        TextView tv = (TextView) findViewById(R.id.textCost);

        String urlData = et.getText().toString();
        String cost;

        MyRequest myRequest = new MyRequest("url", new String[] {urlData});
        ClientServer server = new ClientServer();
        server.makeRequest(myRequest);

        MyResponse myResponse = server.getResponse();
        cost = myResponse.data;
        tv.setText(cost);

        if (!RegActivity.userLoginFromFile.isEmpty() && !RegActivity.userPasswordFromFile.isEmpty()) {
            MyRequest myRequest2 = new MyRequest("changeContent", new String[]{RegActivity.userLoginFromFile, RegActivity.userPasswordFromFile,
                    urlData + " " + cost});

            server.makeRequest(myRequest);

            MyResponse myResponse2 = server.getResponse();

            if (myResponse2.data.equals("True"))
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, SettingsActivity.ip, Toast.LENGTH_LONG).show();
    }
}