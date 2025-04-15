package fr.supdevinci.java_project.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.supdevinci.java_project.utils.Constants;

public class Ennemy {
    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;

    public Ennemy(float x, float y, int imageIndex) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture("images/ennemy" + imageIndex + ".png");
    }

    public void update(float deltaTime) {
        velocity.add(Constants.ENNEMY_SPEED, 0);
        velocity.scl(deltaTime);
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
}
