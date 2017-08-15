package com.example.kim_christian.minapp;

/**
 * Created by Kim-Christian on 2017-02-17.
 */

abstract class Booster {
    private String  name;
    private int     level;
    private int     MIN_LEVEL = 1;
    private int     MAX_LEVEL;
    private int     thumbnail;

    private double BASE_ATK_BOOST;
    private double BASE_ATK_SPD_BOOST;
    private double BASE_DMG_BOOST;
    private double BASE_HP_BOOST;
    private double BASE_MOV_SPD_BOOST;

    private double[] ATK_MULTIPLIER;
    private double[] ATK_SPD_MULTIPLIER;
    private double[] DMG_MULTIPLIER;
    private double[] HP_MULTIPLIER;
    private double[] MOV_SPD_MULTIPLIER;

    private double ATK_BOOST;
    private double ATK_SPD_BOOST;
    private double DMG_BOOST;
    private double HP_BOOST;
    private double MOV_SPD_BOOST;

    Booster(String name, int level, int thumbnail) {
        this.name       = name;
        this.level      = level;
        this.thumbnail  = thumbnail;
    }
    Booster() { this("Booster_name", 1, 0); }            // 1 setBaseAttrBoost 2 setMultipliers 3 setLevel

    protected String    getName()       { return this.name; }
    protected int       getLevel()      { return this.level; }
    protected int       getThumbnail()  { return this.thumbnail; }

    protected void setName(String new_name)         { this.name = new_name; }
    protected void setLevel(int new_level)          { this.level = new_level; updateAttrBoost(); }
    protected void setThumbnail(int new_thumbnail)  { this.thumbnail = new_thumbnail; }

    protected double getATK_BOOST()     { return this.ATK_BOOST; }
    protected double getATK_SPD_BOOST() { return this.ATK_SPD_BOOST; }
    protected double getDMG_BOOST()     { return this.DMG_BOOST; }
    protected double getHP_BOOST()      { return this.HP_BOOST; }
    protected double getMOV_SPD_BOOST() { return this.MOV_SPD_BOOST; }

    protected void setBaseAttrBoost(double atk, double atk_spd, double dmg, double hp, double mov_spd) {
        this.BASE_ATK_BOOST     = atk;
        this.BASE_ATK_SPD_BOOST = atk_spd;
        this.BASE_DMG_BOOST     = dmg;
        this.BASE_HP_BOOST      = hp;
        this.BASE_MOV_SPD_BOOST = mov_spd;
    }

    protected void setMultipliers(double[] atk, double[] atk_spd, double[] dmg, double[] hp, double[] mov_spd) {
        this.ATK_MULTIPLIER     = atk;
        this.ATK_SPD_MULTIPLIER = atk_spd;
        this.DMG_MULTIPLIER     = dmg;
        this.HP_MULTIPLIER      = hp;
        this.MOV_SPD_MULTIPLIER = mov_spd;

        this.MAX_LEVEL = ATK_MULTIPLIER.length;
    }

    private void updateAttrBoost() {
        this.ATK_BOOST      = BASE_ATK_BOOST        * ATK_MULTIPLIER    [ this.level - 1 ];
        this.ATK_SPD_BOOST  = BASE_ATK_SPD_BOOST    * ATK_SPD_MULTIPLIER[ this.level - 1 ];
        this.DMG_BOOST      = BASE_DMG_BOOST        * DMG_MULTIPLIER    [ this.level - 1 ];
        this.HP_BOOST       = BASE_HP_BOOST         * HP_MULTIPLIER     [ this.level - 1 ];
        this.MOV_SPD_BOOST  = BASE_MOV_SPD_BOOST    * MOV_SPD_MULTIPLIER[ this.level - 1 ];
    }
}