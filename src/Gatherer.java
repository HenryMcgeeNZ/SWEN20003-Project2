import bagel.*;
import java.util.ArrayList;

/**
 * Subclass of MovableActor. Contains gatherer logic for interactions with other actors
 */
public class Gatherer extends MovableActor {

    /**
     * Creates a gatherer at point (x,y) moving in specified direction
     * @param x X coordinate
     * @param y Y coordinate
     * @param direction Initial direction
     */
    public Gatherer(int x, int y, int[] direction) {
        super(x, y, "res/images/gatherer.png", GATHERER_POS, direction);
    }

    /**
     * Rotates Thief 270 counter clockwise
     * @param movable Location match with this MovableActor
     */
    @Override
    public void action(MovableActor movable) {
        if(movable instanceof Thief) {
            movable.changeDirection(CW, 270);
        }
    }
}
