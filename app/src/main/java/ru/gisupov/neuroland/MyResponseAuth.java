package ru.gisupov.neuroland;

public class MyResponseAuth extends MyResponse {
    public String content;

    public MyResponseAuth(String data) {
        super(data);
    }

    public MyResponseAuth(String data, String content) {
        super(data);
        this.content = content;
    }
}
