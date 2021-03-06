/**
*represents a chess board square
*
*@author ssalunkhe3
*/
public class Square {
    private char rank;
    private char file;
//     Square – a class to represent squares on a chess board.
    // Square should be instantiable and have the following constructors and
    // methods:

// a public constructor Square(char file, char rank) which uses a file name
    // such as 'a' and rank name such as '1' to initialize instance variables
    // that store the file and rank (as chars), and optionally the String name
     // of the square that would be returned by toString() (see below).
    // Ideally, this constructor should delegate to the other constructor,
    // described below.

    /**
    *creates a square based on the char file and rank locations
    *
    *
    */
    public Square(char file , char rank) throws InvalidSquareException {
        this("" + file + rank);
    }
// a public constructor Square(String name) which uses a square name such
    // as "a1" to initialize the instance variables described in the other
    // constructor above.
    /**
    *creates a square based on the string name of the Square
    *@Param String pos the string representation of the location of square
    *
    */
    public Square(String pos) throws InvalidSquareException {
        if (isValid(pos)) {
            this.file = pos.charAt(0);
            this.rank = pos.charAt(1);
        } else {
            throw new InvalidSquareException(pos);
        }
    }
    /** return the string representation of the square location
    * @return String version of the square location
    */
    public String toString() {
        return "" + file + rank;
    }


// a properly written equals method that overrides the equals method from
    // java.lang.Object and returns true for Square objects that have the same
    // file and rank values, false otherwise.
    /**
    *Tests for equality between two square objects
    *@param Object other another square object
    *@return boolean true if the two objects have same coords else false
    */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (null == other) {
            return false;
        }
        if (!(other instanceof Square)) {
            return false;
        }
        Square that = (Square) other;
        return this.rank == that.rank && this.file == that.file;
    }
    /**Tests for validity of an instance of a square
    *
    *@return boolean true if the square has valid board coords
    */
    public boolean isValid() {
        boolean a = rank <= '8' && rank >= '1';
        boolean b = file <= 'h' && file >= 'a';
        return a && b;
    }
    /**Tests for validity of boards coords given a string location
    *@param String square location of the square
    *
    *@return boolean true if the square has valid board coords
    */
    public boolean isValid(String square) {
        if (square.length() != 2) {
            return false;
        }
        char rank1 = square.charAt(1);
        char file1 = square.charAt(0);
        boolean a = rank1 <= '8' && rank1 >= '1';
        boolean b = file1 <= 'h' && file1 >= 'a';
        return a && b;
    }
    /** returns the rank of a square
    *   @return the rank as a char
    */
    public char getRank() {
        return rank;
    }
 /** returns the file of a square
    *   @return the file as a char
    */
    public char getFile() {
        return file;
    }
}