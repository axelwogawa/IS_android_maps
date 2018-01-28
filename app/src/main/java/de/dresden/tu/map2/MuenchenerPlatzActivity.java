package de.dresden.tu.map2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MuenchenerPlatzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muenchener_platz);
        Window w = getWindow();
        w.setTitle("Münchener Platz");
    }
}
