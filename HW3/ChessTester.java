public class ChessTester {
    public static void main(String[] args) {
    Piece knight = new Knight(Color.BLACK);
    assert knight.algebraicName().equals("N");
    assert knight.fenName().equals("n");
    Square[] attackedSquares1 = knight.movesFrom(new Square("f6"));
    // printSq(attackedSquares1);
    // System.out.println("Thats the knight, everybody!");
    // System.out.println();
    // test that attackedSquares contains e8, g8, etc.
    Square a1 = new Square("a1");
    Square otherA1 = new Square('a', '1');
    Square h8 = new Square("h8");
    assert a1.equals(otherA1);
    assert !a1.equals(h8);

    Piece king = new King(Color.BLACK);
    assert king.algebraicName().equals("K");
    assert king.fenName().equals("k");
    Square[] attackedSquares2 = king.movesFrom(new Square("f6"));
    // printSq(attackedSquares2);
    // System.out.println();
    // System.out.println("Thats the king, folks!");
    // test that attackedSquares contains e8, g8, etc.


    Piece queen = new Queen(Color.BLACK);
    assert queen.algebraicName().equals("Q");
    assert queen.fenName().equals("q");
    Square[] attackedSquares3 = queen.movesFrom(new Square("f6"));
    // printSq(attackedSquares3);
    // System.out.println("Give it up for the Queen!");
    // System.out.println();

    Piece bishop = new Bishop(Color.BLACK);
    assert bishop.algebraicName().equals("B");
    assert bishop.fenName().equals("b");
    Square[] attackedSquares4 = bishop.movesFrom(new Square("f6"));
    // printSq(attackedSquares4);
    // System.out.println("Put your hands together for the bishop!");
    // System.out.println();

    Piece pawn = new Pawn(Color.BLACK);
    assert pawn.algebraicName().equals("");
    assert pawn.fenName().equals("p");
    Square[] attackedSquares5 = pawn.movesFrom(new Square("f7"));
     // printSq(attackedSquares5);
    // System.out.println();
    // System.out.println("Please give a warm hand of applause for the Pawn!");

    Piece rook = new Rook(Color.BLACK);
    assert rook.algebraicName().equals("R");
    assert rook.fenName().equals("r");
    Square[] attackedSquares6 = rook.movesFrom(new Square("f6"));
    // printSq(attackedSquares6);
    // System.out.println();
    // System.out.println("Ladies and Gents, Please help me welcome the Rook!");

    }

    public static void printSq(Square[] squares) {
        for (Square s : squares) {
            System.out.println(s);
        }
    }
}