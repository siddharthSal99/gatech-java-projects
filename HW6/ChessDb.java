import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FilenameFilter;
/** This class holds a bunch of chess games as a db with many methods to acc
*ess those chessgames
*@author ssalunkhe3
*@version 1.1
*/
public class ChessDb {

    private List<ChessGame> games;
/**
*
*
*Creates a chess db object wth unset games and the predtermined two games
*
*/
    public ChessDb() {
        games = new ArrayList<>();
        games.add(morphyIsouard());
        games.add(talFischer());

        String currentDir = System.getProperty("user.dir");
        File folder = new File(currentDir);
        File[] listOfFiles = folder.listFiles(
            new FilenameFilter() {
                @Override

                public boolean accept(File dir, String name) {
                    return name.endsWith(".pgn");
                }
            });
        List<String> pgnfiles = new ArrayList<>();
        for (File f : listOfFiles) {
            String str = "";
            try {
                str =  f.getCanonicalPath();
            } catch (IOException e) {
                System.out.println("Check your file, fam");
            }
            pgnfiles.add(str);
        }

        for (String s : pgnfiles) {
            String content = fileContent(s);
            String gameplay = extractGame(content);
            ArrayList<String> justMoves = splitMoves(gameplay);
            ChessGame c = new ChessGame(
                tagValue("Event", content) ,
                tagValue("Site", content) ,
                tagValue("Date", content) ,
                tagValue("White", content) ,
                tagValue("Black", content) ,
                tagValue("Result", content)
                );
            c.addMoves(justMoves);
            games.add(c);
        }
    }
/**
*returns the games in the database as a list
*@return the List of the games
*
*
*/
    public List<ChessGame> getGames() {
        return games;
    }

    private ChessGame morphyIsouard() {
        ChessGame game = new ChessGame(
            "A Night at the Opera",
            "Paris Opera House",
            "1958.01.01",
            "Morphy, Paul",
            "Comte Isouard de Vauvenargues and Karl II, Duke of Brunswick",
            "1-0"
        );
        game.addMove("e4 e5");
        game.addMove("Nf3 d6");
        game.addMove("d4 Bg4");
        game.addMove("dxe5 Bxf3");
        game.addMove("Qxf3 dxe5");
        game.addMove("Bc4 Nf6");
        game.addMove("Qb3 Qe7");
        game.addMove("Nc3 c6");
        game.addMove("Bg5 b5");
        game.addMove("Nxb5 cxb5");
        game.addMove("Bxb5+ Nbd7");
        game.addMove("O-O-O Rd8");
        game.addMove("Rxd7 Rxd7");
        game.addMove("Rd1 Qe6");
        game.addMove("Bxd7+ Nxd7");
        game.addMove("Qb8+ Nxb8");
        game.addMove("Rd8#");
        return game;
    }

    private ChessGame talFischer() {
        ChessGame game = new ChessGame(
            "Bled-Zagreb-Belgrade Candidates",
            "Bled, Zagreb & Belgrade YUG",
            "1959.10.11",
            "Tal, Mikhail",
            "Fischer, Robert James",
            "1-0"
        );
        game.addMove("d4 Nf6");
        game.addMove("c4 g6");
        game.addMove("Nc3 Bg7");
        game.addMove("e4 d6");
        game.addMove("Be2 O-O");
        game.addMove("Nf3 e5");
        game.addMove("d5 Nbd7");
        game.addMove("Bg5 h6");
        game.addMove("Bh4 a6");
        game.addMove("O-O Qe8");
        game.addMove("Nd2 Nh7");
        game.addMove("b4 Bf6");
        game.addMove("Bxf6 Nhxf6");
        game.addMove("Nb3 Qe7");
        game.addMove("Qd2 Kh7");
        game.addMove("Qe3 Ng8");
        game.addMove("c5 f5");
        game.addMove("exf5 gxf5");
        game.addMove("f4 exf4");
        game.addMove("Qxf4 dxc5");
        game.addMove("Bd3 cxb4");
        game.addMove("Rae1 Qf6");
        game.addMove("Re6 Qxc3");
        game.addMove("Bxf5+ Rxf5");
        game.addMove("Qxf5+ Kh8");
        game.addMove("Rf3 Qb2");
        game.addMove("Re8 Nf6");
        game.addMove("Qxf6+ Qxf6");
        game.addMove("Rxf6 Kg7");
        game.addMove("Rff8 Ne7");
        game.addMove("Na5 h5");
        game.addMove("h4 Rb8");
        game.addMove("Nc4 b5");
        game.addMove("Ne5 1-0");
        return game;

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

    private ArrayList<String> splitMoves(String game) {
        Scanner lineScan = new Scanner(game);
        ArrayList<String> indMoves = new ArrayList<>();
        int i = 1;
        while (lineScan.hasNext()) {
            String w = "";
            String b = "";
            lineScan.next();
            String partial = "";
            if (lineScan.hasNext()) {
                w = lineScan.next();
            }
            partial = partial + w;
            if (lineScan.hasNext()) {
                b = lineScan.next();
                partial = partial + " " + b;
            }
            indMoves.add(partial);
            i++;
        }
        return indMoves;
    }


}