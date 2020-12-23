import bagel.Image;

import java.util.ArrayList;

/**
 * FruitHolder subclass. Contains Hoard logic for interactions
    with other actors
 */
public class Hoard extends FruitHolder {

    /**
     * Creates a Hoard at Point (x,y) with 0 starting fruit
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Hoard(int x, int y) {
        super(x, y, "res/images/hoard.png", STATIONARY_POS, 0);
    }

    /**
     * Calls specific method depending if movable is a Thief or Gatherer
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
     * Logic for Thief moving onto a Hoard
     * @param thief Location match with this Thief
     */
    public void thiefAction(Thief thief) {
        if(thief.isConsuming()) {
            thief.setConsuming(false);
            if(!thief.isCarrying()) {
                if(getNumFruit() > 0) {
                    thief.setCarrying(true);
                    setNumFruit(getNumFruit() - 1);
                } else {
                    thief.changeDirection(CW, 90);
                }
            }
        } else if(thief.isCarrying()) {
            thief.setCarrying(false);
            setNumFruit(getNumFruit() + 1);
            thief.changeDirection(CW, 90);
        }
    }
}
