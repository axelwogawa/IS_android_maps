package de.dresden.tu.map2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class NuerenbergerPlatzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuerenberger_platz);
        Window w = getWindow();
        w.setTitle("Nürenberger Platz");
    }
}
