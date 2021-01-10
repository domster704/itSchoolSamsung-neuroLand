package ru.gisupov.neuroland;

import java.sql.Timestamp;

public class Request {
    protected long id;
    protected Timestamp timestamp;
    protected String data;

    public Request() {
        this.id = (long) Math.ceil((Math.random() * 100000));
//        this.timestamp = ;
//        this.data = ;
    }
}
