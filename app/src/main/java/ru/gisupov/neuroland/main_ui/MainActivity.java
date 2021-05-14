package ru.gisupov.neuroland.main_ui;

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

import java.util.ArrayList;
import java.util.List;

import ru.gisupov.neuroland.ClientServer;
import ru.gisupov.neuroland.MyRequest;
import ru.gisupov.neuroland.MyResponse;
import ru.gisupov.neuroland.R;

/**
 * Гланвая активность всего приложения.
 * Показывает последние запросы пользователя, а также позволяет переходить к
 * другим важным активностям
 *
 * @author domster704
 * @version 0.1.2
 * @since 2020-12-27
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static class RequestForm {
        public View view;
        public boolean isPressed = false;

        public RequestForm(View view) {
            this.view = view;
        }
    }

    private ArrayList<RequestForm> requestForms = new ArrayList<>();
    private int requestID = 242487284;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeStatusBarColor();

        // Создание тул-бара с меню
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

        AddRequestView("Удмуртия", "275874.28");
        AddRequestView("Московская область", "1765890.95");
    }

    /**
     * Меняет цвет строки состояния (строка уведомлений)
     */
    private void changeStatusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.home_screen_color1));
    }

    /**
     * Добавляет в активность request.xml, содержащий в себе
     * информацию о последнем запросе
     *
     * @param link Ссылка или название региона последнего запроса
     * @param cost Цена последнего запроса
     */
    public void AddRequestView(String link, String cost) {

        ViewGroup viewGroup = findViewById(R.id.request_layout);

        View child = LayoutInflater.from(this).inflate(R.layout.request, null);
        child.setId(requestID);
        requestID++;
        child.setOnClickListener(this);

        viewGroup.addView(child);

        TextView linkTV = child.findViewById(R.id.link);
        linkTV.setText(link);

        TextView linkCostTV = child.findViewById(R.id.link_cost);
        linkCostTV.setText(cost);

        int[] ids = new int[] {R.id.ec, R.id.hug, R.id.neigh, R.id.trans};
        String[] words = new String[] {"Экология", "ЖКХ", "Соседи", "Транспорт"};

        for (int i = 0; i < ids.length; i++) {
            TextView tv = child.findViewById(ids[i]);
            tv.setText(WebActivity.lastParameters[i] + "    " + words[i]);
        }

        requestForms.add(new RequestForm(child));
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < requestForms.size(); i++) {
            if (v == requestForms.get(i).view && !requestForms.get(i).isPressed) {

                requestForms.get(i).isPressed = true;
                requestForms.get(i).view.findViewById(R.id.hidden).setVisibility(View.VISIBLE);

            } else if (v == requestForms.get(i).view && requestForms.get(i).isPressed) {

                requestForms.get(i).isPressed = false;
                requestForms.get(i).view.findViewById(R.id.hidden).setVisibility(View.GONE);

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDataToLastRequest();
    }

    /**
     * Функция, которая решает, когда надо вызывать функцию {@link #AddRequestView(String, String)}
     */
    private void addDataToLastRequest() {
        if (!RegActivity.userLoginFromFile.isEmpty() && !RegActivity.userPasswordFromFile.isEmpty()) {
            ClientServer server = new ClientServer();
            MyRequest myRequest = new MyRequest("getContent", new String[]{RegActivity.userLoginFromFile, RegActivity.userPasswordFromFile});
            try {
                server.makeRequest(myRequest);
                MyResponse response = server.getResponse();

                try {
                    String[] data = response.data.split(" ");
                    AddRequestView(data[0].substring(0, 28) + "...", data[1]);
                } catch (Exception ignore) {
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            String cost = WebActivity.lastCost;
            String link = WebActivity.lastLink;

            if (!cost.isEmpty() && !link.isEmpty())
                AddRequestView(link, cost);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    /**
     * onPrepareOptionsMenu также вызывается перед отображением действия,
     * а также каждый раз, когда меню параметров становится недействительным.
     * <p>
     * Обычно вызывается, если нужно если требуется обновить меню во время работы программы
     */
    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Переход на GPS активность
     */
    public void goToGPS(View view) {
        Intent intent = new Intent(this, GPSActivity.class);
        startActivity(intent);
    }

    /**
     * Переход на Login активность
     */
    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Переход на AR активность
     */
    public void goToAr(View view) {
        Intent intent = new Intent(this, ARActivity.class);
        startActivity(intent);
    }

    /**
     * Переход на Web активность
     */
    public void goToWeb(View view) {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }
}

