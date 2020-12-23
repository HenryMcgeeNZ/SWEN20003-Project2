import java.util.ArrayList;

// abstract class of Thief and Gatherer

/**
 * Actor subclass. Parent class of all movable actors
 */
public abstract class MovableActor extends Actor {

    private int[] direction;
    private boolean carrying;
    private boolean active;

    /**
     * Creates a MovableActor at Point (x,y) moving in specified direction
     * @param x X coordinate
     * @param y Y coordinate
     * @param filename Image filepath
     * @param sortOrder Sort order depending on game logic
     * @param direction Initial direction
     */
    public MovableActor(int x, int y, String filename, int sortOrder, int[] direction) {
        super(x, y, filename, sortOrder);
        carrying = false;
        active = true;
        this.direction = direction;
    }

    /**
     * Moves MovableActor one tile in current direction
     */
    public void move() {
        int newX = location.getX() + ShadowLife.TILE_DIMENSION * direction[X];
        int newY = location.getY() + ShadowLife.TILE_DIMENSION * direction[Y];
        location.setX(newX);
        location.setY(newY);
    }

    /**
     * Changes direction of MovableActor
     * @param direction Clockwise or Anticlockwise
     * @param degrees Rotation in degrees
     */
    public void changeDirection(int direction, int degrees) {
        int numRotations = degrees/90 * direction;
        int i;
        for(i = 0; i < DIRECTIONS.length; i++) {
            if(this.direction[X] == DIRECTIONS[i][X]
                    && this.direction[Y] == DIRECTIONS[i][Y]) {
                break;
            }
        }
        if(numRotations > 0) {
            while(numRotations > 0) {
                if(i == DIRECTIONS.length-1) {
                    i = 0;
                } else {
                    i++;
                }
                numRotations -= 1;
            }
        } else {
            while(numRotations < 0) {
                if(i == 0) {
                    i = DIRECTIONS.length-1;
                } else {
                    i--;
                }
                numRotations += 1;
            }
        }
        this.direction = DIRECTIONS[i];
    }

    /**
     * Set new direction
     */
    public void setDirection(int[] direction) {
        this.direction = direction;
    }

    /**
     * Change fruit carrying status
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /**
     * Change active status to false
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Return current direction
     */
    public int[] getDirection() {
        return direction;
    }

    /**
     * Returns carrying status
     */
    public boolean isCarrying() {
        return carrying;
    }

    /**
     * Returns active status
     */
    public boolean isActive() {
        return active;
    }

}

