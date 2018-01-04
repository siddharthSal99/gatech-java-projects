import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;
/**
*This class represents a chessgame that has certain filters to search for moves
*
*@author ssalunkhe3
*@version 1.2
*/
public class ChessGame {
    private List<Move> moves;
    /** creates a chessgame from a list of moves
    *@param m a list of moves to create a chessgame
    *
    */
    public ChessGame(List<Move> m) {
        moves = m;
    }
    /** returns the move at a particular index in the list
    *@param n the nth move
    *@return Move at index n
    */
    public Move getMove(int n) {
        return moves.get(n);
    }
    /** returns the move at a particular index in the list
    *@return Moves
    */
    public List<Move> getMoves() {
        return moves;
    }
    /** returns the filtered move list by predicate
    *@param filter the predicate to be applied to this list
    *@return a filtered list of moves
    */
    public List<Move> filter(Predicate<Move> filter) {
        List<Move> temp = new ArrayList<Move>();
        for (Move m : moves) {
            if (filter.test(m)) {
                temp.add(m);
            }
        }
        return temp;
    }
    /** returns the filtered move list
    *@return a filtered list of moves by if it has a comment
    */
    public List<Move> getMovesWithComment() {
        return filter(m -> {
                return (m.getBlackPly().getComment().isPresent())
                    || (m.getWhitePly().getComment().isPresent());
            });
    }
    /** returns the filtered move list
    *@return a filtered list of moves by if it doesn't have a comment
    */
    public List<Move> getMovesWithoutComment() {
        return filter(new Predicate<Move>() {
            public boolean test(Move m) {
                return !(m.getBlackPly().getComment().isPresent())
                    && !(m.getWhitePly().getComment().isPresent());
            }
        });
    }
    /** returns the filtered move list
    *@param p the piece to check for presence in a move
    *@return a filtered list of moves by if it doesn't have a comment
    */
    public List<Move> getMovesWithPiece(Piece p) {
        return filter(new MovesWithPiecePredicate(p));
    }

    private class MovesWithPiecePredicate implements Predicate<Move> {
        private String pieceToFilter;
        public MovesWithPiecePredicate(Piece p) {
            pieceToFilter = p.algebraicName();
        }

        public boolean test(Move move) {
            String w = move.getWhitePly().getPiece().algebraicName();
            String b = move.getBlackPly().getPiece().algebraicName();

            return w.equals(pieceToFilter) || b.equals(pieceToFilter);
        }
    }
}