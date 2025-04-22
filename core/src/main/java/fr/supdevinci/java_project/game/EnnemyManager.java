package fr.supdevinci.java_project.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import fr.supdevinci.java_project.entities.Ennemy;
import fr.supdevinci.java_project.utils.Constants;

import java.util.Iterator;
import java.util.Random;

public class EnnemyManager {
    private final Array<Ennemy> ennemies = new Array<>();
    private final Random random = new Random();
    private float spawnTimer = 0;
    private float nextSpawnIn = 0;

    public EnnemyManager() {
        nextSpawnIn = getRandomSpawnInterval();
    }

    /**
     * Calcule un intervalle de temps aléatoire pour l'apparition des ennemis.
     * @return Intervalle de temps avant l'apparition d'un ennemi.
     */
    private float getRandomSpawnInterval() {
        return Constants.SPAWN_INTERVAL_MIN + random.nextFloat() * (Constants.SPAWN_INTERVAL_MAX - Constants.SPAWN_INTERVAL_MIN);
    }

    /**
     * Met à jour le gestionnaire d'ennemis en fonction du temps écoulé.
     * Cette méthode gère le temps entre chaque apparition d'ennemi et la suppression des ennemis hors écran.
     * Elle incrémente également le score à chaque nouvel ennemi créé.
     *
     * @param deltaTime Temps écoulé depuis la dernière mise à jour, utilisé pour calculer le spawn des ennemis.
     * @param scoreManager Permettant d'incrémenter le score lorsque un nouvel ennemi apparait.
     */
    public void update(float deltaTime, ScoreManager scoreManager) {
        spawnTimer += deltaTime;

        if (spawnTimer >= nextSpawnIn) {
            spawnTimer = 0;
            nextSpawnIn = getRandomSpawnInterval();

            int index = 1 + random.nextInt(5);
            float spawnX = Constants.VIRTUAL_WIDTH + 50;
            Ennemy ennemy = new Ennemy(spawnX, Constants.GROUND_Y, index);
            ennemies.add(ennemy);
            scoreManager.incrementScore();
        }

        Iterator<Ennemy> iterator = ennemies.iterator();
        while (iterator.hasNext()) {
            Ennemy ennemy = iterator.next();
            ennemy.update(deltaTime);
            if (ennemy.getX() < Constants.DESPAWN_ENNEMY_ZONE) {
                ennemy.dispose();
                iterator.remove();
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Ennemy ennemy : ennemies) {
            ennemy.render(batch);
        }
    }

    /**
     * Retourne la liste des ennemis actuellement en jeu
     *
     * @return Liste des ennemis présents à l'écran
     */
    public Array<Ennemy> getEnnemies() {
        return ennemies;
    }

    public void dispose() {
        for (Ennemy ennemy : ennemies) {
            ennemy.dispose();
        }
        ennemies.clear();
    }
}
