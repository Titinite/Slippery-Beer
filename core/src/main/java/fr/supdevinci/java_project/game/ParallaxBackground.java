package fr.supdevinci.java_project.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

public class ParallaxBackground {
    private final List<ParallaxLayer> layers;

    public ParallaxBackground(List<ParallaxLayer> layers) {
        this.layers = layers;
    }

    public void update(float deltaTime) {
        for (ParallaxLayer layer : layers) {
            layer.update(deltaTime);
        }
    }

    public void render(SpriteBatch batch, float screenHeight) {
        for (ParallaxLayer layer : layers) {
            layer.render(batch, screenHeight);
        }
    }

    public void dispose() {
        for (ParallaxLayer layer : layers) {
            layer.dispose();
        }
    }
}