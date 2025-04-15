package fr.supdevinci.java_project.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Beer {
    private static final float GROUND_Y = 140;
    private static final float JUMP_VELOCITY = 450f;
    private static final float GRAVITY = -1000f;

    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;

    public Beer(float x, float y) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.texture = new Texture("images/beer.png");
    }

    public void update(float deltaTime) {
        // Application de la gravité
        velocity.y += GRAVITY * deltaTime;

        // Mise à jour de la position
        position.y += velocity.y * deltaTime;

        // Gestion du sol
        if (position.y < GROUND_Y) {
            position.y = GROUND_Y;
            velocity.y = 0;
        }
    }

    public void jump() {
        if (position.y <= GROUND_Y + 0.5f) {
            velocity.y = JUMP_VELOCITY;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }
}
