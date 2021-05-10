package ru.gisupov.neuroland.main_ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.gisupov.neuroland.ClientServer;
import ru.gisupov.neuroland.MyRequest;
import ru.gisupov.neuroland.MyResponse;
import ru.gisupov.neuroland.R;

public class LoginActivity extends AppCompatActivity {

    public final static int RESULT_FROM_REG_ACTIVITY = 10;

    private EditText login;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = (EditText) findViewById(R.id.EndLogin);
        pass = (EditText) findViewById(R.id.EndPassword);

        if (!RegActivity.userPasswordFromFile.isEmpty() && !RegActivity.userLoginFromFile.isEmpty()) {
            login.setText(RegActivity.userPasswordFromFile);
            pass.setText(RegActivity.userLoginFromFile);
        }

    }


    public void goToReg(View view) {
        Intent intent = new Intent(this, RegActivity.class);
        startActivityForResult(intent, RESULT_FROM_REG_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FROM_REG_ACTIVITY) {
            EditText loginView = (EditText) findViewById(R.id.EndLogin);
            EditText passView = (EditText) findViewById(R.id.EndPassword);
            if (resultCode == RESULT_OK) {
                assert data != null;
                String userLogin = data.getStringExtra(RegActivity.REG_ACTIVITY_USER_LOGIN);
                String userPassword = data.getStringExtra(RegActivity.REG_ACTIVITY_USER_PASSWORD);
                loginView.setText(userLogin);
                passView.setText(userPassword);
            }
        }
    }

    public void goToHomeAfterLogin(View view) throws InterruptedException {
        MyRequest myRequestAuth = new MyRequest("login", new String[] {login.getText().toString(), pass.getText().toString()});
        ClientServer server = new ClientServer();
        server.makeRequest(myRequestAuth);

        MyResponse myResponseAuth = server.getResponse();

        if (myResponseAuth.data.equals("True")) {
            RegActivity.userPasswordFromFile = pass.getText().toString();
            RegActivity.userLoginFromFile = login.getText().toString();
            Toast.makeText(getApplicationContext(), R.string.successLogin, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}