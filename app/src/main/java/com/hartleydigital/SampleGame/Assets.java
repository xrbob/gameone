package com.hartleydigital.SampleGame;

import com.hartleydigital.framework.Image;
import com.hartleydigital.framework.Music;
import com.hartleydigital.framework.Sound;

public class Assets {

    public static Image menu;
    public static Image splash;
    public static Image background;
    public static Image character;
    public static Image character2;
    public static Image character3;
    public static Image heliboy;
    public static Image heliboy2;
    public static Image heliboy3;
    public static Image heliboy4;
    public static Image heliboy5;
    public static Image tiledirt;
    public static Image tilegrassTop;
    public static Image tilegrassBot;
    public static Image tilegrassLeft;
    public static Image tilegrassRight;
    public static Image characterJump;
    public static Image characterDown;
    public static Image button;

    public static Sound click;
    public static Music theme;

    public static void load(SampleGame sampleGame) {

        theme = sampleGame.getAudio().createMusic("assets/menutheme.mp3");

        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();

    }

}
