package fr.supdevinci.java_project.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class GameScreenManager {
    private static Game game;

    public static void init(Game g) {
        game = g;
    }

    public static void setScreen(Screen screen) {
        game.setScreen(screen);
    }
}
