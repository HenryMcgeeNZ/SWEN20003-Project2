import bagel.Image;

import java.util.ArrayList;

/**
 * FruitHolder subclass. Contains Stockpile logic for interactions with other actors
 */
public class Stockpile extends FruitHolder {

    /**
     * Creates a Stockpile at Point (x,y) with 0 starting fruit
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Stockpile(int x, int y) {
        super(x, y, "res/images/cherries.png", STATIONARY_POS, 0);
    }

    /**
     * Calls specified method depending if movable is Gatherer or Thief
     * @param movable Location match with this MovableActor
     */
    @Override
    public void action(MovableActor movable) {
        if(movable instanceof Gatherer) {
            gathererAction((Gatherer) movable);
        }
        if(movable instanceof Thief) {
            thiefAction((Thief) movable);
        }
    }

    /**
     * Logic for Thief moving onto a Stockpile
     * @param thief Location match with this Thief
     */
    public void thiefAction(Thief thief) {
        if(!thief.isCarrying()) {
            if (getNumFruit() > 0) {
                thief.setCarrying(true);
                thief.setConsuming(false);
                setNumFruit(getNumFruit() - 1);
                thief.changeDirection(CW, 90);
            }
        } else {
            thief.changeDirection(CW, 90);
        }
    }
}
