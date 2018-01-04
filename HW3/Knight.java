/**
*This class represents a the idea of a knight chess piece
*
*@author ssalunkhe3
*/

public class Knight extends Piece {
  /**
*Creates a knight based on the given color
*
*@param Color c the enum color black or white that represents the piece color
*/
    public Knight(Color c) {
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
        return "N";
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
            return "N";
        } else {
            return "n";
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
        Square sq1 = new Square((char) ((int) file + 1) ,
            (char) ((int) rank - 2));
        Square sq2 = new Square((char) ((int) file - 1) ,
            (char) ((int) rank - 2));
        Square sq3 = new Square((char) ((int) file + 1) ,
            (char) ((int) rank + 2));
        Square sq4 = new Square((char) ((int) file - 1) ,
            (char) ((int) rank + 2));

        Square sq5 = new Square((char) ((int) file + 2) ,
            (char) ((int) rank - 1));
        Square sq6 = new Square( (char) ((int) file - 2) ,
            (char) ((int) rank - 1));
        Square sq7 = new Square((char) ((int) file + 2) ,
            (char) ((int) rank + 1));
        Square sq8 = new Square( (char) ((int) file - 2) ,
            (char) ((int) rank + 1));
        Square[] ops = {sq1 , sq2 , sq3 , sq4 , sq5 , sq6 , sq7 , sq8};
        int count = 0;
        for (Square sq : ops) {
            if (sq.isValid()) {
                count++;
            }
        }
        Square[] actual = new Square[count];
        for (int i = 0 , j = 0; i < 8; i++) {
            if (ops[i].isValid()) {
                actual[j] = ops[i];
                j++;
            }
        }
        return actual;
    }


}