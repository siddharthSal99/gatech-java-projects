/**
*This abstract class represents a the idea of a chess piece
*
*@author ssalunkhe3
*@version 1.1
*/


public abstract class Piece {
// a public constructor that takes a Color parameter and stores its value in
// an instance variable
    private Color color;
    /**
*Creates a Piece based on the given color
*
*@param c the enum color black or white that represents the piece color
*/
    public Piece(Color c) {
        color = c;
    }

// a public getColor() instance method that returns the Color of the piece
    /**
    *Returns the color of the piece
    *@return Color c the color of the piece
    */
    public Color getColor() {
        return color;
    }
// a public abstract instance method algebraicName() which returns a String
// containing the algebraic name of the piece, e.g., "" for pawns, or one of
// "K", "Q", "B", "N", "R".

    /**
    *returns the algebraic name of  piece
    *@return String that is the algebraic name of the piece
    *
    *
    */
    public abstract String algebraicName();
// a public abstract instance method fenName() which returns a String
 // containing
// the FEN name for the piece.
    /**
    *returns the forsyth edwards name of  piece
    *@return String that is the forsyth edwards name of the piece
    *
    *
    */
    public abstract String fenName();
// a public abstract instance method movesFrom(Square square) which returns a
// Square[] containg all the squares the piece could move to from square on a
    // chess board containing only the piece.
     /**
    *returns the possible moving squares of piece
    *@param square starting square
    *@return Square[] that is the possible locations of the piece
    *
    *
    */
    public abstract Square[] movesFrom(Square square);
    /** returns if the piece is on the board
    *@param file the file of the piece
    *@param rank the rank of the piece
    *@return boolean whether it is on the board
    */
    public boolean isInBoard(char file, char rank) {
        return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
    }
    /**returns a string version of the piece
    * @return String the string version of the piece
    */
    public String toString() {
        return color.toString() + " " + this.getClass();
    }

}