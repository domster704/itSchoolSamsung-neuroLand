package ru.gisupov.neuroland;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.gisupov.neuroland.main_ui.SettingsActivity;

public class HttpNeuroService {

    private static String url = SettingsActivity.ip;

    private final OkHttpClient httpClient = new OkHttpClient();

    public HttpNeuroService() {}

    public String sendPOST(String data) throws IOException {

        String json = "{\"data\": \"" + data + "\"}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json
        );

        okhttp3.Request request = new Request.Builder()
                .url(url + "/url")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            assert response.body() != null;
            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String sendPOST(String[] data) throws IOException {

        // json formatted data
        StringBuilder json = new StringBuilder()
                .append("{\"area\":\"" + data[0] + "\",")
                .append("\"proximity\":\"" + data[1] + "\",")
                .append("\"ecology\":\"" + data[2] + "\",")
                .append("\"utilities\":\"" + data[3] + "\",")
                .append("\"neighbors\":\"" + data[5] + "\",")
                .append("\"transport\":\"" + data[4] + "\",")
                .append("\"region\":\"" + data[6] + "\"")
                .append("}");

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json.toString()
        );

        okhttp3.Request request = new Request.Builder()
                .url(url + "/data")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            assert response.body() != null;
            return Objects.requireNonNull(response.body()).string();
        }
    }


}
