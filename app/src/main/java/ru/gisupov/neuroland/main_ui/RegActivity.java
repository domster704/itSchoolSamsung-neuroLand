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
import ru.gisupov.neuroland.MyResponse;
import ru.gisupov.neuroland.R;
import ru.gisupov.neuroland.MyRequestReg;


/**
 * Активность с возможностью регистрации пользователя
 */
public class RegActivity extends AppCompatActivity {

    public static String userLoginFromFile = "";
    public static String userPasswordFromFile = "";

    public static final String SAVED_LOGIN = "LOGIN";
    public static final String SAVED_PASSWORD = "PASSWORD";

    EditText userName, userPassword1, userPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        changeStatusBarColor();
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
     * Проверка правильности регистрации пользователя и успешной отправки
     * запроса веб-серверу на регистрацию
     * @return true и false в зависимости от результата
     * @throws InterruptedException
     */
    public boolean isRegistered() throws InterruptedException {
        userName = (EditText) findViewById(R.id.BeginLogin);
        userPassword1 = (EditText) findViewById(R.id.BeginPassword1);
        userPassword2 = (EditText) findViewById(R.id.BeginPassword2);

        String login = userName.getText().toString();
        String pass1 = userPassword1.getText().toString();
        String pass2 = userPassword2.getText().toString();

        MyRequestReg myRequestReg = new MyRequestReg("register", new String[] {login, pass1, pass2});

        if (myRequestReg.checkNamePass() != 1) {
            Toast.makeText(getApplicationContext(), myRequestReg.checkNamePass(), Toast.LENGTH_SHORT).show();
            return false;
        }

        ClientServer clientServer = new ClientServer();
        clientServer.makeRequest(myRequestReg);

        MyResponse myResponse = clientServer.getResponse();
        if (myResponse.data.equals("True")) {
            configureData(login, pass1);
            return true;
        } else
            return false;
    }

    /**
     * Сохранение данных аккаунта в Android Preferences
     * @param login логин пользователя
     * @param password пароль пользователя
     */
    public void configureData(String login, String password) {
        userPasswordFromFile = login;
        userPasswordFromFile = password;

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_LOGIN, userLoginFromFile);
        editor.putString(SAVED_PASSWORD, userPasswordFromFile);
        editor.apply();
    }

    /**
     * Переход на Login активность при успешной регистрации
     * @throws InterruptedException исключение ошибки, связанной с ипользование другого потока
      * при взаимодействии с сервером
     */
    public void goToLoginAfterReg(View view) throws InterruptedException {
        if (isRegistered()) {
            Intent answer = new Intent(this, LoginActivity.class);
            startActivity(answer);
        }
    }
}