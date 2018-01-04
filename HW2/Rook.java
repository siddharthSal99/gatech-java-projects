/**
*This class represents a the idea of a rook chess piece
*
*@author ssalunkhe3
*/

public class Rook extends Piece {
      /**
*Creates a Rook based on the given color
*
*@param Color c the enum color black or white that represents the piece color
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
        if (this.getColor() == Color.WHITE) {
            return "R";
        } else {
            return "r";
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
        Square[] sqarr = new Square[14];
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (file != (char) (97 + i)) {
                Square sq = new Square((char) (97 + i) , rank);
                sqarr[count] = sq;
                count++;
            }
        }
        for (int i = 1; i <= 8; i++) {
            if (rank != Integer.toString(i).charAt(0)) {
                Square sq = new Square(file , Integer.toString(i).charAt(0));
                sqarr[count] = sq;
                count++;
            }
        }
        return sqarr;
    }

}