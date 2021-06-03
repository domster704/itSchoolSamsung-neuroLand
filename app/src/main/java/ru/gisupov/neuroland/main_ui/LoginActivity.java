package ru.gisupov.neuroland.main_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import ru.gisupov.neuroland.ClientServer;
import ru.gisupov.neuroland.MyRequest;
import ru.gisupov.neuroland.MyResponse;
import ru.gisupov.neuroland.R;


/**
 * Активность с возможностью авторизации пользователя
 */
public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = (EditText) findViewById(R.id.EndLogin);
        pass = (EditText) findViewById(R.id.EndPassword);

        changeStatusBarColor();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Автоматическая установка логина и пароля в поля ввода
        if (!RegActivity.userPasswordFromFile.isEmpty() && !RegActivity.userLoginFromFile.isEmpty()) {
            login.setText(RegActivity.userLoginFromFile);
            pass.setText(RegActivity.userPasswordFromFile);
        }
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


    /**
     * Переход на Reg активность
     */
    public void goToReg(View view) {
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
    }

    /**
     * Переход на главную активность после успешной авторизации
     *
     * @throws InterruptedException исключение ошибки, связанной с ипользование другого потока
     *                              при взаимодействии с сервером
     */
    public void goToHomeAfterLogin(View view) throws InterruptedException {
        MyRequest myRequestAuth = new MyRequest("login", new String[]{login.getText().toString(), pass.getText().toString()});
        ClientServer server = new ClientServer();
        server.makeRequest(myRequestAuth);

        MyResponse myResponseAuth = server.getResponse();

        if (myResponseAuth.data.equals("True")) {
            RegActivity.userPasswordFromFile = pass.getText().toString();
            RegActivity.userLoginFromFile = login.getText().toString();

            SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(RegActivity.SAVED_LOGIN, RegActivity.userLoginFromFile);
            editor.putString(RegActivity.SAVED_PASSWORD, RegActivity.userPasswordFromFile);
            editor.apply();

            Toast.makeText(getApplicationContext(), R.string.successLogin, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}