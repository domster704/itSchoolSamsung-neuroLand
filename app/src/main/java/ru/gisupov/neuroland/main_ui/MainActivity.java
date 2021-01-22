package ru.gisupov.neuroland.main_ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

import ru.gisupov.neuroland.R;

public class MainActivity extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button ar = (Button) getView().findViewById(R.id.ar_but);
        ar.setOnClickListener(this::goToAr);

        Button web = (Button) getView().findViewById(R.id.web_but);
        web.setOnClickListener(this::goToWeb);

        Button gps = (Button) getView().findViewById(R.id.gps_but);
        gps.setOnClickListener(this::goToGPS);

        Button login = (Button) getView().findViewById(R.id.log_but);
        login.setOnClickListener(this::goToLogin);

    }

    public void goToGPS(View view) {
        Intent intent = new Intent(getActivity().getApplicationContext(), GPSActivity.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void goToAr(View view) {
        Intent intent = new Intent(requireActivity().getApplicationContext(), ARActivity.class);
        startActivity(intent);
    }

    public void goToWeb(View view) {
        Intent intent = new Intent (getActivity().getApplicationContext(), WebChooseActivity.class);
        startActivity(intent);
    }
}