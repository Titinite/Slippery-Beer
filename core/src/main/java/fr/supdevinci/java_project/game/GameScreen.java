package fr.supdevinci.java_project.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.supdevinci.java_project.Main;
import fr.supdevinci.java_project.entities.Beer;
import java.util.Arrays;

public class GameScreen implements Screen {

    private final Main game;
    private Beer beer;
    private ParallaxBackground parallax;

    private OrthographicCamera camera;
    private Viewport viewport;

    private static final int VIRTUAL_WIDTH = 1280;
    private static final int VIRTUAL_HEIGHT = 720;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
        viewport.apply();
        camera.position.set(VIRTUAL_WIDTH / 2f, VIRTUAL_HEIGHT / 2f, 0);
        camera.update();

        beer = new Beer(100, 200);
        Texture backgroundTex = new Texture("images/background.png");
        Texture barTex = new Texture("images/bar.png");

        float barY = VIRTUAL_HEIGHT - barTex.getHeight() - 250;

        parallax = new ParallaxBackground(Arrays.asList(
            new ParallaxLayer(backgroundTex, 20f, 0),
            new ParallaxLayer(barTex, 60f, barY)
        ));
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            beer.jump();
        }

        beer.update(delta);
        parallax.update(delta);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        parallax.render(game.batch, VIRTUAL_HEIGHT);
        beer.render(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        beer.dispose();
        parallax.dispose();
    }
}
