package ru.gisupov.neuroland.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.gisupov.neuroland.R;

import java.io.IOException;

public class RegActivity extends AppCompatActivity {

    public final static String REG_ACTIVITY_USER_LOGIN = "com.example.myproject_neuroland.ui.RegActivity.LOGIN";
    public final static String REG_ACTIVITY_USER_PASSWORD = "com.example.myproject_neuroland.ui.RegActivity.PASSWORD";

    public final static String FILE_NAME = "personData.txt";

    public static String userLoginFromFile = "";
    public static String userPasswordFromFile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void goToLoginAfterReg(View view) {
        // getting data from register activity
        EditText userName = (EditText) findViewById(R.id.BeginLogin);
        EditText userPassword1 = (EditText) findViewById(R.id.BeginPassword1);
        EditText userPassword2 = (EditText) findViewById(R.id.BeginPassword2);

        short loginSize = (short) userName.getText().length();
        short passSize = (short) userPassword1.getText().length();

        if (loginSize == 0 && passSize == 0) {
            // the both fields are empty
            Toast.makeText(getApplicationContext(), R.string.emptyLogReg, Toast.LENGTH_SHORT).show();
            return;
        } else if (loginSize == 0) {
            // the login field is empty
            Toast.makeText(getApplicationContext(), R.string.emptyLog, Toast.LENGTH_SHORT).show();
            return;
        } else if (passSize == 0) {
            //the password field is empty
            Toast.makeText(getApplicationContext(), R.string.emptyReg, Toast.LENGTH_SHORT).show();
            return;
        } else {
            // all field are are entered
            if (passSize < 8) {
                Toast.makeText(getApplicationContext(), R.string.shortReg, Toast.LENGTH_SHORT).show();
                return;
            } else if (loginSize < 4) {
                Toast.makeText(getApplicationContext(), R.string.shortLog, Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String login = userName.getText().toString();
        String pass1 = userPassword1.getText().toString();
        String pass2 = userPassword2.getText().toString();

        if (pass1.equals(pass2)) {
            userLoginFromFile = login;
            userPasswordFromFile = pass1;

            Intent answer = new Intent();
            answer.putExtra(REG_ACTIVITY_USER_LOGIN, login);
            answer.putExtra(REG_ACTIVITY_USER_PASSWORD, pass1);
            setResult(RESULT_OK, answer);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), R.string.password_not_same, Toast.LENGTH_SHORT).show();
        }
    }
}