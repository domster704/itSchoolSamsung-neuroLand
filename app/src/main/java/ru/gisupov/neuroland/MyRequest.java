package ru.gisupov.neuroland;

/**
 * Класс для запросов к серверу
 */
public class MyRequest {
    public String doing;
    public String json;

    public MyRequest(String doing, String[] data) {
        this.doing = doing;
        this.json = ConvertDataToJson(data);
    }

    /**
     * Конвертирует массив строк в json-строку
     *
     * @param data Массив строк, которые будут конвертироваться в json
     * @return Json-строку
     */
    protected String ConvertDataToJson(String data[]) {
        StringBuilder newJson = new StringBuilder();
        newJson.append("{");
        for (int i = 0; i < data.length; i++) {
            if (i == data.length - 1)
                newJson.append("\"").append(i).append("\":\"").append(data[i]).append("\"");
            else
                newJson.append("\"").append(i).append("\":\"").append(data[i]).append("\",");
        }
        newJson.append("}");
        return newJson.toString();
    }
}
