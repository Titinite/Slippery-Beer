package fr.supdevinci.java_project.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import fr.supdevinci.java_project.utils.Constants;

public class Ennemy {
    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;

    private Rectangle bounds;


    public Ennemy(float x, float y, int imageIndex) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture("images/ennemy" + imageIndex + ".png");
        bounds = new Rectangle(x, y, texture.getWidth()-10, texture.getHeight()-5);
    }

    public void update(float deltaTime) {
        bounds.setPosition(position.x, position.y);

        position.x -= Constants.ENNEMY_SPEED * deltaTime;
        position.add(velocity.x, 0);

        if (position.x < Constants.DESPAWN_ENNEMY_ZONE) {
            System.out.println("L'ennemi a franchi la limite");
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }

    public float getX() {
        return position.x;
    }

    public Rectangle getBounds() {
        return bounds;
    }

}
