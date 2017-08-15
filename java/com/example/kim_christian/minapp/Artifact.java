package com.example.kim_christian.minapp;

/**
 * Created by Kim-Christian on 2017-02-17.
 */

public class Artifact extends Booster {

    Artifact(String name, int level, int thumbnail) {
        super(name, level, thumbnail);
    }
    Artifact(String name, int thumbnail){ this(name, 1, thumbnail); }
    Artifact(String name)  { this(name, R.drawable.logo); }
    Artifact()                          { this("Artifact_name"); }
}
