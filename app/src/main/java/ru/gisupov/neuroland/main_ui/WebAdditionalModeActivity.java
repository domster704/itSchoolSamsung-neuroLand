package ru.gisupov.neuroland.main_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import ru.gisupov.neuroland.ClientServer;
import ru.gisupov.neuroland.HttpNeuroService;
import ru.gisupov.neuroland.MyRequest;
import ru.gisupov.neuroland.MyResponse;
import ru.gisupov.neuroland.R;


public class WebAdditionalModeActivity extends AppCompatActivity {

    private static final String[] city = {"Камчатский край",
            "Марий Эл",
            "Чечня",
            "Оренбургская область",
            "Ямало-Ненецкий АО",
            "Забайкальский край",
            "Ярославская область",
            "Владимирская область",
            "Бурятия",
            "Калмыкия",
            "Белгородская область",
            "Вологодская область",
            "Волгоградская область",
            "Калужская область",
            "Ингушетия",
            "Кабардино-Балкария",
            "Иркутская область",
            "Ивановская область",
            "Астраханская область",
            "Карачаево-Черкесия",
            "Новгородская область",
            "Курганская область",
            "Костромская область",
            "Краснодарский край",
            "Магаданская область",
            "Нижегородская область",
            "Кировская область",
            "Липецкая область",
            "Мурманская область",
            "Курская область",
            "Мордовия",
            "Хакасия",
            "Карелия",
            "Якутия",
            "Татарстан",
            "Адыгея",
            "Омская область",
            "Пензенская область",
            "Псковская область",
            "Северная Осетия",
            "Башкортостан",
            "Пермский край",
            "Ростовская область",
            "Дагестан",
            "Приморский край",
            "Орловская область",
            "Томская область",
            "Тверская область",
            "Удмуртия",
            "Ставропольский край",
            "Ульяновская область",
            "Хабаровский край",
            "Смоленская область",
            "Ханты-Мансийский АО",
            "Челябинская область",
            "Самарская область",
            "Тульская область",
            "Тамбовская область",
            "Тюменская область",
            "Свердловская область",
            "Сахалинская область",
            "Рязанская область",
            "Республика Алтай",
            "Чувашия",
            "Чукотский АО",
            "Брянская область",
            "Еврейская АО",
            "Алтайский край",
            "Калининградская область",
            "Архангельская область",
            "Кемеровская область",
            "Амурская область",
            "Воронежская область",
            "Красноярский край",
            "Ненецкий АО",
            "Тыва",
            "Коми",
            "Новосибирская область",
            "Саратовская область",
            "Ленинградская область",
            "Московская область",
            "Крым",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_add_mode);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.region);
        autoCompleteTextView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, city));

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public void getDataFromLink(View view) throws InterruptedException {
        EditText area = findViewById(R.id.area);
        EditText distance = findViewById(R.id.distance);
        EditText hau = findViewById(R.id.HAU);
        EditText transport = findViewById(R.id.transport);
        EditText neighbors = findViewById(R.id.neighbors);
        EditText ecology = findViewById(R.id.ecology);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.region);

        MyRequest myRequest = new MyRequest("data", new String[] {
                area.getText().toString(),
                distance.getText().toString(),
                ecology.getText().toString(),
                hau.getText().toString(),
                transport.getText().toString(),
                neighbors.getText().toString(),
                autoCompleteTextView.getText().toString().equals("") ? "Московская область" : "Московская область"
        });

        TextView tv = findViewById(R.id.textCost);

        ClientServer server = new ClientServer();
        server.makeRequest(myRequest);

        MyResponse response = server.getResponse();
        tv.setText(response.data);
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
