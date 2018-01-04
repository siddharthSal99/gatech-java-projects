/**
*Represents a single chess move made up of a white ply and black ply
*
*@author ssalunkhe3
*@version 1.1
*/
public class Move {
    private Ply whitePly;
    private Ply blackPly;
/**
* creates a Move object from two plies, white and black
*@param w the white ply
*@param b the black ply
*/
    public Move(Ply w , Ply b) {
        whitePly = w;
        blackPly = b;
    }
/**
* returns the white ply
*@return the white ply
*/
    public Ply getWhitePly() {
        return whitePly;
    }
/**
* returns the black ply
*@return the black ply
*/
    public Ply getBlackPly() {
        return blackPly;
    }
}