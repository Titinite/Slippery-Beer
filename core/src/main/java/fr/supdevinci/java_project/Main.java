package fr.supdevinci.java_project;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.supdevinci.java_project.game.GameScreen;
import fr.supdevinci.java_project.game.GameScreenManager;

public class Main extends Game {

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        GameScreenManager.init(this);
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        getScreen().dispose();
    }
}
