package com.hartleydigital.SampleGame;

import com.hartleydigital.framework.Game;
import com.hartleydigital.framework.Graphics;
import com.hartleydigital.framework.Graphics.ImageFormat;
import com.hartleydigital.framework.Screen;

public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {

        super(game);

    }

    @Override
    public void update(float deltaTime) {

        Graphics g   = game.getGraphics();
        Assets.menu  = g.newImage("menu.jpg", ImageFormat.RGB565);
        Assets.click = game.getAudio().createSound("explode.ogg");

        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}
