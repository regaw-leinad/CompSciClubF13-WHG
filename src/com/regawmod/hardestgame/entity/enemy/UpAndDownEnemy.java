package com.regawmod.hardestgame.entity.enemy;

import com.regawmod.hardestgame.entity.Enemy;
import com.regawmod.hardestgame.level.Level;

/**
 * An enemy, bounded by the walls of the level, that moves up and down.
 * 
 * @author Dan Wager
 */
public class UpAndDownEnemy extends Enemy
{
    /**
     * Creates a new {@link UpAndDownEnemy}.
     * 
     * @param x The X position
     * @param y The Y position
     * @param speed The speed of the enemy
     * @param level The level
     */
    public UpAndDownEnemy(float x, float y, float speed, Level level)
    {
        super(x, y, level);

        this.setSpeed(speed);
    }

    @Override
    public void update(float dt)
    {
        this.moveY(getSpeed() * dt);

        if (this.collidesWithWall() || this.collidesWithZone())
        {
            while (this.collidesWithWall() || this.collidesWithZone())
                this.moveY(this.getBactrackComponent(dt));

            this.turnAround();
        }
    }
}
