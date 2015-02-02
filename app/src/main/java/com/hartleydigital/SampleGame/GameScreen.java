package com.hartleydigital.SampleGame;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.method.Touch;

import com.hartleydigital.framework.Game;
import com.hartleydigital.framework.Graphics;
import com.hartleydigital.framework.Input;
import com.hartleydigital.framework.Input.TouchEvent;
import com.hartleydigital.framework.Screen;

import java.util.List;

public class GameScreen extends Screen {

    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;

    // Variable Setup
    // Create game objects here

    int livesLeft = 1;
    Paint paint;

    public GameScreen(Game game) {

        super(game);

        // Initialise game objects here

        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

    }

    @Override
    public void update(float deltaTime) {

        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {

        if(touchEvents.size() > 0)
            state = GameState.Running;

    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

        /// HANDLE INPUT

        int len = touchEvents.size();

        for(int i = 0; i < len; i++) {

            TouchEvent event = touchEvents.get(i);

            if(event.type == TouchEvent.TOUCH_DOWN) {
                if(event.x < 640) {
                    // move left
                } else if(event.x > 640) {
                    // move right
                }
            }

            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x < 640) {
                    // stop moving left
                } else if(event.x > 640) {
                    // stop moving right
                }

            }

        }

        /// Check for game events (death, etc)

        if(livesLeft == 0) {
            state = GameState.GameOver;
        }

        /// Call other Updates (AI etc)

    }

    private void updatePaused(List<TouchEvent> touchEvents) {

        int len = touchEvents.size();

        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                // Unpause?
            }
        }

    }

    private void updateGameOver(List<TouchEvent> touchEvents) {

        int len = touchEvents.size();

        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 300 && event.x < 980 && event.y > 100 && event.y < 500) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    @Override
    public void paint(float deltaTime) {

        Graphics g = game.getGraphics();

        if(state == GameState.Ready)
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();
    }

    private void nullify() {

        // Set all variables to NULL
        paint = null;

        // Call garbage collector
        System.gc();

    }

    private void drawReadyUI() {

        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap each side of the screen to move in that direction", 640, 300, paint);

    }

    private void drawRunningUI() {

        Graphics g = game.getGraphics();

    }

    private void drawPausedUI() {

        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);

    }

    private void drawGameOverUI() {

        Graphics g = game.getGraphics();

        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER", 640, 300, paint);

    }

    @Override
    public void pause() {

        if(state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

        pause();

    }
}
