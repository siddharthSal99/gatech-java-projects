/**
*This class represents a the idea of a  Queen chess piece
*
*@author ssalunkhe3
*/

public class Queen extends Piece {
      /**
*Creates a Queen based on the given color
*
*@param Color c the enum color black or white that represents the piece color
*/
    public Queen(Color c) {
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
        return "Q";
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
            return "Q";
        } else {
            return "q";
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
        boolean ej1 = rank == '1' || rank == '8' || file == 'a' || file == 'h';
        boolean ej2 = rank == '2' || rank == '7' || file == 'b' || file == 'g';
        boolean ej3 = rank == '3' || rank == '6' || file == 'b' || file == 'g';
        boolean ej4 = rank == '4' || rank == '5' || file == 'b' || file == 'g';
        Square[] moves;
        if (ej1) {
            moves = findSquares(rank , file , 21);
        } else if (ej2) {
            moves = findSquares(rank , file , 23);
        } else if (ej3) {
            moves = findSquares(rank , file , 25);
        } else {
            moves = findSquares(rank , file , 27);
        }
        return moves;
    }

    private Square[] findSquares(char rank , char file , int num) {
        Square[] sqarr = new Square[num];
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
        for (char c = (char) ((int) file - 1) , r = (char) ((int) rank - 1);
            c >= 'a' && r >= '1'; c-- , r--) {
            sqarr[count] = new Square(c , r);
            count++;
        }
        for (char c = (char) ((int) file - 1) , r = (char) ((int) rank + 1);
            c >= 'a' && r <= '8'; c-- , r++) {
            sqarr[count] = new Square(c , r);
            count++;
        }
        for (char c = (char) ((int) file + 1) , r = (char) ((int) rank - 1);
            c <= 'h' && r >= '1'; c++ , r--) {
            sqarr[count] = new Square(c , r);
            count++;
        }
        for (char c = (char) ((int) file + 1) , r = (char) ((int) rank + 1);
            c <= 'h' && r <= '8'; c++ , r++) {
            sqarr[count] = new Square(c , r);
            count++;
        }
        return sqarr;
    }

}