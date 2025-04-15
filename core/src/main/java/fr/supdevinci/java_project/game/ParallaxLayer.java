package fr.supdevinci.java_project.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParallaxLayer {
    private final Texture texture;
    private final float speed;
    private final float y;
    private float x;

    public ParallaxLayer(Texture texture, float speed, float y) {
        this.texture = texture;
        this.speed = speed;
        this.y = y;
    }

    public void update(float deltaTime) {
        x -= speed * deltaTime;
        if (x <= -texture.getWidth()) {
            x += texture.getWidth();
        }
    }

    public void render(SpriteBatch batch, float screenHeight) {
        int textureWidth = texture.getWidth();
        int screenWidth = (int) screenHeight * 16 / 9;
        int repeatCount = screenWidth / textureWidth + 3;

        float currentX = x;
        for (int i = 0; i < repeatCount; i++) {
            batch.draw(texture, currentX, y);
            currentX += textureWidth;
        }
    }

    public void dispose() {
        texture.dispose();
    }
}
