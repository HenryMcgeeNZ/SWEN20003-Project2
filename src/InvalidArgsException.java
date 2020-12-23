/**
 * Exception when command line arguments are of invalid format
 */
public class InvalidArgsException extends Exception {
    public InvalidArgsException() {
        super("usage: ShadowLife <tick rate> <max ticks> <world file>");
    }
}

