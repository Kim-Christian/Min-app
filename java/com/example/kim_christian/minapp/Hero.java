package com.example.kim_christian.minapp;

/**
 * Created by Kim-Christian on 2017-02-16.
 */

public class Hero {
    private String  name;
    private int     level;
    private int     MIN_LEVEL = 1;
    private int     MAX_LEVEL = 200;
    private Skill   skill;
    private Talent  talent;
    private Crest  crest;
    private Artifact  artifact;
    private int     thumbnail;

    private int BASE_ATK;
    private int BASE_ATK_SPD;
    private int BASE_DMG;
    private int BASE_HP;
    private int BASE_MOV_SPD;

    private double[] ATK_MULTIPLIER;
    private double[] ATK_SPD_MULTIPLIER;
    private double[] DMG_MULTIPLIER;
    private double[] HP_MULTIPLIER;
    private double[] MOV_SPD_MULTIPLIER;

    private int ATK;
    private int ATK_SPD;
    private int DMG;
    private int HP;
    private int MOV_SPD;

    public Hero(String name, Skill skill, Talent talent, Crest crest, Artifact artifact, int thumbnail) {
        this.name       = name;
        this.level      = 1;
        this.skill      = skill;
        this.talent     = talent;
        this.crest      = crest;
        this.artifact   = artifact;
        this.thumbnail  = thumbnail;
    }

    public Hero(String name, Skill skill, int thumbnail) {
        this(name, skill, new Talent(), new Crest(), new Artifact(), thumbnail);
    }

    public Hero(String name, Skill skill) {
        this(name, skill, R.drawable.logo);
    }

    public Hero() {
        this("Name", new Skill());
    }

    protected String   getName()       { return this.name; }
    protected String   getSkillName()       { return this.skill.getName(); }
    protected int      getLevel()      { return this.level; }
    protected Skill    getSkill()      { return this.skill; }
    protected Talent   getTalent()     { return this.talent; }
    protected Crest   getCrest()      { return this.crest; }
    protected Artifact   getArtifact()   { return this.artifact; }
    protected int      getThumbnail()  { return this.thumbnail; }

    protected int      getSkillLevel()  { return this.skill.getLevel(); }
    protected int      getTalentLevel()  { return this.talent.getLevel(); }
    protected int      getCrestLevel()  { return this.crest.getLevel(); }
    protected int      getArtifactLevel()  { return this.artifact.getLevel(); }

    protected int      getSkillThumbnail()  { return this.skill.getThumbnail(); }
    protected int      getTalentThumbnail()  { return this.talent.getThumbnail(); }
    protected int      getCrestThumbnail()  { return this.crest.getThumbnail(); }
    protected int      getArtifactThumbnail()  { return this.artifact.getThumbnail(); }

    protected void setName(String new_name)        { this.name      = new_name; }
    protected void setLevel(int new_level)         { this.level     = new_level;    updateAttr(); }
    protected void setSkill(Skill new_skill)       { this.skill     = new_skill; }
    protected void setTalent(Talent new_talent)    { this.talent    = new_talent;  }
    protected void setCrest(Crest new_crest)      { this.crest     = new_crest;  }
    protected void setArtifact(Artifact new_artifact){ this.artifact  = new_artifact;  }
    protected void setThumbnail(int new_thumbnail) { this.thumbnail = new_thumbnail; }

    protected void setSkillLevel(int new_skill_level)   { this.skill.setLevel(new_skill_level);             updateAttr(); }
    protected void setTalentLevel(int new_talent_level)   { this.talent.setLevel(new_talent_level);         updateAttr(); }
    protected void setCrestLevel(int new_crest_level)   { this.crest.setLevel(new_crest_level);             updateAttr(); }
    protected void setArtifactLevel(int new_artifact_level)   { this.artifact.setLevel(new_artifact_level); updateAttr(); }

    protected int getATK()     { return this.ATK; }
    protected int getATK_SPD() { return this.ATK_SPD; }
    protected int getDMG()     { return this.DMG; }
    protected int getHP()      { return this.HP; }
    protected int getMOV_SPD() { return this.MOV_SPD; }

    protected void setBaseAttr(int atk, int atk_spd, int dmg, int hp, int mov_spd) {
        this.BASE_ATK       = atk;
        this.BASE_ATK_SPD   = atk_spd;
        this.BASE_DMG       = dmg;
        this.BASE_HP        = hp;
        this.BASE_MOV_SPD   = mov_spd;
    }

    protected void setSkillBaseBoost(int atk, int atk_spd, int dmg, int hp, int mov_spd) {
        this.skill.setBaseAttrBoost(atk, atk_spd, dmg, hp, mov_spd);
    }

    protected void setTalentBaseBoost(int atk, int atk_spd, int dmg, int hp, int mov_spd) {
        this.talent.setBaseAttrBoost(atk, atk_spd, dmg, hp, mov_spd);
    }

    protected void setCrestBaseBoost(int atk, int atk_spd, int dmg, int hp, int mov_spd) {
        this.crest.setBaseAttrBoost(atk, atk_spd, dmg, hp, mov_spd);
    }

    protected void setArtifactBaseBoost(int atk, int atk_spd, int dmg, int hp, int mov_spd) {
        this.artifact.setBaseAttrBoost(atk, atk_spd, dmg, hp, mov_spd);
    }

    protected void setMultipliers(double[] atk, double[] atk_spd, double[] dmg, double[] hp, double[] mov_spd) {
        this.ATK_MULTIPLIER     = atk;
        this.ATK_SPD_MULTIPLIER = atk_spd;
        this.DMG_MULTIPLIER     = dmg;
        this.HP_MULTIPLIER      = hp;
        this.MOV_SPD_MULTIPLIER = mov_spd;
    }

    protected void setSkillMultipliers(double[] atk, double[] atk_spd, double[] dmg, double[] hp, double[] mov_spd) {
        this.skill.setMultipliers(atk, atk_spd, dmg, hp, mov_spd);
    }

    protected void setTalentMultipliers(double[] atk, double[] atk_spd, double[] dmg, double[] hp, double[] mov_spd) {
        this.talent.setMultipliers(atk, atk_spd, dmg, hp, mov_spd);
    }

    protected void setCrestMultipliers(double[] atk, double[] atk_spd, double[] dmg, double[] hp, double[] mov_spd) {
        this.crest.setMultipliers(atk, atk_spd, dmg, hp, mov_spd);
    }

    protected void setArtifactMultipliers(double[] atk, double[] atk_spd, double[] dmg, double[] hp, double[] mov_spd) {
        this.artifact.setMultipliers(atk, atk_spd, dmg, hp, mov_spd);
    }

    private void updateAttr() {
        try {
            this.ATK = (int) (this.BASE_ATK * this.ATK_MULTIPLIER[this.level - 1]) + (int) this.skill.getATK_BOOST()
                    + (int) this.talent.getATK_BOOST() + (int) this.crest.getATK_BOOST() + (int) this.artifact.getATK_BOOST();

            this.ATK_SPD = (int) (this.BASE_ATK_SPD * ATK_SPD_MULTIPLIER[this.level - 1]) + (int) this.skill.getATK_SPD_BOOST()
                    + (int) this.talent.getATK_SPD_BOOST() + (int) this.crest.getATK_SPD_BOOST() + (int) this.artifact.getATK_SPD_BOOST();

            this.DMG = (int) (this.BASE_DMG * DMG_MULTIPLIER[this.level - 1]) + (int) this.skill.getDMG_BOOST()
                    + (int) this.talent.getDMG_BOOST() + (int) this.crest.getDMG_BOOST() + (int) this.artifact.getDMG_BOOST();

            this.HP = (int) (this.BASE_HP * HP_MULTIPLIER[this.level - 1]) + (int) this.skill.getHP_BOOST()
                    + (int) this.talent.getHP_BOOST() + (int) this.crest.getHP_BOOST() + (int) this.artifact.getHP_BOOST();

            this.MOV_SPD = (int) (this.BASE_MOV_SPD * MOV_SPD_MULTIPLIER[this.level - 1]) + (int) this.skill.getMOV_SPD_BOOST()
                    + (int) this.talent.getMOV_SPD_BOOST() + (int) this.crest.getMOV_SPD_BOOST() + (int) this.artifact.getMOV_SPD_BOOST();
        }
        catch(Exception e){}
    }
}
