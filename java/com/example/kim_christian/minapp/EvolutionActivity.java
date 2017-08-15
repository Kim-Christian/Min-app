package com.example.kim_christian.minapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by Kim-Christian on 2017-02-22.
 */

public class EvolutionActivity extends AppCompatActivity {
    NumberPicker level_np;
    NumberPicker exp_np;
    int level;
    int exp;
    int[] baseExp;
    int[] levelBaseExp;
    int step;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolution);
        Intent intent = getIntent();
        String value = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        level = 1;
        exp = 1;
        baseExp = new int[]{1000, 2000, 5000, 13000, 30000};
        levelBaseExp = new int[]{120, 240, 480, 960, 1920};
        step = 50;

        level_np = NumberPicker(R.id.level, 1, 5);
        exp_np = NumberPicker(R.id.exp, 1, 500);
        level_np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        level_np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                level = newVal;
                Calc(level, exp);
            }
        });
        exp_np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                exp = newVal;
                Calc(level, exp);
            }
        });
    }

    public void updateValues(int exp_to_next, int books_to_consume, int returned_books) {
        ((TextView) findViewById(R.id.exp_to_next)).setText(Integer.toString(exp_to_next));
        ((TextView) findViewById(R.id.books_to_consume)).setText(Integer.toString(books_to_consume));
        ((TextView) findViewById(R.id.returned_books)).setText(Integer.toString(returned_books));
    }

    public void Calc(int level, int exp) {
        int exp_to_next = baseExp[level-1] + level * levelBaseExp[level-1] - exp;
        int books_to_consume = exp_to_next / step;
        int  returned_books = level * 2 * step + exp / step;

        updateValues(exp_to_next, books_to_consume, returned_books);
    }

    public NumberPicker NumberPicker(int numberPicker_id, int min, int max) {
        NumberPicker numberPicker = (NumberPicker) findViewById(numberPicker_id);
        numberPicker.setMinValue(min);
        numberPicker.setMaxValue(max);
        numberPicker.setWrapSelectorWheel(true);
        return numberPicker;
    }
}
