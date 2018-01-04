import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
/** This class is the gui that represents a chess db and displays the
*relevant data for it in nice columns.
*
*@author ssalunkhe3
*@version 1.1
*
*/
public class ChessGui extends Application {
    /**
    *This method sets the scene for the GUI. combines all the tableviews, etc
    * into one method that displays evertyhting.
    * @param stage that sets the scene for the GUI
    *
    */
    @Override
    public void start(Stage stage) {
        TextField filterField = new TextField("Search");
        ChessDb chessDB = new ChessDb();

        ObservableList<ChessGame> games =
            FXCollections.observableArrayList(chessDB.getGames());
        TableView<ChessGame> table = createTable(games);

        FilteredList<ChessGame> wrapGames = new FilteredList<>(games,
            p -> true);

        filterField.textProperty().addListener((obs, oldVal, newVal) -> {
                wrapGames.setPredicate(cg -> {
                        if (newVal == null || newVal.isEmpty()) {
                            return true;
                        }
                        String lcIn = newVal.toLowerCase();
                        if (cg.getWhite().toLowerCase().contains(lcIn)) {
                            return true;
                        }
                        if (cg.getBlack().toLowerCase().contains(lcIn)) {
                            return true;
                        }
                        if (cg.getEvent().toLowerCase().contains(lcIn)) {
                            return true;
                        }
                        if (cg.getSite().toLowerCase().contains(lcIn)) {
                            return true;
                        }
                        if (cg.getOpening().toLowerCase().contains(lcIn)) {
                            return true;
                        }
                        if (cg.getResult().equals("1-0")
                            && newVal.equalsIgnoreCase("white")) {
                            return true;
                        }
                        if (cg.getResult().equals("0-1")
                            && newVal.equalsIgnoreCase("black")) {
                            return true;
                        }
                        return false;
                    });
            });
        SortedList<ChessGame> newData = new SortedList<>(wrapGames);
        newData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(newData);

        Button viewButton = new Button("View");
        viewButton.setOnAction(e -> {
                ChessGame game = table.getSelectionModel().getSelectedItem();
                viewDialog(game);
            });
        viewButton.disableProperty()
            .bind(Bindings.isNull(table.getSelectionModel()
                .selectedItemProperty()));

        Button dismissButton = new Button("Dismiss");
        dismissButton.setOnAction(e -> Platform.exit());

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(viewButton, dismissButton);
        HBox searchBox = new HBox();
        searchBox.getChildren().addAll(filterField);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, buttonBox, searchBox);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Chess DB GUI");
        stage.show();
    }

    private
        TableView<ChessGame>
        createTable(ObservableList<ChessGame> games) {
        TableView<ChessGame> table = new TableView<ChessGame>();
        table.setItems(games);

        TableColumn<ChessGame, String> eventCol =
            new TableColumn<ChessGame, String>("Event");
        eventCol.setCellValueFactory(new PropertyValueFactory("event"));

        TableColumn<ChessGame, String> siteCol =
            new TableColumn<ChessGame, String>("Site");
        siteCol.setCellValueFactory(new PropertyValueFactory("site"));

        TableColumn<ChessGame, String> dateCol =
            new TableColumn<ChessGame, String>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));

        TableColumn<ChessGame, String> whiteCol =
            new TableColumn<ChessGame, String>("White");
        whiteCol.setCellValueFactory(new PropertyValueFactory("white"));

        TableColumn<ChessGame, String> blackCol =
            new TableColumn<ChessGame, String>("Black");
        blackCol.setCellValueFactory(new PropertyValueFactory("black"));

        TableColumn<ChessGame, String> openingCol =
            new TableColumn<ChessGame, String>("Opening");
        openingCol.setCellValueFactory(new PropertyValueFactory("opening"));

        TableColumn<ChessGame, String> resultCol =
            new TableColumn<ChessGame, String>("Result");
        resultCol.setCellValueFactory(new PropertyValueFactory("result"));

        table.getColumns().setAll(eventCol, dateCol, siteCol,
            whiteCol, blackCol, openingCol, resultCol);
        return table;

    }



    private void viewDialog(ChessGame game) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(game.getEvent());
        alert.setHeaderText(String.format("Event: %s%n"
                                        + "Site: %s%n"
                                        + "Date: %s%n"
                                        + "White: %s%n"
                                        + "Black: %s%n"
                                        + "Result: %s",
                                        game.getEvent(), game.getSite(),
                                        game.getDate(), game.getWhite(),
                                        game.getBlack(), game.getResult()));
        String moves = "";
        List<String> gameplay = game.getMoves();
        int i = 1;
        for (String s : gameplay) {
            moves = moves + i + ". " + s + " ";
            i++;
        }
        alert.setContentText(moves);
        alert.showAndWait();
    }

}