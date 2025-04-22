package fr.supdevinci.java_project.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import fr.supdevinci.java_project.Main;
import fr.supdevinci.java_project.entities.Player;
import fr.supdevinci.java_project.entities.Ennemy;
import fr.supdevinci.java_project.utils.Constants;
import fr.supdevinci.java_project.screens.GameOverScreen;

public class GameScreen implements Screen {
    private final Main game;
    private Player beer;

    private ParallaxBackground parallax;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;

    private final ArrayList<Ennemy> ennemies = new ArrayList<>();
    private float spawnTimer = 0;
    private float nextSpawnIn = 0;
    private final Random random = new Random();

    public int score = 0;

    public GameScreen(Main game) {
        this.game = game;
    }

    private float getRandomSpawnInterval() {
        return Constants.SPAWN_INTERVAL_MIN + random.nextFloat() * (Constants.SPAWN_INTERVAL_MAX - Constants.SPAWN_INTERVAL_MIN);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, camera);
        viewport.apply();
        camera.position.set(Constants.VIRTUAL_WIDTH / 2f, Constants.VIRTUAL_HEIGHT / 2f, 0);
        camera.update();
        font = new BitmapFont();

        nextSpawnIn = getRandomSpawnInterval();

        beer = new Player(Constants.PLAYER_SPAWN_X, Constants.PLAYER_SPAWN_Y);
        Texture backgroundTex = new Texture("images/background.png");

        Texture barTex = new Texture("images/bar.png");
        float backgroundY = 0;
        float barY = Constants.VIRTUAL_HEIGHT - barTex.getHeight() - 250;

        parallax = new ParallaxBackground(Arrays.asList(
            new ParallaxLayer(backgroundTex, 75, backgroundY),
            new ParallaxLayer(barTex, Constants.ENNEMY_SPEED, barY)
        ));
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            beer.jump();
        }

        beer.update(delta);
        parallax.update(delta);
        spawnTimer += delta;

        if (spawnTimer >= nextSpawnIn) {
            spawnTimer = 0;
            nextSpawnIn = getRandomSpawnInterval();

            int index = 1 + random.nextInt(5);
            float spawnX = Constants.VIRTUAL_WIDTH + 50;
            Ennemy ennemy = new Ennemy(spawnX, Constants.GROUND_Y, index);
            ennemies.add(ennemy);

            score++;
        }
        Iterator<Ennemy> iterator = ennemies.iterator();

        while (iterator.hasNext()) {
            Ennemy ennemy = iterator.next();
            ennemy.update(delta);
            if (ennemy.getX() < Constants.DESPAWN_ENNEMY_ZONE) {
                ennemy.dispose();
                iterator.remove();
            }
        }

        for (Ennemy ennemy : ennemies) {
            if (ennemy.getBounds().overlaps(beer.getBounds())) {
                GameScreenManager.setScreen(new GameOverScreen());
            }
        }
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        parallax.render(game.batch, Constants.VIRTUAL_HEIGHT);
        beer.render(game.batch);
        for (Ennemy ennemy : ennemies) {
            ennemy.render(game.batch);
        }
        font.setColor(Color.WHITE);
        font.getData().setScale(4);
        font.draw(game.batch, "Score: " + score, Constants.SCORE_MARGIN, Constants.VIRTUAL_HEIGHT - Constants.SCORE_MARGIN);
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
        for (Ennemy ennemy : ennemies) {
            ennemy.dispose();
        }
        ennemies.clear();
    }
}
