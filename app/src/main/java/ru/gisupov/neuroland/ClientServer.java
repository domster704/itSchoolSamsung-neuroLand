package ru.gisupov.neuroland;

public class ClientServer {
    private long id;
    private String address;

    public ClientServer(long id, String address) {
        this.address = address;
        this.id = id;
    }

    public long makeRequest(Request req) {
        // making request
        return 1;
    }

    public boolean sendRequest(long id) {
        // checking is request sending and after this return true or false
        return true;
    }

//    public Response getResponse(long id) {
//        // doing something
//    }
}
