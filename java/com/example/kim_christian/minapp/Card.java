package com.example.kim_christian.minapp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Kim-Christian on 2017-02-16.
 */

public class Card extends AppCompatActivity {
    private int id;
    private String subtitle;
    private String title;
    private int thumbnail;
    private String action1;
    private String action2;
    private CardView cardView;
    private int position;

    public Card(String calcCard, LayoutInflater vi) {
        View v = vi.inflate(R.layout.view_card, null);
        this.cardView = (CardView) v.findViewById(R.id.cardView);
        createCardView(this.id, 20);
        switch(calcCard) {
            case "hero": setAttr(R.id.hero_card, "Hero", "Choose hero", "Level", "Remove", 0); break;
            case "skill": setAttr(R.id.skill_card, "Skill", "Skill", "Level", "Remove", 1); break;
            case "talent": setAttr(R.id.talent_card, "Talent", "Choose talent", "Level", "Remove", 2); break;
            case "crest": setAttr(R.id.crest_card, "Crest", "Choose crest", "Level", "Remove", 3); break;
            case "artifact": setAttr(R.id.artifact_card, "Artifact", "Choose artifact", "Level", "Remove", 4); break;
            default: setAttr(R.id.card, "Subtitle", "Title", "Action1", "Action2", 0); break;
        }
    }

    public Card() {
        this("", null);
    }

    public void setAttr(int id, String subtitle, String title, String action1, String action2, int position) {
        setId(id);
        setSubtitle(subtitle);
        setTitle(title);
        setAction1(action1);
        setAction2(action2);
        setPosition(position);
    }

    public int getId() {
        return this.id;
    }
    public String getSubtitle() {
        return this.subtitle;
    }
    public String get_title() {
        return this.title;
    }           // getTitle cannot override
    public int getThumbnail() {
        return this.thumbnail;
    }
    public String getAction1() {
        return this.action1;
    }
    public String getAction2() {
        return this.action2;
    }
    public CardView getCardView() { return this.cardView; }
    public int getPosition() { return this.position; }

    public void setId(int new_id) {
        this.id = new_id;
        this.cardView.setId(this.id);
    }
    public void setSubtitle(String new_subtitle) {
        this.subtitle = new_subtitle;
        ((TextView) this.cardView.findViewById(R.id.card_subtitle)).setText(this.subtitle);
    }
    public void setTitle(String new_title) {
        this.title = new_title;
        ((TextView) this.cardView.findViewById(R.id.card_title)).setText(this.title);
    }
    public void setThumbnail(int new_thumbnail) {
        this.thumbnail = new_thumbnail;
        this.cardView.findViewById(R.id.card_thumbnail).setBackgroundResource(this.thumbnail);
    }
    public void setAction1(String new_action) {
        this.action1 = new_action;
        ((TextView) this.cardView.findViewById(R.id.card_action1)).setText(this.action1);
    }
    public void setAction2(String new_action) {
        this.action2 = new_action;
        ((TextView) this.cardView.findViewById(R.id.card_action2)).setText(this.action2);
    }
    public void setPosition(int new_position) {
        this.position = new_position;
    }

    private void createCardView(int id, int margin){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        lp.setMargins(margin, margin, margin, margin);
        this.cardView.setLayoutParams(lp);
    }
}
