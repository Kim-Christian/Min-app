package com.example.kim_christian.minapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.kim_christian.minapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void heroStats(View view) {
        Intent intent = new Intent(this, HeroStatsActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "test");
        startActivity(intent);
    }

    public void evolution(View view) {
        Intent intent = new Intent(this, EvolutionActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "test");
        startActivity(intent);
    }
}
