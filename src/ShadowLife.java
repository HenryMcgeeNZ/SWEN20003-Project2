/* SWEN20003 project 2b. Author - Henry Mcgee. October 2020

    Comment on the design - I chose to design this project with extendability
    in mind. The number of Actor subclasses could have been reduced by having
    interaction logic in the Gatherer and Thief classes. Instead I chose to make
    a subclass for all the different actors and put the logic in these subclasses
    instead. The main reason for this is if the game were to be extended such
    that actors have more complex functionality and interactions, subclasses
    already exist to easily accommodate these changes.
*/

import bagel.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * ShadowLife game utilising Bagel package
 */
public class ShadowLife extends AbstractGame {

    // command line constants
    private static final int TICK_RATE_INDEX = 0;
    private static final int MAX_TICKS_INDEX = 1;
    private static final int FILE_NAME_INDEX = 2;
    private static final int NUM_ARGS = 3;
    // world file constants
    private static final int TYPE_INDEX = 0;
    private static final int X_INDEX = 1;
    private static final int Y_INDEX = 2;
    private static final int NUM_FIELDS = 3;
    // game play constants
    private static final int CENTRE_X = 512;
    private static final int CENTRE_Y = 384;
    protected static final int TILE_DIMENSION = 64;
    private static final int[] GATHERER_INITIAL = Actor.LEFT;
    private static final int[] THIEF_INITIAL = Actor.UP;

    private static int tickRate;
    private static int maxTicks;
    private static String worldName;
    private int numTicks = 0;
    private long time = System.currentTimeMillis();
    private final Image backGround;
    private static final ArrayList<Actor> actors = new ArrayList<>();
    protected static ListIterator<Actor> actorsIterator;
    protected static ArrayList<MovableActor> clones;

    /**
     * Creates game with background image and actors from world file
     */
    public ShadowLife() {
        super();

        // background image for game
        backGround = new Image("res/images/background.png");

        // read world file and store all actors in ArrayList
        try {
            readWorld();
        } catch(InvalidLineException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        /* sort actors so movable actors are printed last and gatherers perform
            their ticks before thieves */
        Collections.sort(actors);
    }

    /**
     * Main method to read arguments then create and run game
     */
    public static void main(String[] arguments) {
        // read command line arguments from txt file
        String[] args = argsFromFile();

        // checks command line arguments are valid
        try {
            checkArgs(args);
        } catch (InvalidArgsException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        // create and run the game
        ShadowLife game = new ShadowLife();
        game.run();

    }

    /**
     * Read command line arguments from args.txt file
     * @return Return null if can't read file
     */
    public static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("args.txt"), Charset.defaultCharset())
                    .split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check command line arguments are valid, assign tick rate and max ticks
     * @param args Arguments to be checked
     * @throws InvalidArgsException If arguments are not in valid format
     */
    public static void checkArgs(String[] args) throws InvalidArgsException {
        if(args == null || args.length != NUM_ARGS) {
            throw new InvalidArgsException();
        }
        try {
            tickRate = Integer.parseInt(args[TICK_RATE_INDEX]);
            maxTicks = Integer.parseInt(args[MAX_TICKS_INDEX]);
            worldName = args[FILE_NAME_INDEX];
        } catch (Exception e) {
            throw new InvalidArgsException();
        }
        if(tickRate < 0 || maxTicks < 0) {
            throw new InvalidArgsException();
        }
    }

    /**
     * Reads world file and stores all actors in ArrayList
     * @throws InvalidLineException If line in file has invalid format
     */
    public static void readWorld() throws InvalidLineException {
        String line;
        int lineNum = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader(worldName));
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                checkLine(fields, lineNum);
                Actor newActor = createActor(fields, lineNum);
                actors.add(newActor);
                lineNum += 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("error: file \"" + worldName + "\" not found");
            System.exit(-1);
        } catch (IOException e) {
            throw new InvalidLineException(worldName, lineNum);
        }
    }

    /**
     * Checks that line has valid format
     * @param fields Fields to be checked
     * @param lineNum Current file line number
     * @throws InvalidLineException If line in file has invalid format
     */
    public static void checkLine(String[] fields, int lineNum)
            throws InvalidLineException {
        if(fields.length != NUM_FIELDS) {
            throw new InvalidLineException(worldName, lineNum);
        }
        try {
            int x = Integer.parseInt(fields[X_INDEX]);
            int y = Integer.parseInt(fields[Y_INDEX]);
        } catch (Exception e) {
            throw new InvalidLineException(worldName, lineNum);
        }
    }

    /**
     * Takes one line from world file, creates new Actor
     * @param fields Fields to create actor
     * @param lineNum Current file line number
     * @return Returns new Actor
     * @throws InvalidLineException If line has invalid format
     */
    public static Actor createActor(String[] fields, int lineNum) throws InvalidLineException {
        Actor newActor = null;
        int x = Integer.parseInt(fields[X_INDEX]);
        int y = Integer.parseInt(fields[Y_INDEX]);
        String type = fields[TYPE_INDEX].toLowerCase();

        // create actor based on type
        switch (type) {
            case "tree":
                newActor = new Tree(x, y);
                break;
            case "goldentree":
                newActor = new GoldenTree(x, y);
                break;
            case "stockpile":
                newActor = new Stockpile(x, y);
                break;
            case "hoard":
                newActor = new Hoard(x, y);
                break;
            case "pad":
                newActor = new Pad(x, y);
                break;
            case "fence":
                newActor = new Fence(x, y);
                break;
            case "signup":
                newActor = new Sign(x, y, Sign.SIGN_UP, Actor.UP);
                break;
            case "signright":
                newActor = new Sign(x, y, Sign.SIGN_RIGHT, Actor.RIGHT);
                break;
            case "signdown":
                newActor = new Sign(x, y, Sign.SIGN_DOWN, Actor.DOWN);
                break;
            case "signleft":
                newActor = new Sign(x, y, Sign.SIGN_LEFT, Actor.LEFT);
                break;
            case "pool":
                newActor = new MitosisPool(x, y);
                break;
            case "gatherer":
                newActor = new Gatherer(x, y, GATHERER_INITIAL);
                break;
            case "thief":
                newActor = new Thief(x, y, THIEF_INITIAL);
                break;
            default:
                // actor type not recognised, exit program
                throw new InvalidLineException(worldName, lineNum);
        }
        return newActor;
    }

    /**
     * Performs a state update, this method keeps the game running
     */
    @Override
    public void update(Input input) {

        // draw background
        backGround.draw(CENTRE_X, CENTRE_Y);

        // draw actors and fruit counts
        for (Actor actor : actors) {
            actor.render();
        }

        // check for new tick
        if ((time + tickRate) < System.currentTimeMillis()) {
            boolean moveThisTick = false;
            int initialSize = actors.size();
            clones = new ArrayList<MovableActor>();
            actorsIterator = actors.listIterator();
            while(actorsIterator.hasNext()) {
                Actor actor = actorsIterator.next();
                if(actor instanceof MovableActor) {
                    MovableActor movable = (MovableActor) actor;
                    if(movable.isActive()) {
                        // actor is still active, execute move method
                        movable.move();
                        moveThisTick = true;
                        // check for location matches after moving
                        checkLocationMatch(movable);
                    }
                }
            }
            // add any mitosis clones
            if(clones.size() > 0) {
                actors.addAll(clones);
                Collections.sort(actors);
            }
            if(!moveThisTick) {
                // all movable actors are inactive, halt simulation
                simulationHalt(actors, numTicks);
            }
            time = System.currentTimeMillis();
            numTicks++;
        }
        if(numTicks > maxTicks) {
            // simulation has exceeded max number of ticks, exit program
            System.out.println("Timed out");
            System.exit(-1);
        }
    }

    /**
     * Check for location match, if so execute action method
     * @param movable Actor we are checking location match with
     */
    void checkLocationMatch(MovableActor movable) {
        for (Actor other : actors) {
            if (other.location.getX() == movable.location.getX()
                    && other.location.getY() == movable.location.getY()
                    && other != movable) {
                other.action(movable);
                break;
            }
        }
    }

    /**
     * prints number of ticks for this run and number of fruit at each stockpile
        and hoard, then exits program
     * @param actors Actors array to get Hoard and Stockpile fruit counts
     * @param numTicks Number of ticks this game
     */
    public static void simulationHalt(ArrayList<Actor> actors, int numTicks) {
        System.out.println(numTicks + " ticks");
        for (Actor actor : actors) {
            if (actor instanceof Stockpile || actor instanceof Hoard) {
                System.out.println(((FruitHolder) actor).getNumFruit());
            }
        }
        System.exit(0);
    }
}
