package com.regawmod.hardestgame.state;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import com.regawmod.hardestgame.GameStats;
import com.regawmod.hardestgame.level.BrandonLevel;
import com.regawmod.hardestgame.level.Level;

public class InGameState extends AbstractGameState
{
    private Level level;
    private GameStats stats;

    private boolean createNextLevel;

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {
        this.stats = new GameStats();
        this.level = new BrandonLevel();
        this.level.setGameStats(this.stats);
        this.createNextLevel = false;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, float dt) throws SlickException
    {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
            gc.exit();

        level.update(gc, dt);

        if (this.level.isLevelComplete())
        {
            this.createNextLevel = true;
            game.enterState(GameState.LEVEL_COMPLETE, new FadeOutTransition(), new FadeInTransition());
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
    {
        level.render(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), 60);

        g.setColor(Color.white);
        g.drawString("Deaths: " + this.stats.getAmountOfDeaths(), 280, 10);
        g.drawString("Level: " + this.stats.getCurrentLevel(), 283, 30);
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame game) throws SlickException
    {
        if (this.createNextLevel)
        {
            this.stats.incrementLevel();
            this.level = new BrandonLevel();
            this.level.setGameStats(this.stats);
        }

        super.leave(gc, game);
    }

    public void incrementDeaths()
    {
        this.stats.incrementDeaths();
    }

    @Override
    public int getID()
    {
        return GameState.IN_GAME;
    }
}