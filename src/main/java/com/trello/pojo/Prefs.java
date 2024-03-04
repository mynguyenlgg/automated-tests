package com.trello.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Prefs {

    private String permissionLevel;
    private boolean hideVotes;
    private String voting;
    private String comments;
    private String invitations;
    private boolean selfJoin;
    private boolean cardCovers;
    private boolean cardCounts;
    private boolean isTemplate;
    private String cardAging;
    private boolean calendarFeedEnabled;
    private ArrayList<Object> hiddenPluginBoardButtons;
    private ArrayList<SwitcherView> switcherViews;
    private String background;
    private String backgroundColor;
    private Object backgroundImage;
    private Object backgroundImageScaled;
    private boolean backgroundTile;
    private String backgroundBrightness;
    private String backgroundBottomColor;
    private String backgroundTopColor;
    private boolean canBePublic;
    private boolean canBeEnterprise;
    private boolean canBeOrg;
    private boolean canBePrivate;
    private boolean canInvite;
    @JsonProperty("isTemplate")
    public boolean isTemplate() {
        return isTemplate;
    }
    @JsonProperty("isTemplate")
    public void setTemplate(boolean template) {
        isTemplate = template;
    }


}
