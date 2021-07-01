package ru.gisupov.neuroland.main_ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Arrays;

import ru.gisupov.neuroland.ClientServer;
import ru.gisupov.neuroland.MyRequest;
import ru.gisupov.neuroland.MyResponse;
import ru.gisupov.neuroland.R;

/**
 * Активность для получения цены земельного участка из ссылки
 */
public class WebActivity extends AppCompatActivity {

    // Ссылка и цена из последнего запроса
    public static String lastLink = "";
    public static String lastCost = "";
    public static int lastIDLink = 0;
    public static String[] lastParameters = new String[]{"12 сот", "24 км ", "3.0", "3.0", "3.0", "3.0",};

    public static String encode(String i1, String i2, String[] i3) {
        return i1 + "-" + i2 + "-" + Arrays.toString(i3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        SharedPreferences preferences = getSharedPreferences("AppData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        lastIDLink = preferences.getInt("key", 0);
        editor.apply();

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
     * Переход на WebAdditionalMode активность
     */
    public void goToAdditionalMode(View view) {
        Intent intent = new Intent(this, WebAdditionalModeActivity.class);
        startActivity(intent);
    }

    /**
     * Функция для получения цены из сслыки на земельный участок (с сайта {@link #domofond.ru}
     *
     * @throws InterruptedException исключение ошибки, связанной с ипользование другого потока
     *                              при взаимодействии с сервером
     */
    public void getDataFromLink(View view) throws InterruptedException {
        EditText et = (EditText) findViewById(R.id.textLink);
        TextView tv = (TextView) findViewById(R.id.textCost);

        String urlData = et.getText().toString();
        String cost;

        MyRequest myRequest = new MyRequest("url", new String[]{urlData});
        try {
            ClientServer server = new ClientServer();
            server.makeRequest(myRequest);

            MyResponse myResponse = server.getResponse();

            String[] data = myResponse.data.split(";");
            cost = data[0];
            tv.setText(cost + "₽");

            lastLink = data[5];
            lastCost = cost;
            lastParameters = new String[]{data[6] + " сот", data[7] + " км ", data[1], data[2], data[3], data[4]};

            // Изменение последнего запроса в базе данных текущего пользователя
            if (!RegActivity.userLoginFromFile.isEmpty() && !RegActivity.userPasswordFromFile.isEmpty()) {
                MyRequest myRequest2 = new MyRequest("changeContent", new String[]{RegActivity.userLoginFromFile, RegActivity.userPasswordFromFile,
                        urlData + " " + cost});

                server.makeRequest(myRequest);

                MyResponse myResponse2 = server.getResponse();

                if (myResponse2.data.equals("True"))
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                SharedPreferences preferences = getSharedPreferences("WebData", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(String.valueOf(lastIDLink), encode(lastLink, lastCost, lastParameters));
                lastIDLink++;
                editor.apply();

                SharedPreferences preferences2 = getSharedPreferences("AppData", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = preferences2.edit();
                editor2.putInt("key", lastIDLink);
                editor2.apply();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
        }
    }
}