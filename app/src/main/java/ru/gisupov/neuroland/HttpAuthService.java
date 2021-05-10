package ru.gisupov.neuroland;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.gisupov.neuroland.main_ui.SettingsActivity;

public class HttpAuthService {

    private static String url = SettingsActivity.ip + "/";

    private final OkHttpClient httpClient = new OkHttpClient();

    public HttpAuthService() {}

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

//    public ResponseReg registration(String[] data) throws IOException {
//
//        String json = "{\"name\": \"" + data[0] + "\"," +
//                        "\"password\": \"" + data[1] + "\"}";
//
//        RequestBody body = RequestBody.create(
//                MediaType.parse("application/json; charset=utf-8"),
//                json
//        );
//
//        okhttp3.Request request = new Request.Builder()
//                .url(url + "/register")
//                .post(body)
//                .build();
//
//        try (Response response = httpClient.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            assert response.body() != null;
//            return new ResponseReg(Objects.requireNonNull(response.body()).string());
//        }
//    }
//
//    public MyResponseAuth login(String[] data) throws IOException {
//
//        String json = "{\"name\": \"" + data[0] + "\"," +
//                    "\"password\": \"" + data[1] + "\"}";
//
//        RequestBody body = RequestBody.create(
//                MediaType.parse("application/json; charset=utf-8"),
//                json
//        );
//
//        okhttp3.Request request = new Request.Builder()
//                .url(url + "/login")
//                .post(body)
//                .build();
//
//        try (Response response = httpClient.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            assert response.body() != null;
//            String content = getContent(data);
//            if (content.equals("None"))
//                return new MyResponseAuth(Objects.requireNonNull(response.body()).string());
//            else
//                return new MyResponseAuth(Objects.requireNonNull(response.body()).string(), content);
//        }
//    }
//
//    public String getContent(String[] data) throws IOException {
//
//        String json = "{\"name\": \"" + data[0] + "\"," +
//                "\"password\": \"" + data[1] + "\"}";
//
//        RequestBody body = RequestBody.create(
//                MediaType.parse("application/json; charset=utf-8"),
//                json
//        );
//
//        okhttp3.Request request = new Request.Builder()
//                .url(url + "/getContent")
//                .post(body)
//                .build();
//
//        try (Response response = httpClient.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            assert response.body() != null;
//            return Objects.requireNonNull(response.body()).string();
//        }
//    }
//
//    public String changeContent(String[] data) throws IOException {
//
//        String json = "{\"name\": \"" + data[0] + "\"," +
//                    "\"password\": \"" + data[1] + "\",}" +
//                    "\"content\": \"" + data[2] + "\"}";
//
//        RequestBody body = RequestBody.create(
//                MediaType.parse("application/json; charset=utf-8"),
//                json
//        );
//
//        okhttp3.Request request = new Request.Builder()
//                .url(url + "/changeContent")
//                .post(body)
//                .build();
//
//        try (Response response = httpClient.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            assert response.body() != null;
//            return Objects.requireNonNull(response.body()).string();
//        }
//    }

}
