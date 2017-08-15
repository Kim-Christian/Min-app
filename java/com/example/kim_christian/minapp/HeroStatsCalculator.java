package com.example.kim_christian.minapp;

/**
 * Created by Kim-Christian on 2017-02-17.
 */

public class HeroStatsCalculator {

    private Hero hero;

    public HeroStatsCalculator() {
        this.hero        = new Hero();
    }

    protected void updateHero(String hero_name, int thumbnail, int[] base_attributes, double[][] multipliers) {
        this.hero        = new Hero(hero_name, new Skill(), thumbnail);
        int BASE_ATK     = base_attributes[0];
        int BASE_ATK_SPD = base_attributes[1];
        int BASE_DMG     = base_attributes[2];
        int BASE_HP      = base_attributes[3];
        int BASE_MOV_SPD = base_attributes[4];
        double[] ATK_MULTIPLIER     = multipliers[0];
        double[] ATK_SPD_MULTIPLIER = multipliers[1];
        double[] DMG_MULTIPLIER     = multipliers[2];
        double[] HP_MULTIPLIER      = multipliers[3];
        double[] MOV_SPD_MULTIPLIER = multipliers[4];

        this.hero.setBaseAttr(BASE_ATK, BASE_ATK_SPD, BASE_DMG, BASE_HP, BASE_MOV_SPD);
        this.hero.setMultipliers(ATK_MULTIPLIER, ATK_SPD_MULTIPLIER, DMG_MULTIPLIER, HP_MULTIPLIER, MOV_SPD_MULTIPLIER);
    }

    protected void updateHeroLevel(int level) {
        this.hero.setLevel(level);
    }

    protected void updateSkillLevel(int level) {
        this.hero.setSkillLevel(level);
    }

    protected void updateTalentLevel(int level) {
        this.hero.setTalentLevel(level);
    }

    protected void updateCrestLevel(int level) {
        this.hero.setCrestLevel(level);
    }

    protected void updateArtifactLevel(int level) {
        this.hero.setArtifactLevel(level);
    }

    protected void updateSkill(String skill_name, int thumbnail, int[] base_boosts, double[][] multipliers) {
        this.hero.setSkill(new Skill(skill_name, thumbnail, this.hero.getName()));
        int BASE_ATK     = base_boosts[0];
        int BASE_ATK_SPD = base_boosts[1];
        int BASE_DMG     = base_boosts[2];
        int BASE_HP      = base_boosts[3];
        int BASE_MOV_SPD = base_boosts[4];
        double[] ATK_MULTIPLIER     = multipliers[0];
        double[] ATK_SPD_MULTIPLIER = multipliers[1];
        double[] DMG_MULTIPLIER     = multipliers[2];
        double[] HP_MULTIPLIER      = multipliers[3];
        double[] MOV_SPD_MULTIPLIER = multipliers[4];

        this.hero.setSkillBaseBoost(BASE_ATK, BASE_ATK_SPD, BASE_DMG, BASE_HP, BASE_MOV_SPD);
        this.hero.setSkillMultipliers(ATK_MULTIPLIER, ATK_SPD_MULTIPLIER, DMG_MULTIPLIER, HP_MULTIPLIER, MOV_SPD_MULTIPLIER);
    }

    protected void updateTalent(String talent_name, int thumbnail, int[] base_boosts, double[][] multipliers) {
        this.hero.setTalent(new Talent(talent_name, thumbnail));
        int BASE_ATK     = base_boosts[0];
        int BASE_ATK_SPD = base_boosts[1];
        int BASE_DMG     = base_boosts[2];
        int BASE_HP      = base_boosts[3];
        int BASE_MOV_SPD = base_boosts[4];
        double[] ATK_MULTIPLIER     = multipliers[0];
        double[] ATK_SPD_MULTIPLIER = multipliers[1];
        double[] DMG_MULTIPLIER     = multipliers[2];
        double[] HP_MULTIPLIER      = multipliers[3];
        double[] MOV_SPD_MULTIPLIER = multipliers[4];

        this.hero.setTalentBaseBoost(BASE_ATK, BASE_ATK_SPD, BASE_DMG, BASE_HP, BASE_MOV_SPD);
        this.hero.setTalentMultipliers(ATK_MULTIPLIER, ATK_SPD_MULTIPLIER, DMG_MULTIPLIER, HP_MULTIPLIER, MOV_SPD_MULTIPLIER);
    }

    protected void updateCrest(String crest_name, int thumbnail, int[] base_boosts, double[][] multipliers) {
        this.hero.setCrest(new Crest(crest_name, thumbnail));
        int BASE_ATK     = base_boosts[0];
        int BASE_ATK_SPD = base_boosts[1];
        int BASE_DMG     = base_boosts[2];
        int BASE_HP      = base_boosts[3];
        int BASE_MOV_SPD = base_boosts[4];
        double[] ATK_MULTIPLIER     = multipliers[0];
        double[] ATK_SPD_MULTIPLIER = multipliers[1];
        double[] DMG_MULTIPLIER     = multipliers[2];
        double[] HP_MULTIPLIER      = multipliers[3];
        double[] MOV_SPD_MULTIPLIER = multipliers[4];

        this.hero.setCrestBaseBoost(BASE_ATK, BASE_ATK_SPD, BASE_DMG, BASE_HP, BASE_MOV_SPD);
        this.hero.setCrestMultipliers(ATK_MULTIPLIER, ATK_SPD_MULTIPLIER, DMG_MULTIPLIER, HP_MULTIPLIER, MOV_SPD_MULTIPLIER);
    }

    protected void updateArtifact(String artifact_name, int thumbnail, int[] base_boosts, double[][] multipliers) {
        this.hero.setArtifact(new Artifact(artifact_name, thumbnail));
        int BASE_ATK     = base_boosts[0];
        int BASE_ATK_SPD = base_boosts[1];
        int BASE_DMG     = base_boosts[2];
        int BASE_HP      = base_boosts[3];
        int BASE_MOV_SPD = base_boosts[4];
        double[] ATK_MULTIPLIER     = multipliers[0];
        double[] ATK_SPD_MULTIPLIER = multipliers[1];
        double[] DMG_MULTIPLIER     = multipliers[2];
        double[] HP_MULTIPLIER      = multipliers[3];
        double[] MOV_SPD_MULTIPLIER = multipliers[4];

        this.hero.setArtifactBaseBoost(BASE_ATK, BASE_ATK_SPD, BASE_DMG, BASE_HP, BASE_MOV_SPD);
        this.hero.setArtifactMultipliers(ATK_MULTIPLIER, ATK_SPD_MULTIPLIER, DMG_MULTIPLIER, HP_MULTIPLIER, MOV_SPD_MULTIPLIER);
    }

    protected String getHeroName() {
        return this.hero.getName();
    }

    protected int[] getHeroAttr() {
        int atk = this.hero.getATK();
        int atk_spd = this.hero.getATK_SPD();
        int dmg = this.hero.getDMG();
        int hp = this.hero.getHP();
        int mov_spd = this.hero.getMOV_SPD();

        return new int[]{atk, atk_spd, dmg, hp, mov_spd};
    }

    protected int getHeroLevel() {
        return this.hero.getLevel();
    }

    protected String getSkillName() {
        return this.hero.getSkillName();
    }

    protected int getSkillLevel() {
        return this.hero.getSkillLevel();
    }

    protected int getTalentLevel() {
        return this.hero.getTalentLevel();
    }

    protected int getCrestLevel() {
        return this.hero.getCrestLevel();
    }

    protected int getArtifactLevel() {
        return this.hero.getArtifactLevel();
    }

    protected int getHeroThumbnail() {
        return this.hero.getThumbnail();
    }

    protected int getSkillThumbnail() {
        return this.hero.getSkillThumbnail();
    }

    protected int getTalentThumbnail() {
        return this.hero.getTalentThumbnail();
    }

    protected int getCrestThumbnail() {
        return this.hero.getCrestThumbnail();
    }

    protected int getArtifactThumbnail() {
        return this.hero.getArtifactThumbnail();
    }
}
