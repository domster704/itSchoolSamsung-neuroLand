package ru.gisupov.neuroland;

public class RequestAuth extends Request{
    public String login;
    public String password;

    public RequestAuth(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void isDataTrue() {
        // Check data from dataBase and data from this class
        // and return true if they are the same, otherwise return false
    }

}
