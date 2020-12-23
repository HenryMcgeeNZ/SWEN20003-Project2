/**
 * Represents location of Actor as (x,y) coordinate
 */
public class Point {
    private int x;
    private int y;

    /**
     * Creates a Point at (x,y)
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set X coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set Y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Return Y coordinate
     */
    public int getY() {
        return y;
    }
}
