/**
*This class represents a the idea of a rook chess piece
*
*@author ssalunkhe3
*@version 1.2
*/

public class Rook extends Piece {
      /**
*Creates a Rook based on the given color
*
*@param c the enum color black or white that represents the piece color
*/
    public Rook(Color c) {
        super(c);
    }

    /**
    *returns the algebraic name of  piece
    *@return String that is the algebraic name of the piece
    *
    *
    */

    @Override
    public String algebraicName() {
        return "R";
    }


    /**
    *returns the forsyth edwards name of  piece
    *@return String that is the forsyth edwards name of the piece
    *
    *
    */
    @Override
    public String fenName() {
        return getColor() == Color.WHITE ? "R" : "r";
    }

      /**
    *returns the possible moving squares of piece
    *@return Square[] that is the possible locations of the piece
    *
    *
    */
    @Override
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[27];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int i = 1; i <= 8; i++) {
            char[] ranks = new char[]{(char) (rank + i), (char) (rank - i)};
            char[] files = new char[]{(char) (file + i), (char) (file - i)};
            if (isInBoard(files[0], rank)) {
                sq[counter++] = new Square(files[0], rank);
            }
            if (isInBoard(files[1], rank)) {
                sq[counter++] = new Square(files[1], rank);
            }
            if (isInBoard(file, ranks[0])) {
                sq[counter++] = new Square(file, ranks[0]);
            }
            if (isInBoard(file, ranks[1])) {
                sq[counter++] = new Square(file, ranks[1]);
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }

}