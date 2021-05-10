package ru.gisupov.neuroland;

public class MyRequest {
    public String doing;
    public String json;

    public MyRequest(String doing, String[] data) {
        this.doing = doing;
        this.json = ConvertDataToJson(data);
    }

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
