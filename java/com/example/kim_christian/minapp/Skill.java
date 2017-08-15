package com.example.kim_christian.minapp;

/**
 * Created by Kim-Christian on 2017-02-17.
 */

public class Skill extends Booster {
    private String attachedTo;

    Skill(String name, int level, int thumbnail, String hero) {
        super(name, level, thumbnail);
        this.attachedTo = hero;
    }
    Skill(String name, int thumbnail, String hero){ this(name, 1, thumbnail, hero); }
    Skill(String name, String hero)  { this(name, R.drawable.logo, hero); }
    Skill()                          { this("Skill_name", "Attached_to_hero"); }

    protected String getAttachedTo() { return this.attachedTo; }
    protected void setAttachedTo(String hero) { this.attachedTo = hero; }
}
