package ru.gisupov.neuroland;

public class RequestReg extends Request{
    private String login;
    private String pass1;
    private String pass2;
    private String email;

    private String userLoginFromFile = "";
    private String userPasswordFromFile = "";

    public RequestReg(String login, String pass1, String pass2) {
        this.login = login;
        this.pass1 = pass1;
        this.pass2 = pass2;
//        this.email = email;
    }

    public boolean checkPassword() {
        // checking are pass1 and pass2 the same? checking pass1.length etc.
        if (pass1.equals(pass2)) {

        }
        return true;
    }

    public ResponseReg checkData() {
        // checking if there is data in db and return ResponseReg
        ResponseReg answer = null;
        return answer;
    }
}
