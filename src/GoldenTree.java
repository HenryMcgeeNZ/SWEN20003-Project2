import bagel.Image;
import java.util.ArrayList;

/**
 * Actor subclass. Contains golden tree logic for interactions with other actors
 */
public class GoldenTree extends Actor {

    /**
     * Creates GoldenTree at point (x,y)
     * @param x X coordinate
     * @param y Y coordinate
     */
    public GoldenTree(int x, int y) {
        super(x, y, "res/images/gold-tree.png", STATIONARY_POS);
    }

    /**
     * Bestows one fruit to movable
     * @param movable Location match with this MovableActor
     */
    @Override
    public void action(MovableActor movable) {
        if(!movable.isCarrying()) {
            movable.setCarrying(true);
            if(movable instanceof Gatherer) {
                movable.changeDirection(CW, 180);
            }
        }
    }
}
