import fr.supdevinci.java_project.entities.Player;
import fr.supdevinci.java_project.utils.Constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.math.Rectangle;

import static org.mockito.Mockito.*;
import com.badlogic.gdx.graphics.Texture;

public class TestPlayer {

    private Player player;

    private Texture createMockTexture() {
        Texture mockTexture = mock(Texture.class);
        when(mockTexture.getWidth()).thenReturn(50);
        when(mockTexture.getHeight()).thenReturn(100);
        return mockTexture;
    }

    @BeforeEach
    public void setUp() {
        player = new Player(100, Constants.GROUND_Y, createMockTexture());
    }

    // Tests classiques
    @Test
    public void testInitialPosition() {
        assertEquals(100, player.getBounds().x, "Position X initiale incorrecte");
        assertEquals(Constants.GROUND_Y, player.getBounds().y, "Position Y initiale incorrecte");
    }

    @Test
    public void testUpdatePositionWithGravity() {
        float deltaTime = 0.1f;
        player.update(deltaTime);
        assertTrue(player.getBounds().y < 200, "Le joueur ne tombe pas sous l'effet de la gravité");
    }

    @Test
    public void testJump() {
        player.jump();
        float initialVelocityY = player.getBounds().y;
        player.update(0.1f);
        assertTrue(player.getBounds().y > initialVelocityY, "Le joueur ne saute pas");
    }

    @Test
    public void testCollisionWithGround() {
        player.update(1.0f);
        assertEquals(Constants.GROUND_Y, player.getBounds().y, "Le joueur passe à travers le sol");
    }

    @Test
    public void testHitbox() {
        Rectangle bounds = player.getBounds();
        assertNotNull(bounds, "La hitbox ne doit pas être nulle");
        assertEquals(100, bounds.x, "Position X de la hitbox incorrecte");
        assertEquals(Constants.GROUND_Y, bounds.y, "Position Y de la hitbox incorrecte");
    }

    // Tests extrêmes
    @Test
    public void testHighPosition() {
        player = new Player(100, 1000, createMockTexture());
        assertTrue(player.getBounds().y > Constants.GROUND_Y, "Le joueur est trop haut, le jeu doit supporter des positions élevées");
    }

    @Test
    public void testPositionAtGround() {
        player = new Player(100, Constants.GROUND_Y, createMockTexture());
        assertEquals(Constants.GROUND_Y, player.getBounds().y, "Le joueur ne doit pas être au-dessus du sol");
    }

    // Tests d'erreurs
    @Test
    public void testNegativePosition() {
        player = new Player(100, -Constants.GROUND_Y, createMockTexture());
        assertTrue(player.getBounds().y >= 0, "Le joueur ne doit pas se retrouver sous le sol");
    }

    @Test
    public void testExcessiveJump() {
        player = new Player(100, Constants.GROUND_Y, createMockTexture());
        player.jump();
        player.update(0.5f);
        assertTrue(player.getBounds().y > Constants.GROUND_Y, "Le joueur ne doit pas rester collé au sol après un saut");
    }
}
