package com.regawmod.hardestgame.state;

/**
 * The different game states.
 * 
 * @author Dan Wager
 */
public final class GameState
{
    /**
     * The state while playing a level.
     */
    public static final int IN_GAME = 0;
    /**
     * State that is shown after completing a level.
     */
    public static final int LEVEL_COMPLETE = 1;
    /**
     * The main menu state
     */
    public static final int MAIN_MENU = 2;
    /**
     * The loading screen state
     */
    public static final int LOADING = 3;

    private GameState()
    {
    }
}
