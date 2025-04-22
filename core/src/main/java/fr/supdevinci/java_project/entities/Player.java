package fr.supdevinci.java_project.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.supdevinci.java_project.utils.Constants;

/**
 * Représente le joueur sous forme de bière dans le jeu.
 * Gère la physique de saut, la position, le rendu et la collision avec le comptoir.
 */
public class Player {

    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;
    private Rectangle bounds;

    /**
     * Initialise la bière à une position donnée.
     *
     * @param x position X initiale
     * @param y position Y initiale
     */
    public Player(float x, float y) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.texture = new Texture("images/beer.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    /**
     * Met à jour la position et la collision de la bière.
     *
     * @param deltaTime temps écoulé depuis la dernière frame
     * */
    public void update(float deltaTime) {
        velocity.y += Constants.GRAVITY * deltaTime;
        position.y += velocity.y * deltaTime;

        if (position.y < Constants.GROUND_Y) {
            position.y = Constants.GROUND_Y;
            velocity.y = 0;
        }
        bounds.setPosition(position.x, position.y);
    }

    public void jump() {
        if (position.y <= Constants.GROUND_Y + Constants.JUMP_TOLERANCE) {
            velocity.y = Constants.JUMP_VELOCITY;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }

    /**
     * Retourne la hitbox de la bière pour les collisions.
     *
     * @return rectangle de collision
     */
    public Rectangle getBounds() {
        return bounds;
    }
}
