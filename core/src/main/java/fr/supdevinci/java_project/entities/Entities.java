package fr.supdevinci.java_project.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entities {
    public void update(float deltaTime);
    public void render(SpriteBatch batch);
    public void dispose();
}
