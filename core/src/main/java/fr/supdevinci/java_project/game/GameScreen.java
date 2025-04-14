package fr.supdevinci.java_project.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import fr.supdevinci.java_project.Main;
import fr.supdevinci.java_project.entities.Beer;

public class GameScreen implements Screen {

    private final Main game;
    private Beer beer;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        beer = new Beer(100, 100);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            beer.jump();
        }

        beer.update(delta);

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1); // fond gris clair
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        beer.render(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        beer.dispose();
    }
}
