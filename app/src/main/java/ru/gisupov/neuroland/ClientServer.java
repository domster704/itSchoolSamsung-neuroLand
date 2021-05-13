package ru.gisupov.neuroland;

import java.io.IOException;

/**
 * Класс, который упрощает работу с классом HttpService
 */
public class ClientServer {
    private static MyResponse response;
    AsyncTask asyncTask;

    public ClientServer() { }

    /**
     * Класс для создания запроса в отдельной потоке
     */
    static class AsyncTask extends Thread {

        private final String json;
        private final String doing;

        public AsyncTask(String json, String doing) {
            this.doing = doing;
            this.json = json;
        }

        @Override
        public void run() {
            HttpService service = new HttpService();
            try {
                response = new MyResponse(service.post(json, doing));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Создаёт запрос по входящему объекту класса MyRequest
     * @param req Набор инструкций для создания запроса, объект класса MyRequest
     */
    public void makeRequest(MyRequest req) {
        asyncTask = new AsyncTask(req.json, req.doing);
        asyncTask.start();
    }

    /**
     * Даёт принятые от сервера данные
     * @return Объект класса MyResponse, хранящий в себе принятые от сервера данные
     * @throws InterruptedException
     */
    public MyResponse getResponse() throws InterruptedException {
        while (asyncTask.isAlive()) {
            try {
                asyncTask.join();
            } catch (Exception ignored) {}
        }

        return response;
    }
}
