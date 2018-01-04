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
    public Square(char file , char rank) {
        this("" + file + rank);
    }
// a public constructor Square(String name) which uses a square name such
    // as "a1" to initialize the instance variables described in the other
    // constructor above.
    public Square(String pos) {
        this.file = pos.charAt(0);
        this.rank = pos.charAt(1);
    }
// a public instance method toString() which returns a String representation
 // of the square name, e.g., "a1".
    public String toString() {
        return "" + file + rank;
    }


// a properly written equals method that overrides the equals method from
    // java.lang.Object and returns true for Square objects that have the same
    // file and rank values, false otherwise.
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

    public boolean isValid() {
        boolean a = rank <= '8' && rank >= '1';
        boolean b = file <= 'h' && file >= 'a';
        return a && b;
    }
}