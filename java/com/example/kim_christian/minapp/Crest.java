package com.example.kim_christian.minapp;

/**
 * Created by Kim-Christian on 2017-02-17.
 */

public class Crest extends Booster {

    Crest(String name, int level, int thumbnail) {
        super(name, level, thumbnail);
    }
    Crest(String name, int thumbnail){ this(name, 1, thumbnail); }
    Crest(String name)  { this(name, R.drawable.logo); }
    Crest()                          { this("Crest_name"); }
}
