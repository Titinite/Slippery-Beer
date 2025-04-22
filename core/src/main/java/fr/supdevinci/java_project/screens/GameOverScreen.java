package fr.supdevinci.java_project.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import fr.supdevinci.java_project.utils.Constants;

/**
 * Écran de fin de jeu affichant le message "Game Over".
 */
public class GameOverScreen implements Screen {
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
        title.setText(font, Constants.GAME_OVER_TEXT);
        drawCenteredText(title);

        batch.end();
    }

    /**
     * Méthode utilitaire pour afficher le texte centré à l'écran.
     *
     * @param glyphLayout Le GlyphLayout contenant le texte à afficher
     */
    private void drawCenteredText(GlyphLayout glyphLayout) {
        float x = (Gdx.graphics.getWidth() - glyphLayout.width) / 2;
        float y = (Gdx.graphics.getHeight() + glyphLayout.height) / 2;
        font.draw(batch, glyphLayout, x, y);
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
