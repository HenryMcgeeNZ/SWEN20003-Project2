import bagel.Image;

import java.util.ArrayList;

/**
 * Actor subclass. Contains fence logic for interactions with other actors
 */
public class Fence extends Actor {

    /**
     * Create Fence at point (x,y)
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Fence(int x, int y) {
        super(x, y, "res/images/fence.png", STATIONARY_POS);
    }

    /**
     * Set movable to inactive
     * @param movable Location match with this MovableActor
     */
    @Override
    public void action(MovableActor movable) {
        movable.setActive(false);
        movable.changeDirection(CW, 180);
        movable.move();
    }
}
