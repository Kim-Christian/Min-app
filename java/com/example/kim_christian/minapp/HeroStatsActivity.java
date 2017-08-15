package com.example.kim_christian.minapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim-Christian on 2017-01-31.
 */

public class HeroStatsActivity extends AppCompatActivity
{
    private BottomSheetBehavior mBottomSheetBehavior;
    String bottomSheetState;
    int expPos;
    int colPos;
    LayoutInflater vi;
    Card hero_card;
    Card skill_card;
    Card talent_card;
    Card crest_card;
    Card artifact_card;
    String[] hero_list;
    String[] skill_list;
    String[] talent_list;
    String[] crest_list;
    String[] artifact_list;
    int[] hero_level_list;
    int[] skill_level_list;
    int[] talent_level_list;
    int[] crest_level_list;
    int[] artifact_level_list;
    List<String> card_list = new ArrayList<>();
    int[] pd_base_attr;
    int[] sk_base_attr;
    HeroStatsCalculator calc;
    FloatingActionButton edit_fab;
    FloatingActionButton add_fab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_stats);
        Intent intent = getIntent();
        String value = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        hero_list = getResources().getStringArray(R.array.heroes);
        skill_list = getResources().getStringArray(R.array.skills);
        talent_list = getResources().getStringArray(R.array.talents);
        crest_list = getResources().getStringArray(R.array.crests);
        artifact_list = getResources().getStringArray(R.array.artifacts);

        hero_level_list = intArray(1, 5);
        skill_level_list = intArray(1, 10);
        talent_level_list = intArray(1, 5);
        crest_level_list = intArray(1, 5);
        artifact_level_list = intArray(1, 5);

        pd_base_attr = getResources().getIntArray(R.array.pd_base_attr);
        sk_base_attr = getResources().getIntArray(R.array.sk_base_attr);

        hero_card = new Card("hero", vi);
        skill_card = new Card("skill", vi);
        talent_card = new Card("talent", vi);
        crest_card = new Card("crest", vi);
        artifact_card = new Card("artifact", vi);

        CardView hero_cardView = hero_card.getCardView();

        addView(hero_cardView, R.id.cards, 0);

        card_list.add(skill_card.getSubtitle());
        card_list.add(talent_card.getSubtitle());
        card_list.add(crest_card.getSubtitle());
        card_list.add(artifact_card.getSubtitle());

        onClickCard(hero_card);
        onClickCard(skill_card);
        onClickCard(talent_card);
        onClickCard(crest_card);
        onClickCard(artifact_card);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        final View bottomSheet = findViewById( R.id.bottom_sheet );
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(1000);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetState = "collapsed";
        colPos = bottomSheet.getTop();
        expPos = 0;

        edit_fab = (FloatingActionButton) findViewById(R.id.edit_fab);
        add_fab = (FloatingActionButton) findViewById(R.id.add_fab);

        add_fab.hide();

        BottomSheetBehavior.from(findViewById(R.id.bottom_sheet))
                .setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                    @Override
                    public void onStateChanged(@NonNull View bottomSheet, int newState) {
                        switch (newState) {
                            case BottomSheetBehavior.STATE_EXPANDED:
                                bottomSheetState = "expanded";
                                expPos = bottomSheet.getTop();
                                add_fab.show();
                                break;
                            case BottomSheetBehavior.STATE_COLLAPSED:
                                bottomSheetState = "collapsed";
                                colPos = bottomSheet.getTop();
                                add_fab.hide();
                                break;
                        }
                    }

                    @Override
                    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                        switch(bottomSheetState) {
                            case "collapsed" : if (bottomSheet.getTop() < colPos) {
                                    add_fab.show();
                                } break;

                            case "expanded" : if (bottomSheet.getTop() > expPos) {
                                    add_fab.hide();
                                } break;
                        }
                    }
                });

        edit_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCardDialog();
            }
        });

        calc = new HeroStatsCalculator();
    }

    public int[] intArray(int start, int end) {
        int[] array = new int[end-start+1];
        for (int i = start; i <= end; i++) {
            array[i-start] = i;
        }
        return array;
    }

    public String[] intToString(int[] intArray) {
        String[] strArray = new String[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            strArray[i] = Integer.toString(intArray[i]);
        }
        return strArray;
    }

    public void onClickCard(final Card card) {
        final String subtitle = card.getSubtitle();
        final CardView cardView = card.getCardView();
        TextView level = ((TextView) cardView.findViewById(R.id.card_action1));
        final TextView remove = ((TextView) cardView.findViewById(R.id.card_action2));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(subtitle) {
                    case "Hero" : showHeroDialog(); break;
                    case "Talent" : showTalentDialog(); break;
                    case "Crest" : showCrestDialog(); break;
                    case "Artifact" : showArtifactDialog(); break;
                    default : break;
                }
            }
        });
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (subtitle) {
                    case "Hero":
                        showHeroLevelDialog();
                        break;
                    case "Skill":
                        showSkillLevelDialog();
                        break;
                    case "Talent":
                        showTalentLevelDialog();
                        break;
                    case "Crest":
                        showCrestLevelDialog();
                        break;
                    case "Artifact":
                        showArtifactLevelDialog();
                        break;
                    default:
                        break;
                }
            }
        });
        level.setEnabled(false);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(subtitle) {
                    case "Hero":
                        calc.updateHero("Choose hero", 0, new int[]{0, 0, 0, 0, 0}, new double[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}});
                        calc.updateHeroLevel(1);
                        updateCard(card, "Choose hero", 0);
                        break;
                    case "Skill":
                        calc.updateSkill(card.get_title(), 0, new int[]{0, 0, 0, 0, 0}, new double[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});
                        calc.updateSkillLevel(1);
                        updateCard(card, card.get_title(), 0);
                        break;
                    case "Talent":
                        calc.updateTalent("Choose talent", 0, new int[]{0, 0, 0, 0, 0}, new double[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}});
                        calc.updateTalentLevel(1);
                        updateCard(card, "Choose talent", 0);
                        break;
                    case "Crest":
                        calc.updateCrest("Choose crest", 0, new int[]{0, 0, 0, 0, 0}, new double[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}});
                        calc.updateCrestLevel(1);
                        updateCard(card, "Choose crest", 0);
                        break;
                    case "Artifact":
                        calc.updateArtifact("Choose artifact", 0, new int[]{0, 0, 0, 0, 0}, new double[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}});
                        calc.updateArtifactLevel(1);
                        updateCard(card, "Choose artifact", 0);
                        break;
                }
                updateCardLevel(card, 1);
                disableLevel(card);
                updateValues(calc.getHeroAttr());
                removeView(cardView.getId());
                card_list.add(subtitle);

                String[] cardArray = new String[ card_list.size() ];
                card_list.toArray( cardArray );
                if (cardArray.length == 1 && !bottomSheetState.equals("collapsed")) {
                    add_fab.show();
                }
            }
        });
    }

    public double[] stringToDouble(String[] str_array) {
        double[] values = new double[str_array.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = Double.parseDouble(str_array[i]);
        }
        return values;
    }

    public int[] getBaseAttr(String hero) {
        int[] values;
        switch(hero) {
            case "Pumpkin Duke" : values = getResources().getIntArray(R.array.pd_base_attr); break;
            case "Skull Knight" : values = getResources().getIntArray(R.array.sk_base_attr); break;
            default: values = new int[]{0, 0, 0, 0, 0}; break;
        }
        return values;
    }

    public int[] getSkillBaseBoost(String skill) {
        int[] values;
        switch(skill) {
            case "Celebrate" : values = getResources().getIntArray(R.array.celebrate_base_attr); break;
            case "Vengeful Sword" : values = getResources().getIntArray(R.array.vs_base_attr); break;
            default: values = new int[]{0, 0, 0, 0, 0}; break;
        }
        return values;
    }

    public int[] getTalentBaseBoost(String talent) {
        int[] values;
        switch(talent) {
            case "Berserk" : values = getResources().getIntArray(R.array.berserk_base_attr); break;
            case "Life Drain" : values = getResources().getIntArray(R.array.ld_base_attr); break;
            default: values = new int[]{0, 0, 0, 0, 0}; break;
        }
        return values;
    }

    public int[] getCrestBaseBoost(String crest) {
        int[] values;
        switch(crest) {
            case "Tenacity" : values = getResources().getIntArray(R.array.tenacity_base_attr); break;
            case "Revive" : values = getResources().getIntArray(R.array.revive_base_attr); break;
            default: values = new int[]{0, 0, 0, 0, 0}; break;
        }
        return values;
    }

    public int[] getArtifactBaseBoost(String artifact) {
        int[] values;
        switch(artifact) {
            case "Blitz Scroll" : values = getResources().getIntArray(R.array.bs_base_attr); break;
            case "Goblet of Life" : values = getResources().getIntArray(R.array.gol_base_attr); break;
            default: values = new int[]{0, 0, 0, 0, 0}; break;
        }
        return values;
    }

    public double[][] getHeroMultipliers(String hero) {
        double[][] values = new double[5][];
        System.out.println(hero);
        switch(hero) {
            case "Pumpkin Duke" :
                values[0] = stringToDouble(getResources().getStringArray(R.array.pd_atk_mult));
                values[1] = stringToDouble(getResources().getStringArray(R.array.pd_atk_spd_mult));
                values[2] = stringToDouble(getResources().getStringArray(R.array.pd_dmg_mult));
                values[3] = stringToDouble(getResources().getStringArray(R.array.pd_hp_mult));
                values[4] = stringToDouble(getResources().getStringArray(R.array.pd_mov_spd_mult));
                break;
            case "Skull Knight" :
                values[0] = stringToDouble(getResources().getStringArray(R.array.sk_atk_mult));
                values[1] = stringToDouble(getResources().getStringArray(R.array.sk_atk_spd_mult));
                values[2] = stringToDouble(getResources().getStringArray(R.array.sk_dmg_mult));
                values[3] = stringToDouble(getResources().getStringArray(R.array.sk_hp_mult));
                values[4] = stringToDouble(getResources().getStringArray(R.array.sk_mov_spd_mult));
                break;
            default: values = new double[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        }
        return values;
    }

    public double[][] getSkillMultipliers(String skill) {
        double[][] values = new double[5][];
        System.out.println(skill);
        switch(skill) {
            case "Celebrate" :
                values[0] = stringToDouble(getResources().getStringArray(R.array.celebrate_atk_mult));
                values[1] = stringToDouble(getResources().getStringArray(R.array.celebrate_atk_spd_mult));
                values[2] = stringToDouble(getResources().getStringArray(R.array.celebrate_dmg_mult));
                values[3] = stringToDouble(getResources().getStringArray(R.array.celebrate_hp_mult));
                values[4] = stringToDouble(getResources().getStringArray(R.array.celebrate_mov_spd_mult));
                break;
            case "Vengeful Sword" :
                values[0] = stringToDouble(getResources().getStringArray(R.array.vs_atk_mult));
                values[1] = stringToDouble(getResources().getStringArray(R.array.vs_atk_spd_mult));
                values[2] = stringToDouble(getResources().getStringArray(R.array.vs_dmg_mult));
                values[3] = stringToDouble(getResources().getStringArray(R.array.vs_hp_mult));
                values[4] = stringToDouble(getResources().getStringArray(R.array.vs_mov_spd_mult));
                break;
            default: values = new double[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        }
        return values;
    }

    public double[][] getTalentMultipliers(String talent) {
        double[][] values = new double[5][];
        switch(talent) {
            case "Berserk" :
                values[0] = stringToDouble(getResources().getStringArray(R.array.berserk_atk_mult));
                values[1] = stringToDouble(getResources().getStringArray(R.array.berserk_atk_spd_mult));
                values[2] = stringToDouble(getResources().getStringArray(R.array.berserk_dmg_mult));
                values[3] = stringToDouble(getResources().getStringArray(R.array.berserk_hp_mult));
                values[4] = stringToDouble(getResources().getStringArray(R.array.berserk_mov_spd_mult));
                break;
            case "Life Drain" :
                values[0] = stringToDouble(getResources().getStringArray(R.array.ld_atk_mult));
                values[1] = stringToDouble(getResources().getStringArray(R.array.ld_atk_spd_mult));
                values[2] = stringToDouble(getResources().getStringArray(R.array.ld_dmg_mult));
                values[3] = stringToDouble(getResources().getStringArray(R.array.ld_hp_mult));
                values[4] = stringToDouble(getResources().getStringArray(R.array.ld_mov_spd_mult));
                break;
            default: values = new double[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        }
        return values;
    }

    public double[][] getCrestMultipliers(String crest) {
    double[][] values = new double[5][];
    switch(crest) {
        case "Tenacity" :
            values[0] = stringToDouble(getResources().getStringArray(R.array.tenacity_atk_mult));
            values[1] = stringToDouble(getResources().getStringArray(R.array.tenacity_atk_spd_mult));
            values[2] = stringToDouble(getResources().getStringArray(R.array.tenacity_dmg_mult));
            values[3] = stringToDouble(getResources().getStringArray(R.array.tenacity_hp_mult));
            values[4] = stringToDouble(getResources().getStringArray(R.array.tenacity_mov_spd_mult));
            break;
        case "Revive" :
            values[0] = stringToDouble(getResources().getStringArray(R.array.revive_atk_mult));
            values[1] = stringToDouble(getResources().getStringArray(R.array.revive_atk_spd_mult));
            values[2] = stringToDouble(getResources().getStringArray(R.array.revive_dmg_mult));
            values[3] = stringToDouble(getResources().getStringArray(R.array.revive_hp_mult));
            values[4] = stringToDouble(getResources().getStringArray(R.array.revive_mov_spd_mult));
            break;
        default: values = new double[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
    }
    return values;
}

    public double[][] getArtifactMultipliers(String artifact) {
        double[][] values = new double[5][];
        switch(artifact) {
            case "Blitz Scroll" :
                values[0] = stringToDouble(getResources().getStringArray(R.array.bs_atk_mult));
                values[1] = stringToDouble(getResources().getStringArray(R.array.bs_atk_spd_mult));
                values[2] = stringToDouble(getResources().getStringArray(R.array.bs_dmg_mult));
                values[3] = stringToDouble(getResources().getStringArray(R.array.bs_hp_mult));
                values[4] = stringToDouble(getResources().getStringArray(R.array.bs_mov_spd_mult));
                break;
            case "Goblet of Life" :
                values[0] = stringToDouble(getResources().getStringArray(R.array.gol_atk_mult));
                values[1] = stringToDouble(getResources().getStringArray(R.array.gol_atk_spd_mult));
                values[2] = stringToDouble(getResources().getStringArray(R.array.gol_dmg_mult));
                values[3] = stringToDouble(getResources().getStringArray(R.array.gol_hp_mult));
                values[4] = stringToDouble(getResources().getStringArray(R.array.gol_mov_spd_mult));
                break;
            default: values = new double[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        }
        return values;
    }

    public void showNoticeDialog(final String view, String dialog_title, final String[] list) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialog_title)
                .setItems(list, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int thumbnail;
                        int level;

                        switch(view) {
                            case "hero":
                                String hero = hero_list[which];
                                String skill = skill_list[which];
                                int hero_thumbnail = R.drawable.pumpkin_duke;
                                int skill_thumbnail = 0;
                                if ("Choose hero".equals(hero_card.get_title()) && card_list.contains("Skill")) {
                                    addView(skill_card.getCardView(), R.id.cards, 5 - list.length);
                                    card_list.remove(card_list.indexOf("Skill"));
                                }
                                calc.updateHero(hero, hero_thumbnail, getBaseAttr(hero), getHeroMultipliers(hero));
                                calc.updateSkill(skill, skill_thumbnail, getSkillBaseBoost(skill), getSkillMultipliers(skill));
                                calc.updateHeroLevel(1);
                                calc.updateSkillLevel(1);
                                updateCard(skill_card, skill, calc.getSkillThumbnail());
                                updateCard(hero_card, hero, calc.getHeroThumbnail());
                                updateCardLevel(hero_card, calc.getHeroLevel());
                                updateCardLevel(skill_card, calc.getSkillLevel());
                                enableLevel(hero_card);
                                enableLevel(skill_card);
                                break;
                            case "talent":
                                String talent = talent_list[which];
                                thumbnail = 0;
                                calc.updateTalent(talent, thumbnail, getTalentBaseBoost(talent), getTalentMultipliers(talent));
                                calc.updateTalentLevel(1);
                                updateCard(talent_card, talent, calc.getTalentThumbnail());
                                updateCardLevel(talent_card, calc.getTalentLevel());
                                enableLevel(talent_card);
                                break;
                            case "crest":
                                String crest = crest_list[which];
                                thumbnail = 0;
                                calc.updateCrest(crest, thumbnail, getCrestBaseBoost(crest), getCrestMultipliers(crest));
                                calc.updateCrestLevel(1);
                                updateCard(crest_card, crest, calc.getCrestThumbnail());
                                updateCardLevel(crest_card, calc.getCrestLevel());
                                enableLevel(crest_card);
                                break;
                            case "artifact":
                                String artifact = artifact_list[which];
                                thumbnail = 0;
                                calc.updateArtifact(artifact, thumbnail, getArtifactBaseBoost(artifact), getArtifactMultipliers(artifact));
                                calc.updateArtifactLevel(1);
                                updateCard(artifact_card, artifact, calc.getArtifactThumbnail());
                                updateCardLevel(artifact_card, calc.getArtifactLevel());
                                enableLevel(artifact_card);
                                break;
                            case "hero level":
                                level = hero_level_list[which];
                                calc.updateHeroLevel(level);
                                updateCardLevel(hero_card, level);
                                break;
                            case "skill level":
                                skill = calc.getSkillName();
                                level = skill_level_list[which];
                                calc.updateSkill(skill, calc.getSkillThumbnail(), getSkillBaseBoost(skill), getSkillMultipliers(skill));
                                calc.updateSkillLevel(level);
                                updateCardLevel(skill_card, level);
                                break;
                            case "talent level":
                                level = talent_level_list[which];
                                calc.updateTalentLevel(level);
                                updateCardLevel(talent_card, level);
                                break;
                            case "crest level":
                                level = crest_level_list[which];
                                calc.updateCrestLevel(level);
                                updateCardLevel(crest_card, level);
                                break;
                            case "artifact level":
                                level = artifact_level_list[which];
                                calc.updateArtifactLevel(level);
                                updateCardLevel(artifact_card, level);
                                break;
                            case "add card":
                                String card = list[which];
                                View view = new View(getBaseContext());
                                switch(card) {
                                    case "Hero"     : view = hero_card.getCardView();                               break;
                                    case "Skill"    : view = skill_card.getCardView();
                                        if (!"Choose hero".equals(hero_card.get_title())){ enableLevel(skill_card); }
                                        skill = calc.getSkillName();
                                        calc.updateSkill(skill, calc.getSkillThumbnail(), getSkillBaseBoost(skill), getSkillMultipliers(skill));
                                        calc.updateSkillLevel(1);
                                        break;
                                    case "Talent"   : view = talent_card.getCardView();                             break;
                                    case "Crest"    : view = crest_card.getCardView();                              break;
                                    case "Artifact" : view = artifact_card.getCardView();                           break;
                                }
                                addView(view, R.id.cards, 5-list.length);
                                card_list.remove(which);
                                if(list.length == 1) { add_fab.hide(); }
                        }
                        updateValues(calc.getHeroAttr());
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showHeroDialog() {
        showNoticeDialog("hero", "Choose hero", hero_list);
    }

    public void showTalentDialog() {
        showNoticeDialog("talent", "Choose talent", talent_list);
    }

    public void showCrestDialog() {
        showNoticeDialog("crest", "Choose crest", crest_list);
    }

    public void showArtifactDialog() {
        showNoticeDialog("artifact", "Choose artifact", artifact_list);
    }

    public void showHeroLevelDialog() {
        showNoticeDialog("hero level", "Hero level", intToString(hero_level_list));
    }

    public void showSkillLevelDialog() {
        showNoticeDialog("skill level", "Skill level", intToString(skill_level_list));
    }

    public void showTalentLevelDialog() {
        showNoticeDialog("talent level", "Talent level", intToString(talent_level_list));
    }

    public void showCrestLevelDialog() {
        showNoticeDialog("crest level", "Crest level", intToString(crest_level_list));
    }

    public void showArtifactLevelDialog() {
        showNoticeDialog("artifact level", "Artifact level", intToString(artifact_level_list));
    }

    public void showCardDialog() {
        String[] cardArray = new String[ card_list.size() ];
        card_list.toArray( cardArray );
        showNoticeDialog("add card", "Add to calculation", cardArray);
    }

    public void updateCard(Card card, String title, int thumbnail) {
        card.setTitle(title);
        card.setThumbnail(thumbnail);
    }

    public void updateCardLevel(Card card, int level) {
        card.setAction1("Level  " + Integer.toString(level));
    }

    public void updateValues(int[] hero_attr) {
        ((TextView) findViewById(R.id.atk_value)).setText(Integer.toString(hero_attr[0]));
        ((TextView) findViewById(R.id.atk_spd_value)).setText(Integer.toString(hero_attr[1]));
        ((TextView) findViewById(R.id.dmg_value)).setText(Integer.toString(hero_attr[2]));
        ((TextView) findViewById(R.id.hp_value)).setText(Integer.toString(hero_attr[3]));
        ((TextView) findViewById(R.id.mov_spd_value)).setText(Integer.toString(hero_attr[4]));
    }

    public void enableLevel(Card card) {
        card.getCardView().findViewById(R.id.card_action1).setEnabled(true);
    }

    public void disableLevel(Card card) {
        card.getCardView().findViewById(R.id.card_action1).setEnabled(false);
    }

    public void addView(View view, int insertPointId, int insertPosition) {
        ViewGroup insertPoint = (ViewGroup) findViewById(insertPointId);
        insertPoint.addView(view, insertPosition, view.getLayoutParams());
    }

    public void removeView(int id) {
        View view = findViewById(id);
        ((ViewGroup) view.getParent()).removeView(view);
    }
}