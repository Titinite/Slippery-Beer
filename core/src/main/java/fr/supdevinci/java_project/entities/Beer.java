package fr.supdevinci.java_project.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Beer {
    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;

    private static final float GRAVITY = -20;
    private static final float JUMP_VELOCITY = 350;
    private static final float GROUND_Y = 100;

    public Beer(float x, float y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture("images/beer.png");
    }

    public void update(float deltaTime) {
        velocity.add(0, GRAVITY);
        velocity.scl(deltaTime);
        position.add(0, velocity.y);

        if (position.y < GROUND_Y) {
            position.y = GROUND_Y;
            velocity.y = 0;
        }
    }

    public void jump() {
        if (position.y == GROUND_Y) {
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
