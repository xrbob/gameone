package com.hartleydigital.SampleGame;

import com.hartleydigital.framework.Game;
import com.hartleydigital.framework.Graphics;
import com.hartleydigital.framework.Graphics.ImageFormat;
import com.hartleydigital.framework.Screen;

public class SplashLoadingScreen extends Screen {

    public SplashLoadingScreen(Game game) {

        super(game);

    }

    @Override
    public void update(float deltaTime) {

        Graphics g = game.getGraphics();

        Assets.splash = g.newImage("splash.jpg", ImageFormat.RGB565);

        game.setScreen(new LoadingScreen(game));

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
