package ru.gisupov.neuroland;

import java.util.Arrays;

/**
 * Класс для хранения принятых данных от сервера
 */
public class MyResponse {

    public String data;

    public MyResponse(String data) {
        this.data = data;
    }

    public MyResponse(String[] data) {
        this.data = Arrays.toString(data);
    }
}
