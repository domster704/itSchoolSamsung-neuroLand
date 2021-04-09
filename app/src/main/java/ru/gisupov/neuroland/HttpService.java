package ru.gisupov.neuroland;

import java.io.IOException;

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

    public String sendPOST(String data) throws IOException {

        // json formatted data
        String json = "{\"data\": \"" + data + "\"}";

        // json request body
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json
        );

        okhttp3.Request request = new Request.Builder()
                .url(url + "/url")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            assert response.body() != null;
            return response.body().string();
        }
    }

    public String sendPOST(String[] data) throws IOException {

        // json formatted data
        StringBuilder json = new StringBuilder()
                .append("{\"area\":\"" + data[0] + "\",")
                .append("\"proximity\":\"" + data[1] + "\",")
                .append("\"ecology\":\"" + data[2] + "\",")
                .append("\"purity\":\"" + 3.1f + "\",")
                .append("\"utilities\":\"" + data[3] + "\",")
                .append("\"neighbors\":\"" + data[5] + "\",")
                .append("\"children\":\"" + 3.4f + "\",")
                .append("\"sport\":\"" + 3.0f + "\",")
                .append("\"shop\":\"" + 4.2f + "\",")
                .append("\"transport\":\"" + data[4] + "\",")
                .append("\"safety\":\"" + 3.4f + "\",")
                .append("\"lifecost\":\"" + 2.3f + "\",")
                .append("\"region\":\"" + data[6] + "\"")
                .append("}");

        // json request body
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json.toString()
        );

        okhttp3.Request request = new Request.Builder()
                .url(url + "/data")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            assert response.body() != null;
            return response.body().string();
        }
    }
}
