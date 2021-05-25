package ru.gisupov.neuroland;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.gisupov.neuroland.main_ui.SettingsActivity;

/**
 * Класс для обращения к серверую.
 * Использует библиотеку okhttp3
 * Использует класс MyRequest для отправки запросов и
 * класс MyResponse для принятия ответов
 */
public class HttpService {

    private static String url = SettingsActivity.ip;

    private final OkHttpClient httpClient = new OkHttpClient();

    public HttpService() {}

    /**
     * Делает запрос на веб-сервер и получает от него ответ
     * @param json Данные запроса
     * @param doing Действие, которое необхожимо выполнить веб серверу (../login, ../register)
     * @return Объект класса MyResponse, который хранит в себе данные от сервера
     * @throws IOException
     */
    public String post(String json, String doing) throws IOException {

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json
        );

        Request request = new Request.Builder()
                .url(url + doing)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Error " + response);

            assert response.body() != null;
            return Objects.requireNonNull(response.body()).string();
        }
    }

}
