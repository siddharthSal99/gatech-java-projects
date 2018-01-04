/**
*This class represents a the idea of a Pawn chess piece
*
*@author ssalunkhe3
*/

public class Pawn extends Piece {
      /**
*Creates a Pawn based on the given color
*
*@param Color c the enum color black or white that represents the piece color
*/
    public Pawn(Color c) {
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
        return "";
    }

    /**
    *returns the forsyth edwards name of  piece
    *@return String that is the forsyth edwards name of the piece
    *
    *
    */
    @Override
    public String fenName() {
        if (this.getColor() == Color.WHITE) {
            return "P";
        } else {
            return "p";
        }
    }
      /**
    *returns the possible moving squares of piece
    *@return Square[] that is the possible locations of the piece
    *
    *
    */
    @Override
    public Square[] movesFrom(Square square) {
        String pos = square.toString();
        char rank = pos.charAt(1);
        char file = pos.charAt(0);
        Square sq1;
        Square[] sqarr;
        if (this.getColor() == Color.WHITE) {
            sq1 = new Square(file , (char) ((int) rank + 1));
            if (rank == '2') {
                Square sq2 = new Square(file , (char) ((int) rank + 2));
                sqarr = new Square[2];
                sqarr[0] = sq1;
                sqarr[1] = sq2;
            } else {
                sqarr = new Square[1];
                sqarr[0] = sq1;
            }

        } else {
            sq1 = new Square(file , (char) ((int) rank - 1));
            if (rank == '7') {
                Square sq2 = new Square(file , (char) ((int) rank - 2));
                sqarr = new Square[2];
                sqarr[0] = sq1;
                sqarr[1] = sq2;
            } else {
                sqarr = new Square[1];
                sqarr[0] = sq1;
            }
        }
        int valids = 0;
        for (Square sq : sqarr) {
            if (sq.isValid()) {
                valids++;
            }
        }
        Square[] actual = new Square[valids];
        for (int i = 0 , j = 0; i < sqarr.length; i++) {
            if (sqarr[i].isValid()) {
                actual[j] = sqarr[i];
                j++;
            }
        }
        return actual;
    }
}