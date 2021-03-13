package ru.gisupov.neuroland.main_ui.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.gisupov.neuroland.R;
import ru.gisupov.neuroland.main_ui.ARActivity;
import ru.gisupov.neuroland.main_ui.GPSActivity;
import ru.gisupov.neuroland.main_ui.WebChooseActivity;

public class MainFragment extends Fragment implements View.OnClickListener {

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

//        Button login = (Button) getView().findViewById(R.id.log_but);
//        login.setOnClickListener(this::goToLogin);
    }

    public void goToGPS(View view) {
        Intent intent = new Intent(requireActivity().getApplicationContext(), GPSActivity.class);
        startActivity(intent);
    }

//    public void goToLogin(View view) {
//        Intent intent = new Intent(requireActivity().getApplicationContext(), LoginActivity.class);
//        startActivity(intent);
//    }

    public void goToAr(View view) {
        Intent intent = new Intent(requireActivity().getApplicationContext(), ARActivity.class);
        startActivity(intent);
    }

    public void goToWeb(View view) {
        Intent intent = new Intent (requireActivity().getApplicationContext(), WebChooseActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}