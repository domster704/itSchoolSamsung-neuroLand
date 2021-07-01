package ru.gisupov.neuroland.main_ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import ru.gisupov.neuroland.R;

/**
 * Активность с AR и выбором моделей
 */
public class ARActivity extends AppCompatActivity implements View.OnClickListener {

    private final ArrayList<HousePicture> imageViews = new ArrayList<>();
    private TextView modelTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar);

        changeStatusBarColor();

        modelTV = findViewById(R.id.model);

        int[] imgViewsID = {R.id.imageHouse1, R.id.imageHouse2, R.id.imageHouse3};
        String[] nameImages = {"House 1", "House 2", "House 3"};

        for (int i = 0; i < imgViewsID.length; i++) {
            imageViews.add(new HousePicture(findViewById(imgViewsID[i]), nameImages[i]));
            imageViews.get(i).imageView.setOnClickListener(this);
        }
    }

    /**
     * Выбирается модель на которую нажали
     *
     * @param v View, на которую нажали
     */
    @Override
    public void onClick(View v) {
        for (int i = 0; i < imageViews.size(); i++) {
            if (v == imageViews.get(i).imageView) {
                modelTV.setText(imageViews.get(i).name);
            }
        }
    }

    /**
     * Меняет цвет строки состояния (строка уведомлений)
     */
    private void changeStatusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background_app_color));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    static class HousePicture {
        public ImageView imageView;
        public String name;

        public HousePicture(ImageView iv, String name) {
            this.imageView = iv;
            this.name = name;
        }
    }
}