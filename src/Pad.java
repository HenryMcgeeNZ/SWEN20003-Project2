import bagel.Image;
import java.util.ArrayList;

/**
 * Actor subclass. Contains Pad logic for interactions with other actors
 */
public class Pad extends Actor {

    /**
     * Creates a Pad at Point (x,y)
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Pad(int x, int y) {
        super(x, y, "res/images/pad.png", STATIONARY_POS);
    }

    /**
     * Sets Thief consuming to true
     * @param movable Location match with this MovableActor
     */
    @Override
    public void action(MovableActor movable) {
        if(movable instanceof Thief) {
            ((Thief) movable).setConsuming(true);
        }
    }
}
