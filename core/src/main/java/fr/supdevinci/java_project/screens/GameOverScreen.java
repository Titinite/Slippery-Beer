package fr.supdevinci.java_project.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import fr.supdevinci.java_project.Main;
import fr.supdevinci.java_project.game.GameScreen;
import fr.supdevinci.java_project.game.GameScreenManager;

public class GameOverScreen implements Screen {

    private static Game game;
    SpriteBatch batch;
    BitmapFont font;

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.setColor(Color.RED);
        font.getData().setScale(3);
        GlyphLayout title = new GlyphLayout();
        title.setText(font, "Game Over");
        font.draw(batch, title, (Gdx.graphics.getWidth() - title.width) / 2, Gdx.graphics.getHeight() / 2 + title.height);
        batch.end();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
