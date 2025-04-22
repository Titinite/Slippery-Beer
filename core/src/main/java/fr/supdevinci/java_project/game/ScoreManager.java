package fr.supdevinci.java_project.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

import fr.supdevinci.java_project.utils.Constants;

public class ScoreManager {
    private int score;
    private final BitmapFont font;

    public ScoreManager() {
        score = 0;
        font = new BitmapFont();
    }

    public void incrementScore() {
        score++;
    }

    public void render(SpriteBatch batch) {
        font.setColor(Color.WHITE);
        font.getData().setScale(4);
        font.draw(batch, "Score: " + score, Constants.SCORE_MARGIN, Constants.VIRTUAL_HEIGHT - Constants.SCORE_MARGIN);
    }

    public void dispose() {
        font.dispose();
    }
}
