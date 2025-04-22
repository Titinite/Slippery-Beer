package fr.supdevinci.java_project.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.supdevinci.java_project.utils.Constants;

/**
 * Représente un ennemi que le joueur doit éviter.
 * Se déplace horizontalement vers la gauche.
 */
public class Ennemy implements Entities {
    private Vector2 position;
    private Vector2 velocity;
    private Texture texture;
    private Rectangle bounds;

    /**
     * Crée un ennemi à une position donnée avec un visuel spécifique.
     *
     * @param x           Position X initiale
     * @param y           Position Y initiale
     * @param imageIndex  Index de l'image à utiliser pour le visuel de l'ennemi
     */
    public Ennemy(float x, float y, int imageIndex) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture("images/ennemy" + imageIndex + ".png");
        bounds = new Rectangle(x, y,
            texture.getWidth() - Constants.HITBOX_MARGIN_WIDTH,
            texture.getHeight() - Constants.HITBOX_MARGIN_HEIGHT
        );
    }

    /**
     * Met à jour la position de l’ennemi.
     *
     * @param deltaTime Temps écoulé depuis la dernière frame
     */
    public void update(float deltaTime) {
        position.x -= Constants.ENNEMY_SPEED * deltaTime;
        position.add(velocity.x, 0);
        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }

    /**
     * Retourne la position X actuelle de l’ennemi.
     *
     * @return position X
     */
    public float getX() {
        return position.x;
    }

    /**
     * Retourne la zone de collision de l’ennemi.
     *
     * @return rectangle de collision
     */
    public Rectangle getBounds() {
        return bounds;
    }
}
