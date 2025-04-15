package fr.supdevinci.java_project.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.supdevinci.java_project.utils.Constants;

public class Beer {

    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;

    private Rectangle bounds;


    public Beer(float x, float y) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.texture = new Texture("images/beer.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update(float deltaTime) {

        bounds.setPosition(position.x, position.y);

        // Application de la gravité
        velocity.y += Constants.GRAVITY * deltaTime;

        // Mise à jour de la position
        position.y += velocity.y * deltaTime;

        // Gestion du sol
        if (position.y < Constants.GROUND_Y) {
            position.y = Constants.GROUND_Y;
            velocity.y = 0;
        }
    }

    public void jump() {
        if (position.y <= Constants.GROUND_Y + 0.5f) {
            velocity.y = Constants.JUMP_VELOCITY;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }
    
}
