package fr.supdevinci.java_project.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Arrays;

import fr.supdevinci.java_project.Main;
import fr.supdevinci.java_project.entities.Player;
import fr.supdevinci.java_project.entities.Ennemy;
import fr.supdevinci.java_project.utils.Constants;
import fr.supdevinci.java_project.screens.GameOverScreen;

/**
 * Écran principal du jeu où le joueur contrôle une bière et doit éviter les ennemis
 */
public class GameScreen implements Screen {
    private final Main game;
    private Player beer;

    private ParallaxBackground parallax;
    private OrthographicCamera camera;
    private Viewport viewport;

    private EnnemyManager ennemyManager;
    private ScoreManager scoreManager;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        initializeCamera();
        initializeBeer();
        initializeParallaxBackground();
        initializeEnnemyManager();
        initializeScoreManager();
    }

    /**
     * Initialise la caméra et le viewport
     */
    private void initializeCamera() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, camera);
        viewport.apply();
        camera.position.set(Constants.VIRTUAL_WIDTH / 2f, Constants.VIRTUAL_HEIGHT / 2f, 0);
        camera.update();
    }

    /**
     * Initialise le joueur
     */
    private void initializeBeer() {
        beer = new Player(Constants.PLAYER_SPAWN_X, Constants.PLAYER_SPAWN_Y, Constants.PLAYER_TEXTURE);
    }

    /**
     * Initialise le fond parallax et les éléments de l'arrière-plan
     */
    private void initializeParallaxBackground() {
        Texture backgroundTex = new Texture("images/background.png");
        Texture barTex = new Texture("images/bar.png");
        float barY = Constants.VIRTUAL_HEIGHT - barTex.getHeight() - 250;

        parallax = new ParallaxBackground(Arrays.asList(
            new ParallaxLayer(backgroundTex, 75, 0),
            new ParallaxLayer(barTex, Constants.ENNEMY_SPEED, barY)
        ));
    }

    /**
     * Initialise le gestionnaire des ennemis
     */
    private void initializeEnnemyManager() {
        ennemyManager = new EnnemyManager();
    }

    /**
     * Initialise le gestionnaire de score
     */
    private void initializeScoreManager() {
        scoreManager = new ScoreManager();
    }

    @Override
    public void render(float delta) {
        handlePlayerInput();
        updateGameObjects(delta);
        checkCollisions();
        renderGameObjects();
    }

    /**
     * Gère les entrées utilisateur
     */
    private void handlePlayerInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            beer.jump();
        }
    }

    /**
     * Met à jour l'état du joueur, des ennemis et du fond parallax
     */
    private void updateGameObjects(float delta) {
        beer.update(delta);
        parallax.update(delta);
        ennemyManager.update(delta, scoreManager);
    }

    /**
     * Vérifie les collisions entre le joueur et les ennemis
     */
    private void checkCollisions() {
        for (Ennemy ennemy : ennemyManager.getEnnemies()) {
            if (ennemy.getBounds().overlaps(beer.getBounds())) {
                GameScreenManager.setScreen(new GameOverScreen());
            }
        }
    }

    /**
     * Rendu des objets de jeu (fond, bière, ennemis, score)
     */
    private void renderGameObjects() {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        parallax.render(game.batch, Constants.VIRTUAL_HEIGHT);
        beer.render(game.batch);
        ennemyManager.render(game.batch);
        scoreManager.render(game.batch);
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
        ennemyManager.dispose();
        scoreManager.dispose();
    }
}
