package com.client.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelNames {

    private String green;
    private String yellow;
    private String orange;
    private String red;
    private String purple;
    private String blue;
    private String sky;
    private String lime;
    private String pink;
    private String black;

    @SerializedName("green_dark")
    private String greenDark;
    @SerializedName("yellow_dark")
    private String yellowDark;
    @SerializedName("orange_dark")
    private String orangeDark;
    @SerializedName("red_dark")
    private String redDark;
    @SerializedName("purple_dark")
    private String purpleDark;
    @SerializedName("blue_dark")
    private String blueDark;
    @SerializedName("sky_dark")
    private String skyDark;
    @SerializedName("lime_dark")
    private String limeDark;
    @SerializedName("pink_dark")
    private String pinkDark;
    @SerializedName("black_dark")
    private String blackDark;
    @SerializedName("green_light")
    private String greenLight;
    @SerializedName("yellow_light")
    private String yellowLight;
    @SerializedName("orange_light")
    private String orangeLight;
    @SerializedName("red_light")
    private String redLight;
    @SerializedName("purple_light")
    private String purpleLight;
    @SerializedName("blue_light")
    private String blueLight;
    @SerializedName("sky_light")
    private String skyLight;
    @SerializedName("lime_light")
    private String limeLight;
    @SerializedName("pink_light")
    private String pinkLight;
    @SerializedName("black_light")
    private String blackLight;
}
