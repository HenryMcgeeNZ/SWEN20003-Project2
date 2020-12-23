import bagel.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Actor subclass. Abstract class for actors which have finite amounts of fruit
    which are to be displayed on screen
 */
public abstract class FruitHolder extends Actor {

    private final int XOFFSET = 40;
    private final int YOFFSET = 20;

    private final Font font = new Font("res/VeraMono.ttf", 24);
    private int numFruit;

    /**
     * Create FruitHolder at point (x,y) with starting number of fruit
     * @param x X coordinate
     * @param y Y coordinate
     * @param filename Image file path
     * @param sortOrder Order in which actors are to be sorted based on game logic
     * @param startFruit Starting number of fruit
     */
    public FruitHolder(int x, int y, String filename, int sortOrder, int startFruit) {
        super(x, y, filename, sortOrder);
        this.numFruit = startFruit;
    }

    /**
     * Returns current number of fruit
     */
    public int getNumFruit() {
        return numFruit;
    }

    /**
     * Sets number of fruit
     */
    public void setNumFruit(int numFruit) {
        this.numFruit = numFruit;
    }

    /**
     * Renders image to screen at current location and draws number of fruit
     */
    @Override
    public void render() {
        super.render();
        drawNumFruit();
    }

    /**
     * Draws number of fruit in top left corner of image
     */
    public void drawNumFruit() {
        font.drawString(Integer.toString(numFruit),
                location.getX()-XOFFSET, location.getY()-YOFFSET);
    }

    /**
     * Method when Gatherer moves onto a Hoard or Stockpile
     * @param gatherer Gatherer at location
     */
    public void gathererAction(Gatherer gatherer) {
        if(gatherer.isCarrying()) {
            gatherer.setCarrying(false);
            setNumFruit(getNumFruit() + 1);
        }
        gatherer.changeDirection(CW, 180);
    }
}
