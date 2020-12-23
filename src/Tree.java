import bagel.*;

import java.util.ArrayList;

/**
 * FruitHolder subclass. contains tree logic for interactions with other actors
 */
public class Tree extends FruitHolder{

    /**
     * Creates a Tree at Point (x,y) with 0 starting fruit
     * @param x
     * @param y
     */
    public Tree(int x, int y) {
        super(x, y, "res/images/tree.png", STATIONARY_POS, 3);
    }

    /**
     * Bestows one of its fruits to movable
     * @param movable Location match with this MovableActor
     */
    @Override
    public void action(MovableActor movable) {
        if(!movable.isCarrying()) {
            if(getNumFruit() > 0) {
                setNumFruit(getNumFruit() - 1);
                movable.setCarrying(true);
                if(movable instanceof Gatherer) {
                    movable.changeDirection(CW, 180);
                }
            }
        }
    }
}
