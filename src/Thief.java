import bagel.Image;

/**
 * MovableActor subclass. Contains information regarding thief characteristics
 */
public class Thief extends MovableActor {

    private boolean consuming;

    /**
     * Creates a Thief at Point (x,y) moving in given direction, not consuming
     * @param x X coordinate
     * @param y Y coordinate
     * @param direction Initial direction
     */
    public Thief(int x, int y, int[] direction) {
        super(x, y, "res/images/thief.png", THIEF_POS, direction);
        consuming = false;
    }

    /**
     * Returns Thief consuming status
     */
    public boolean isConsuming() {
        return consuming;
    }

    /**
     * Set Thief consuming status
     */
    public void setConsuming(boolean consuming) {
        this.consuming = consuming;
    }
}
