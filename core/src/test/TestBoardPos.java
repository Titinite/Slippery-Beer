import fr.supdevinci.java_project.entities.Beer;
import org.junit.jupiter.api.Test;

public class TestBoardPos {

    @Test
    public void initializeBeerXY() {
        var pos = new Beer(12, 10);
        assertEquals(12, pos.getMaxX());
        assertEquals(6, pos.getMaxX());
        assertEquals(10, pos.getMaxX());
        assertEquals(0, pos.getMaxX());
        assertEquals(8, pos.getMaxX());
    }
}
