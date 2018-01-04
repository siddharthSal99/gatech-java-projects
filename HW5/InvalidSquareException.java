/**
*@author ssalunkhe3
*@version 1.4
*represents an exception thrown by an invalid file rank combo square
*This exception is an unchecked exception, extending runtime exception, because
*the exception is not expected, because the input files contain actual game
*moves and likely do not contain illegal moves. If a file contains an illegal
*square, the problem is likely an error with the caller's code.
**/
public class InvalidSquareException extends RuntimeException {
    private String name;
    /** creates an invalid square exception based on the name of the square
    *@param square the name of the square.
    *
    *
    */
    public InvalidSquareException(String square) {
        super(square);
        this.name = square;
    }
    /** returns the error message of the exception
    *@return String the message of the error, the square name.
    *
    *
    */
    public String getMessage() {
        return name;
    }
}