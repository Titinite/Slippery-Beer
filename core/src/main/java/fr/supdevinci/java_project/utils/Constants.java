package fr.supdevinci.java_project.utils;

public class Constants {

    // Camera de jeu
    public static final int VIRTUAL_WIDTH = 1280;
    public static final int VIRTUAL_HEIGHT = 720;

    // Items
    public static final int GROUND_Y = 140;

    // Player
    public static final int PLAYER_SPAWN_X = 100;
    public static final int PLAYER_SPAWN_Y = 200;
    public static final int GRAVITY = -1000;
    public static final int JUMP_VELOCITY = 700;
    public static final float JUMP_TOLERANCE = 0.5f;

    // Ennemy
    public static final float SPAWN_INTERVAL_MIN = 2;
    public static final float SPAWN_INTERVAL_MAX = 5;
    public static final int ENNEMY_SPEED = 250;
    public static final int DESPAWN_ENNEMY_ZONE = -150;
    public static final int HITBOX_MARGIN_WIDTH = 15;
    public static final int HITBOX_MARGIN_HEIGHT = 10;

    // Score
    public static final int SCORE_MARGIN = 25;

    // Game Over
    public static final String GAME_OVER_TEXT = "Game Over";
}
