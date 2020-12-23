import bagel.Image;
import java.util.ArrayList;

// sign with one of four directions

/**
 * Actor subclass. Sign can be one of four directions
 */
public class Sign extends Actor {

    protected final static String SIGN_UP = "res/images/up.png";
    protected final static String SIGN_RIGHT = "res/images/right.png";
    protected final static String SIGN_DOWN = "res/images/down.png";
    protected final static String SIGN_LEFT = "res/images/left.png";

    private final int[] direction;

    /**
     * Creates a sign at Point (x,y) with given direction
     * @param x X coordinate
     * @param y Y coordinate
     * @param filename Image file path
     * @param direction Sign Direction
     */
    public Sign(int x, int y, String filename, int[] direction) {
        super(x, y, filename, STATIONARY_POS);
        this.direction = direction;
    }

    /**
     * Changes actors direction to signs direction
     * @param movable Location match with this MovableActor
     */
    @Override
    public void action(MovableActor movable) {
        movable.setDirection(this.direction);
    }

}
