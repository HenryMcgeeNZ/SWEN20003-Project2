import bagel.*;

/**
 * Abstract parent class of all actor types
 */
public abstract class Actor implements Comparable<Actor> {

    protected static final int[] UP = {0, -1};
    protected static final int[] RIGHT = {1, 0};
    protected static final int[] DOWN = {0, 1};
    protected static final int[] LEFT = {-1, 0};
    protected static final int X = 0;
    protected static final int Y = 1;
    protected static final int[][] DIRECTIONS = {UP, RIGHT, DOWN, LEFT};
    protected static final int CW = 1;
    protected static final int ACW = -1;
    protected static final int STATIONARY_POS = 1;
    protected static final int GATHERER_POS = 2;
    protected static final int THIEF_POS = 3;

    private final Image image;
    private final int sortOrder;
    protected Point location;

    /**
     * Create actor at point (x,y) with supplied image
     * @param x X coordinate
     * @param y Y coordinate
     * @param filename Image file path
     * @param sortOrder Order in which actors will be sorted based on game logic
     */
    public Actor(int x, int y, String filename, int sortOrder) {
        image = new Image(filename);
        location = new Point(x, y);
        this.sortOrder = sortOrder;
    }

    /**
     * Draws Actor on screen at current location
     */
    public void render() {
        image.draw(location.getX(), location.getY());
    }

    /**
     * Empty method to be overriden by subclasses. When a movable actor moves
        onto given Actor, this method will execute logic for their interaction
     * @param movable Location match with this MovableActor
     */
    public void action(MovableActor movable) {};

    /**
     * Sorts actors based on game logic requirement
     */
    public int compareTo(Actor other) {
        return this.sortOrder - other.sortOrder;
    }

}
