import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
*This class represents a chessgame by containing all the metadata and the moves
*of a chessgame
*@author ssalunkhe3
*@version 1.1
*/
public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private StringProperty opening = new SimpleStringProperty(this, "NA");
    private List<String> moves;
/**
*This constructor makes a chess game from the metadata and moves
*@param event the event of the game
*@param site the place where the game happened
*@param date when the game happenned
*@param white player
*@param black player
*@param result who won the game
*
*/
    public ChessGame(String event, String site, String date,
                     String white, String black,  String result) {
        this.event.set(event);
        this.site.set(site);
        this.date.set(date);
        this.white.set(white);
        this.black.set(black);
        this.result.set(result);
        opening.set("NO OPENING");
        moves = new ArrayList<>();
    }
/**
*adds the move to the chessgame metadata
*@param move the string move to add
*
*
*/
    public void addMove(String move) {
        moves.add(move);
        findOpening();
    }
    /**
*adds the moves to the chessgame metadata
*@param in the ArraylIst move to add
*
*
*/
    public void addMoves(ArrayList<String> in) {
        moves = in;
        findOpening();
    }
/**
*gets the moves from the chessgame metadata at index n
*@param n the location of the move
*@return String the move at int n
*
*/

    public String getMove(int n) {
        return moves.get(n - 1);
    }
    /**
*gets the moves to the chessgame metadata
*@return List of the moves
*
*
*/
    public List<String> getMoves() {
        return moves;
    }
    /**
*returns the event tag
*@return String event tag
*
*
*/
    public String getEvent() {
        return event.get();
    }
    /**
*returns the opening tag
*@return String opening tag
*
*
*/
    public String getOpening() {
        return opening.get();
    }
    /**
*returns the site tag
*@return String site tag
*
*
*/
    public String getSite() {
        return site.get();
    }
    /**
*returns the date tag
*@return String date tag
*
*
*/
    public String getDate() {
        return date.get();
    }
    /**
*returns the white tag
*@return String white tag
*
*
*/
    public String getWhite() {
        return white.get();
    }
    /**
*returns the black tag
*@return String black tag
*
*
*/
    public String getBlack() {
        return black.get();
    }
    /**
*returns the result tag
*@return String result tag
*
*
*/
    public String getResult() {
        return result.get();
    }
    private void findOpening() {
        if (moves.size() >= 1) {
            if (moves.get(0).equals("e4 e5")) {
                if (moves.size() >= 2 && moves.get(1).equals("Nf3 d6")) {
                    opening.set("Philidor Defence");
                } else if (moves.size() >= 3
                    && moves.get(1).equals("Nf3 Nc6")) {
                    if (moves.get(2).equals("Bc4 Bc5")) {

                        opening.set("Giuoco Piano");
                    } else if (moves.get(2).substring(0, 3).equals("Bb5")) {

                        opening.set("Ruy Lopez");
                    }
                }
            } else if (moves.get(0).equals("e4 c5")) {

                opening.set("Sicilian Defence");
            } else if (moves.size() >= 2 && moves.get(0).equals("d4 d5")
                && moves.get(1).substring(0, 2).equals("c4")) {

                opening.set("Queen's Gambit");

            } else if (moves.get(0).equals("d4 Nf6")) {

                opening.set("Indian Defence");
            }
        }
    }
}