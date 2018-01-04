import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {
    private static char[] rank8 = {'r' , 'n' , 'b' , 'q' ,
                                   'k' , 'b' , 'n' , 'r'};
    private static char[] rank7 = {'p' , 'p' , 'p' , 'p' ,
                                   'p' , 'p' , 'p' , 'p'};
    private static char[] rank6 = {'0' , '0' , '0' , '0' ,
                                   '0' , '0' , '0' , '0'};
    private static char[] rank5 = {'0' , '0' , '0' , '0' ,
                                   '0' , '0' , '0' , '0'};
    private static char[] rank4 = {'0' , '0' , '0' , '0' ,
                                   '0' , '0' , '0' , '0'};
    private static char[] rank3 = {'0' , '0' , '0' , '0' ,
                                   '0' , '0' , '0' , '0'};
    private static char[] rank2 = {'P' , 'P' , 'P' , 'P' ,
                                   'P' , 'P' , 'P' , 'P'};
    private static char[] rank1 = {'R' , 'N' , 'B' , 'Q' ,
                                   'K' , 'B' , 'N' , 'R'};
    private static char[][] board =
    {rank8 , rank7 , rank6 , rank5 , rank4 , rank3 , rank2 , rank1};
    private static int moveCount = 1;


    private static String sanitize(String game) {
        for (int i = 0; i < game.length();) {
            boolean a = !Character.isDigit(game.charAt(i));
            boolean b = !Character.isLetter(game.charAt(i));
            boolean c = !(game.charAt(i) == '.');
            boolean d = !(game.charAt(i) == ' ');
            if (a && b && c && d) {
                game = game.substring(0 , i)
                    + game.substring(i + 1 , game.length());
            } else {
                i++;
            }
        }
        game.replaceAll("." , ". ");
        game = game + " ";


        int count = 0;
        for (int i = 0; i < game.length();) {
            if (game.charAt(i) == '.') {
                count++;
                if (count < 10 && i != 1) {
                    game = game.substring(0 , i - 1) + " "
                        + game.substring(i - 1 , game.length());
                } else if (i == 1) {
                    game = " " + game;
                } else {
                    game = game.substring(0 , i - 2) + " "
                        + game.substring(i - 2 , game.length());
                }
                i += 2;
            } else {
                i++;
            }

        }
        for (int i = 0; i < game.length();) {
            if ((game.charAt(i) <= 56 && game.charAt(i) >= 49)
                && (game.charAt(i - 1) <= 104 && game.charAt(i - 1) >= 97)) {
                game = game.substring(0 , i + 1) + " "
                    + game.substring(i + 1 , game.length());
                i += 2;
            } else {
                i++;
            }
        }
        game = game.replaceAll("\\s+" , " ");
        return game;

    }

    private static String getMaxMove(String game) {
        int ind1 = game.lastIndexOf("." , game.length() - 1);
        int ind2 = game.lastIndexOf(" " , ind1);
        int maxMove = Integer.parseInt(game.substring((ind2 + 1) , ind1));
        if (moveCount > maxMove) {
            return "GAME OVER";
        } else {
            return "CONTINUE";
        }
    }
    private static String getMove(String game) {


        int ind1 = game.lastIndexOf("." , game.length() - 1);
        int ind2 = game.lastIndexOf(" " , ind1);
        int maxMove = Integer.parseInt(game.substring((ind2 + 1) , ind1));
        if (moveCount > maxMove) {
            return "GAME OVER";
        } else if (moveCount == maxMove) {
            String sub = game.substring(ind1 + 2 , game.length() - 1);
            String move = sub;
            if (game.endsWith("#")) {
                move = sub.substring(0 , sub.length() - 2);
            } else if (sub.endsWith("01") || sub.endsWith("10")) {
                move = sub.substring(0 , sub.length() - 3);
            } else if (sub.endsWith("1212") || sub.endsWith("1212")) {
                move = sub.substring(0 , sub.length() - 5);
            } else {
                move = sub;
            }
            return move;
        } else {
            String moveFind = moveCount + ".";
            int ind = game.indexOf(moveFind);
            String moveEnd = (moveCount + 1) + ".";
            int end = game.indexOf(moveEnd);
            String move = game.substring(ind + moveFind.length() + 1 , end - 1);
            return move;
        }
    }

    private static String[] splitMove(String move) {
        return move.split(" ");
    }

    private static String[] parseMove(String move, boolean white) {
        String[] result = new String[4];
        if (move.endsWith("ooo") || move.endsWith("OOO")) {
            result[3] = "ooo";
            move = move.substring(0 , move.length() - 3);
            result[2] = "*";
            result[1] = "*";
            result[0] = "QCASTLE";
            return result;
        } else if (move.endsWith("oo") || move.endsWith("OO")) {
            result[3] = move.substring(move.length() - 2 , move.length());
            move = move.substring(0 , move.length() - 2);
            result[2] = "*";
            result[1] = "*";
            result[0] = "KCASTLE";
            return result;
        } else {
            result[3] = move.substring(move.length() - 2 , move.length());
            move = move.substring(0 , move.length() - 2);
        }
        if (move.endsWith("x")) {
            result[2] = "x";
            move = move.substring(0 , move.length() - 1);
        }
        if (move.length() == 0) {
            result[0] = "P";
        } else {
            if (move.length() == 0) {
                result[0] = "P";
            } else {
                boolean a = move.charAt(0) <= 104 && move.charAt(0) >= 97;
                if (!a) {
                    result[0] = "" + move.charAt(0);
                    move = move.substring(1 , move.length());
                } else {
                    result[1] = "" + move.charAt(0);
                    result[0] = "P";
                }
            }
            if (move.length() > 0) {
                result[1] = move;
                move = move.substring(0 , move.length() - 1);
            }

        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] == null) {
                result[i] = "*";
            }
        }
        return result;
    }


    private static int[][] idMove(String[] move) {
        char pieceChar = move[0].charAt(0);
        char rankfile = move[1].charAt(0);

        String newloc = move[3];
        int rank = 0;
        int file = 0;
        if (newloc.equalsIgnoreCase("OOO")) {
            rank = 100;
            file = 100;
        } else if (newloc.equalsIgnoreCase("OO")) {
            rank = 50;
            file = 50;
        } else {
            rank = 7 - (Integer.parseInt(newloc.substring(1 , 2)) - 1);
            file = (int) newloc.charAt(0) - 97;
        }

        int[][] result = {{rank , file} , {(int) pieceChar ,
                (int) move[2].charAt(0)}};
        return result;
    }


    private static int[] idPiece(int[][] info , char rankfile , boolean white) {
        int[] coords = info[0]; char piece = (char) info[1][0];
        boolean capture = (char) info[1][1] == 'x'; int[] coords1 = null;
        if (coords[0] == 100 || coords[0] == 50) {
            return coords;
        }
        if (piece == 'P' && !white) {
            if (capture) {
                if ((rankfile == '*'
                    || ((int) rankfile - 97 - coords[1]) == -1)
                    && ((board[coords[0] - 1][coords[1] - 1] == 'p'))) {
                    coords1 = new int[]{coords[0] - 1 , coords[1] - 1};
                } else {
                    coords1 = new int[]{coords[0] - 1 , coords[1] + 1};
                }
            } else if (board[coords[0] - 1][coords[1]] == 'p') {
                coords1 = new int[]{coords[0] - 1 , coords[1]};
            } else {
                coords1 = new int[]{coords[0] - 2 , coords[1]};
            }
        } else if (piece == 'P' && white) {
            if (capture) {
                if ((rankfile == '*'
                    || ((int) rankfile - 97 - coords[1] == 1))
                    && ((board[coords[0] + 1][coords[1] + 1] == 'P')))  {
                    coords1 = new int[]{coords[0] + 1 , coords[1] + 1};
                } else {
                    coords1 = new int[]{coords[0] + 1 , coords[1] - 1};
                }
            } else if (board[coords[0] + 1][coords[1]] == 'P') {
                coords1 = new int[]{coords[0] + 1 , coords[1]};
            } else {
                coords1 = new int[]{coords[0] + 2 , coords[1]};
            }
        } else if (piece == 'B' && !white) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'b'
                        && (Math.abs(i - coords[0])
                        == Math.abs(j - coords[1]))) {
                        coords1 = new int[]{i , j};
                    }
                }
            }
        } else if (piece == 'B' && white) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'B'
                        && (Math.abs(i - coords[0])
                        == Math.abs(j - coords[1]))) {
                        coords1 = new int[]{i , j};
                    }
                }
            }
        } else if (piece == 'N' && !white) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'n' && (Math.pow(i - coords[0] , 2)
                        + Math.pow(j - coords[1] , 2) == 5)) {
                        if (rankfile == '*' || (int) rankfile == i
                            || (int) rankfile - 97 == j) {
                            coords1 = new int[]{i , j};
                        }
                    }
                }
            }
        } else if (piece == 'N' && white) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'N' && (Math.pow(i - coords[0] , 2)
                        + Math.pow(j - coords[1] , 2) == 5)) {
                        if (rankfile == '*' || (int) rankfile == i
                            || (int) rankfile - 97 == j) {
                            coords1 = new int[]{i , j};
                        }
                    }
                }
            }
        } else if (piece == 'K' && !white) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'k') {
                        coords1 = new int[]{i , j};
                    }
                }
            }
        } else if (piece == 'K' && white) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'K') {
                        coords1 = new int[]{i , j};
                    }
                }
            }
        } else if (piece == 'R' && !white) {
            boolean keep = true;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'r') {
                        if (((int) rankfile == i
                            || (int) rankfile - 97 == j)) {
                            coords1 = new int[]{i , j};
                        } else if (rankfile == '*') {
                            if (i == coords[0]) {
                                if (j < coords[1]) {
                                    for (int v = j + 1; v < coords[1]; v++) {
                                        if (board[i][v] != '0') {
                                            keep = false;
                                        }
                                    }
                                } else {
                                    for (int v = j - 1; v > coords[1]; v--) {
                                        if (board[i][v] != '0') {
                                            keep = false;
                                        }
                                    }
                                }
                            } else {
                                if (i < coords[0]) {
                                    for (int v = i + 1; v < coords[0]; v++) {
                                        if (board[v][j] != '0') {
                                            keep = false;
                                        }
                                    }
                                } else {
                                    for (int v = i - 1; v > coords[0]; v--) {
                                        if (board[v][j] != '0') {
                                            keep = false;
                                        }
                                    }
                                }
                            }
                            if (keep) {
                                coords1 = new int[]{i , j};
                            }
                        }
                    }
                }
            }
        } else if (piece == 'R' && white) {
            boolean keep = true;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'R') {
                        if (((int) rankfile == i
                            || (int) rankfile - 97 == j)) {
                            coords1 = new int[]{i , j};
                        } else if (rankfile == '*') {
                            if (i == coords[0]) {
                                if (j < coords[1]) {
                                    for (int v = j + 1; v < coords[1]; v++) {
                                        if (board[i][v] != '0') {
                                            keep = false;
                                        }
                                    }
                                } else {
                                    for (int v = j - 1; v > coords[1]; v--) {
                                        if (board[i][v] != '0') {
                                            keep = false;
                                        }
                                    }
                                }
                            } else {
                                if (i < coords[0]) {
                                    for (int v = i + 1; v < coords[0]; v++) {
                                        if (board[v][j] != '0') {
                                            keep = false;
                                        }
                                    }
                                } else {
                                    for (int v = i - 1; v > coords[0]; v--) {
                                        if (board[v][j] != '0') {
                                            keep = false;
                                        }
                                    }
                                }
                            }
                            if (keep) {
                                coords1 = new int[]{i , j};
                            }
                        }
                    }
                }
            }
        } else if (piece == 'Q' && !white) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'q') {
                        coords1 = new int[]{i , j};
                    }
                }
            }
        } else if (piece == 'Q' && white) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'Q') {
                        coords1 = new int[]{i , j};
                    }
                }
            }
        }
        return coords1;
    }

    private static void movePiece(int[] oldCoords , int[] newCoords,
                                  boolean white , char capture) {
        boolean cap = capture == 'x';
        int oldrank = oldCoords[0];
        int oldfile = oldCoords[1];
        int newrank = newCoords[0];
        int newfile = newCoords[1];
        if (oldrank == 50) {
            if (white) {
                board[7][4] = '0';
                board[7][5] = 'R';
                board[7][6] = 'K';
                board[7][7] = '0';
            } else {
                board[0][4] = '0';
                board[0][5] = 'r';
                board[0][6] = 'k';
                board[0][7] = '0';
            }
        } else if (oldrank ==  100) {
            if (white) {
                board[7][0] = '0';
                board[7][1] = '0';
                board[7][2] = 'K';
                board[7][3] = 'R';
                board[7][4] = '0';

            } else {
                board[0][0] = '0';
                board[0][1] = '0';
                board[0][2] = 'k';
                board[0][3] = 'r';
                board[0][4] = '0';
            }
        } else {
            char piece = board[oldrank][oldfile];
            board[newrank][newfile] = piece;
            board[oldrank][oldfile] = '0';
            if (piece == 'P' && cap && board[newrank + 1][newfile] == 'p') {
                board[newrank - 1][newfile] = '0';
            } else if (piece == 'p'
                        && cap
                        && board[newrank - 1][newfile] == 'P') {
                board[newrank + 1][newfile] = '0';
            }

            if (piece == 'P' && newrank == 0) {
                board[newrank][newfile] = 'Q';
            }
            if (piece == 'p' && newrank == 7) {
                board[newrank][newfile] = 'q';
            }
        }

    }

    private static char[][] runGame(String game) {

        String gameSteps = extractGame(game);
        String combMove = getMove(gameSteps);
        int i = 1;
        while (!combMove.equals("GAME OVER")) {
            String[] sepMove = splitMove(combMove);

            String[] moveBreakdownWhite = parseMove(sepMove[0] , true);
            int[][] moveCharacterWhite = idMove(moveBreakdownWhite);
            char cp = (char) moveCharacterWhite[1][1];
            int[] oldCoords = idPiece(moveCharacterWhite ,
                                     moveBreakdownWhite[1].charAt(0) ,
                                     true);

            movePiece(oldCoords , moveCharacterWhite[0] , true , cp);

            if (sepMove.length == 1 || sepMove[1].length() < 2) {
                break;
            }
            String[] moveBreakdownBlack = parseMove(sepMove[1] , false);

            int[][] moveCharacterBlack = idMove(moveBreakdownBlack);
            char cpb = (char) moveCharacterBlack[1][1];
            int[] oldCoordsB = idPiece(moveCharacterBlack ,
                                      moveBreakdownBlack[1].charAt(0) , false);

            movePiece(oldCoordsB , moveCharacterBlack[0] , false , cpb);
            moveCount++;
            i++;
            combMove = getMove(gameSteps);
        }
        return board;
    }






    /**
     * Play out the moves in game and return a String with the game's
     * final position in Forsyth-Edwards Notation (FEN).
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c16.1
     *
     * @param game a `Strring` containing a PGN-formatted chess game or opening
     * @return the game's final position in FEN.
     */
    public static String finalPosition(String game) {
        char[][] finalBoard = runGame(game);
        String finalPos = "";
        for (char[] rank : finalBoard) {
            finalPos = finalPos + processRank(rank) + "\\";
        }
        return finalPos.substring(0 , finalPos.length() - 1);
    }
    private static String processRank(char[] rank) {
        int count = 0;
        String s = "";
        for (int i = 0; i < rank.length; i++) {
            if (rank[i] != '0') {
                if (count != 0) {
                    s = s + Character.forDigit(count , 10);
                }
                s = s + rank[i];
                count = 0;
            } else {
                count++;
            }
        }
        if (count != 0) {
            s = s + Character.forDigit(count , 10);
        }
        return s;
    }


    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param path the relative or abolute path of the file to read
     * @return a String containing the content of the file
     */
    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
    public static String tagValue(String tagName, String game) {
        int ind = game.indexOf(tagName);

        if (ind == -1) {
            return "NOT GIVEN";
        } else {
            int end = game.indexOf("]" , ind);
            String tagStr = game.substring(ind , end);
            int start = tagStr.indexOf("\"");
            int tagEnd = tagStr.indexOf("\"" , start + 1);
            return tagStr.substring(start + 1 , tagEnd);
        }
    }

        private static String extractGame(String game) {
        int ind = game.indexOf("1.");
        game = game.substring(ind);
        // game = sanitize(game);
        return game;
    }

        private static String getMove(String game) {


        int ind1 = game.lastIndexOf("." , game.length() - 1);
        int ind2 = game.lastIndexOf(" " , ind1);
        int maxMove = Integer.parseInt(game.substring((ind2 + 1) , ind1));
        if (moveCount > maxMove) {
            return "GAME OVER";
        } else if (moveCount == maxMove) {
            String sub = game.substring(ind1 + 2 , game.length() - 1);
            String move = sub;
            if (game.endsWith("#")) {
                move = sub.substring(0 , sub.length() - 2);
            } else if (sub.endsWith("01") || sub.endsWith("10")) {
                move = sub.substring(0 , sub.length() - 3);
            } else if (sub.endsWith("1212") || sub.endsWith("1212")) {
                move = sub.substring(0 , sub.length() - 5);
            } else {
                move = sub;
            }
            return move;
        } else {
            String moveFind = moveCount + ".";
            int ind = game.indexOf(moveFind);
            String moveEnd = (moveCount + 1) + ".";
            int end = game.indexOf(moveEnd);
            String move = game.substring(ind + moveFind.length() + 1 , end - 1);
            return move;
        }
    }


    public static void printArray(char[][] arr) {
        int rows = arr.length;
        int columns = arr[1].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void printInt(int[][] arr) {
        int rows = arr.length;
        int columns = arr[1].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }






    public static void main(String[] args) {
         // int[][] gameplay = {{1,2},{3,4},{5,6},{7,8}};
         // printInt(gameplay);
         // gameplay[1][0] = 100;
         // printInt(gameplay);
        String game = fileContent(args[0]);
        //  System.out.printf("length: %10d\n",game.length());



        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));

    }
}