import fr.supdevinci.java_project.entities.Ennemy;
import fr.supdevinci.java_project.utils.Constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEnnemy {

    private Ennemy ennemy;

    @BeforeEach
    public void setUp() {
        ennemy = new Ennemy(100, Constants.GROUND_Y, 1);
    }

    // Tests classiques
    @Test
    public void testInitialPosition() {
        assertEquals(100, ennemy.getBounds().x, "Position X de l'ennemi initiale incorrecte");
        assertEquals(Constants.GROUND_Y, ennemy.getBounds().y, "Position Y de l'ennemi initiale incorrecte");
    }

    @Test
    public void testUpdatePosition() {
        float deltaTime = 0.1f;
        ennemy.update(deltaTime);
        assertTrue(ennemy.getBounds().x < 100, "L'ennemi n'avance pas");
    }
}
