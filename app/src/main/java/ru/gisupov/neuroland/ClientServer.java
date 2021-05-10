package ru.gisupov.neuroland;

import android.util.Log;

import java.io.IOException;

import ru.gisupov.neuroland.main_ui.LoginActivity;
import ru.gisupov.neuroland.main_ui.SettingsActivity;

public class ClientServer {
    private static MyResponse response;
    AsyncTask asyncTask;

    public ClientServer() { }

    static class AsyncTask extends Thread {

        private final String json;
        private final String doing;

        public AsyncTask(String json, String doing) {
            this.doing = doing;
            this.json = json;
        }

        @Override
        public void run() {
            HttpAuthService service = new HttpAuthService();
            try {
                response = new MyResponse(service.post(json, doing));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void makeRequest(MyRequest req) {
        asyncTask = new AsyncTask(req.json, req.doing);
        asyncTask.start();
    }

    public MyResponse getResponse() throws InterruptedException {
        if (asyncTask.isAlive())
            asyncTask.join();

        Log.d("xd", response.data);
        return response;
    }
}
