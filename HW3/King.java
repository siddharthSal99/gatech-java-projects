/**
*This class represents a the idea of a king chess piece
*
*@author ssalunkhe3
*/

public class King extends Piece {
      /**
*Creates a King based on the given color
*
*@param Color c the enum color black or white that represents the piece color
*/
    public King(Color c) {
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
        return "K";
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
            return "K";
        } else {
            return "k";
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
            rank);
        Square sq2 = new Square((char) ((int) file - 1) ,
            rank);
        Square sq3 = new Square(file ,
            (char) ((int) rank + 1));
        Square sq4 = new Square(file ,
            (char) ((int) rank - 1));

        Square sq5 = new Square((char) ((int) file + 1) ,
            (char) ((int) rank - 1));
        Square sq6 = new Square((char) ((int) file - 1) ,
            (char) ((int) rank - 1));
        Square sq7 = new Square((char) ((int) file + 1) ,
            (char) ((int) rank + 1));
        Square sq8 = new Square((char) ((int) file - 1) ,
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