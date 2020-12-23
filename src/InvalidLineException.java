/**
 * Exception when line in world file is of invalid format
 */
public class InvalidLineException extends Exception {
    public InvalidLineException(String fileName, int lineNum) {
        super("error: in file \"" + fileName + "\" at line " + lineNum);
    }
}
