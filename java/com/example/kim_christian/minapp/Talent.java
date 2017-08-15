package com.example.kim_christian.minapp;

/**
 * Created by Kim-Christian on 2017-02-17.
 */

public class Talent extends Booster {

    Talent(String name, int level, int thumbnail) {
        super(name, level, thumbnail);
    }
    Talent(String name, int thumbnail){ this(name, 1, thumbnail); }
    Talent(String name)  { this(name, R.drawable.logo); }
    Talent()                          { this("Talent_name"); }
}
