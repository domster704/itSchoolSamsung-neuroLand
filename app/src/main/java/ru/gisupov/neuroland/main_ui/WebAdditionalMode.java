package ru.gisupov.neuroland.main_ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import ru.gisupov.neuroland.HttpService;
import ru.gisupov.neuroland.R;


public class WebAdditionalMode extends AppCompatActivity {

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
    }

    public void getDataFromLink(View view) {
        AsyncRequest asyncRequest = new AsyncRequest();
        asyncRequest.start();
    }

    class AsyncRequest extends Thread {

        EditText area = findViewById(R.id.area);
        EditText distance = findViewById(R.id.distance);
        EditText hau = findViewById(R.id.HAU);
        EditText transport = findViewById(R.id.transport);
        EditText neighbors = findViewById(R.id.neighbors);
        EditText ecology = findViewById(R.id.ecology);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.region);

        TextView tv = (TextView) findViewById(R.id.textCost);

        @Override
        public void run() {
            String cost;
            String[] data = {
                    area.getText().toString(),
                    distance.getText().toString(),
                    ecology.getText().toString(),
                    hau.getText().toString(),
                    transport.getText().toString(),
                    neighbors.getText().toString(),
                    autoCompleteTextView.getText().toString()
            };

            try {
                HttpService httpService = new HttpService();
                cost = httpService.sendPOST("http://100.80.127.91:8000/data", data);
                tv.setText(cost);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
