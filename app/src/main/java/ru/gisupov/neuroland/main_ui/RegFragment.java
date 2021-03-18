package ru.gisupov.neuroland.main_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.gisupov.neuroland.R;
import ru.gisupov.neuroland.RequestReg;

public class RegFragment extends Fragment {

    public final static String REG_ACTIVITY_USER_LOGIN = "com.example.myproject_neuroland.main_ui.RegActivity.LOGIN";
    public final static String REG_ACTIVITY_USER_PASSWORD = "com.example.myproject_neuroland.main_ui.RegActivity.PASSWORD";

    public final static String FILE_NAME = "personData.txt";

    public static String userLoginFromFile = "";
    public static String userPasswordFromFile = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button goLogin = (Button) getView().findViewById(R.id.regButton);
        goLogin.setOnClickListener(this::goToLoginAfterReg);
    }

    public void goToLoginAfterReg(View view) {
        // getting data from register activity
        EditText userName = (EditText) getView().findViewById(R.id.BeginLogin);
        EditText userPassword1 = (EditText) getView().findViewById(R.id.BeginPassword1);
        EditText userPassword2 = (EditText) getView().findViewById(R.id.BeginPassword2);

        String login = userName.getText().toString();
        String pass1 = userPassword1.getText().toString();
        String pass2 = userPassword2.getText().toString();

        RequestReg requestReg = new RequestReg(login, pass1, pass2);

        short loginSize = (short) userName.getText().length();
        short passSize = (short) userPassword1.getText().length();

        if (loginSize == 0 && passSize == 0) {
            // the both fields are empty
            Toast.makeText(getContext(), R.string.emptyLogReg, Toast.LENGTH_SHORT).show();
            return;
        } else if (loginSize == 0) {
            // the login field is empty
            Toast.makeText(getContext(), R.string.emptyLog, Toast.LENGTH_SHORT).show();
            return;
        } else if (passSize == 0) {
            //the password field is empty
            Toast.makeText(getContext(), R.string.emptyReg, Toast.LENGTH_SHORT).show();
            return;
        } else {
            // all field are are entered
            if (passSize < 8) {
                Toast.makeText(getContext(), R.string.shortReg, Toast.LENGTH_SHORT).show();
                return;
            } else if (loginSize < 4) {
                Toast.makeText(getContext(), R.string.shortLog, Toast.LENGTH_SHORT).show();
                return;
            }
        }

//        if (pass1.equals(pass2)) {
//            userLoginFromFile = login;
//            userPasswordFromFile = pass1;
//
//            Intent answer = new Intent();
//            answer.putExtra(REG_ACTIVITY_USER_LOGIN, login);
//            answer.putExtra(REG_ACTIVITY_USER_PASSWORD, pass1);
//            setResult(RESULT_OK, answer);
//            finish();
//        } else {
//            Toast.makeText(getContext(), R.string.password_not_same, Toast.LENGTH_SHORT).show();
//        }
    }
}
