package com.client.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
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
    private List<Object> hiddenPluginBoardButtons;
    private List<SwitcherView> switcherViews;
    private String background;
    private String backgroundColor;
    private String backgroundImage;
    private List<ImageDescriptor> backgroundImageScaled;
    private boolean backgroundTile;
    private String backgroundBrightness;
    private String backgroundBottomColor;
    private String backgroundTopColor;
    private boolean canBePublic;
    private boolean canBeEnterprise;
    private boolean canBeOrg;
    private boolean canBePrivate;

    private boolean canInvite;
}
