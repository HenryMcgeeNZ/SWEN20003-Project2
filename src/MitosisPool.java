import bagel.Image;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Subclass of Actor. Contains mitosis pool logic for interactions with other actors
 */
public class MitosisPool extends Actor {

    private final int ROTATION = 90;

    /**
     * Creates a MitosisPool at Point (x,y)
     * @param x X coordinate
     * @param y Y coordinate
     */
    public MitosisPool(int x, int y) {
        super(x, y, "res/images/pool.png", STATIONARY_POS);
    }

    /**
     * MitosisPool logic when moved onto
     * @param movable Location match with this MovableActor
     */
    @Override
    public void action(MovableActor movable) {
        MovableActor new1 = null;
        MovableActor new2 = null;
        if(movable instanceof Gatherer) {
            new1 = new Gatherer(movable.location.getX(), movable.location.getY(), movable.getDirection());
            new2 = new Gatherer(movable.location.getX(), movable.location.getY(), movable.getDirection());
        }
        if (movable instanceof Thief) {
            new1 = new Thief(movable.location.getX(), movable.location.getY(), movable.getDirection());
            new2 = new Thief(movable.location.getX(), movable.location.getY(), movable.getDirection());
        }
        assert new1 != null;
        assert new2 != null;
        new1.changeDirection(ACW, ROTATION);
        new2.changeDirection(CW, ROTATION);
        new1.move();
        new2.move();
        ShadowLife.clones.add(new1);
        ShadowLife.clones.add(new2);
        ShadowLife.actorsIterator.remove();
    }
}
