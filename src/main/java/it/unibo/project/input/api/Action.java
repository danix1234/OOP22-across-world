package it.unibo.project.input.api;

/**
 * enum for {@linkplain InputHandler} classes to know what action to execute.
 */
public enum Action {
    /** change the current scene to {@code SceneType.MENU}. */
    CHANGE_SCENE_TO_MENU,
    /** change the current scene to {@code SceneType.SHOP}. */
    CHANGE_SCENE_TO_SHOP,
    /** change the current scene to {@code SceneType.GAME}. */
    CHANGE_SCENE_TO_GAME,
    /** change the current scene to {@code SceneType.OVER}. */
    CHANGE_SCENE_TO_OVER,
    /** change the current scene to {@code SceneType.VICTORY}. */
    CHANGE_SCENE_TO_VICTORY,
    /** saves and exit application. */
    EXIT_APP,
    /** do not save and exit application. */
    NO_SAVE_EXIT_APP,
    /** indicate input handler to try moving player one position up. */
    MOVE_PLAYER_UP,
    /** indicate input handler to try moving player one position down. */
    MOVE_PLAYER_DOWN,
    /** indicate input handler to try moving player one position left. */
    MOVE_PLAYER_LEFT,
    /** indicate input handler to try moving player one position right. */
    MOVE_PLAYER_RIGHT,
}
