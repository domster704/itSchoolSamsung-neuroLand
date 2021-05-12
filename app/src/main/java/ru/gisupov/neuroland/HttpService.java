package ru.gisupov.neuroland;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.gisupov.neuroland.main_ui.SettingsActivity;

public class HttpService {

    private static String url = SettingsActivity.ip;

    private final OkHttpClient httpClient = new OkHttpClient();

    public HttpService() {}

    public String post(String json, String doing) throws IOException {

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json
        );

        okhttp3.Request request = new Request.Builder()
                .url(url + doing)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            assert response.body() != null;
            return Objects.requireNonNull(response.body()).string();
        }
    }

}
