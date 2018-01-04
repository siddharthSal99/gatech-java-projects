import java.util.Optional;
/**
*represents a ply object using the parts of a ply: start end piece comment
*
*@author ssalunkhe3
*@version 1.1
*/
public class Ply {
    private Piece piece;
    private Square from;
    private Square to;
    private Optional<String> comment;
    /**
    *creates a ply from the piece, start, end squares, and comment
    *@param p the piece moving
    *@param f the starting square
    *@param t the ending square
    *@param c the optional comment
    */
    public Ply(Piece p , Square f , Square t , Optional<String> c) {
        piece = p;
        from = f;
        to = t;
        comment = c;
    }
    /** returns the piece of the ply
    *@return Piece moving
    */
    public Piece getPiece() {
        return piece;
    }
    /** returns the starting square of the ply
    *@return Square from
    */
    public Square getFrom() {
        return from;
    }
    /** returns the ending of the ply
    *@return Square to
    */
    public Square getTo() {
        return to;
    }
    /** returns the comment of the ply
    *@return Optional Comment
    */
    public Optional<String> getComment() {
        return comment;
    }

}