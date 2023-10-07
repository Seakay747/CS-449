package com.example.sos_game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SOSGameController implements Initializable {

    //Data Container is used to pass data between scenes, also has potential if future use with Database is desired
    DataContainer data = DataContainer.getInstance();

    //Declaring an array to store the buttons which act as the Cells on the Game Board
    ArrayList<Button> buttonArray;

    //Board is a structure used for the programs view of the game
    Board board;



    //Creating GUI elements
    @FXML
    private RadioButton blueO;

    @FXML
    private RadioButton blueS;

    @FXML
    private ToggleGroup blueToggle;

    @FXML
    private GridPane gameBoard;

    @FXML
    private Text gameText;

    @FXML
    private Button newGameButton;

    @FXML
    private RadioButton redO;

    @FXML
    private RadioButton redS;

    @FXML
    private ToggleGroup redToggle;

    @FXML
    private Text testTextBoardSize;

    @FXML
    private Text testTextGameType;



    @FXML
    void newGame(MouseEvent event) throws IOException {
        //Setting up the "New Game" button to send user back to the "Create Game" Scene
        Stage gameStage = (Stage) newGameButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SOSGame.class.getResource("CreateGame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        gameStage.setTitle("SOS Game");
        gameStage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initializing the Game Board

        //TO-DO Delete Later, used for testing and verification; and to show moves work in both game modes
        testTextBoardSize.setText("Board Size is " + String.valueOf(data.getBoardSize()));
        testTextGameType.setText(data.getGameType());

        //Setting the initial turn to start with the Blue Player
        gameText.setText("Blue Player's Turn");

        //Creating the button array to be filled by (Board Size)^2 many buttons
        buttonArray = new ArrayList<>();
        board = new Board();

        //Creating the Game Board
        createBoard(buttonArray);
    }



    public void createBoard(ArrayList<Button> buttonArray) {

        //Filling the button array and the game board with buttons and set the board state to all 0 (empty)
        for (int i = 0; i < (data.getBoardSize() * data.getBoardSize()); i++) {
            Button tempButton = new Button();
            buttonArray.add(tempButton);
            GridPane.setConstraints(tempButton, i % data.getBoardSize(), i / data.getBoardSize());
            gameBoard.getChildren().add(tempButton);
        }

        //Running each button through a set-up process
        buttonArray.forEach(this::setupButton);

        //Initializing the Board
        board.boardInit(data.getBoardSize(), data.getGameType());

        //TO-DO Delete later, included for testing
        board.printBoardState();
    }

    private void setupButton(Button button) {
        //Height, width, alignment, color, and font to be applied to the button
        button.setPrefHeight((double) 500 / data.getBoardSize());
        button.setPrefWidth ((double) 500 / data.getBoardSize());
        button.setAlignment(Pos.CENTER);
        button.setStyle("-fx-background-color: transparent;");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        /*
        Setting up a quality of life on-hover feature
        that highlights the Cell a user hovers with the respective player color
        */
        button.setOnMouseEntered(event -> {
            if (board.getBluePlayerTurn()) {
                button.setStyle("-fx-background-color: lightblue;");
            }
            else {
                button.setStyle("-fx-background-color: pink;");
            }
        });
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent;"));

        /*
        Giving each button an on-click feature that sets an S or an O in the clicked Cell
        and then changed the turn to the other player
        */
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            changeTurn();
        });
    }

    public void setPlayerSymbol(Button button) {

        int buttonIndex = buttonArray.indexOf(button);

        //Sets an S or an O in the clicked Cell
        if ((board.getBluePlayerTurn() && blueToggle.getSelectedToggle().equals(blueS)) || !board.getBluePlayerTurn() && redToggle.getSelectedToggle().equals(redS)) {
            button.setText("S");
            board.registerMove(buttonIndex, 'S');
        }
        else {
            button.setText("O");
            board.registerMove(buttonIndex, '0');
        }

        //TO-DO Delete later, included for testing
        board.printBoardState();

        button.setDisable(true);
        button.setOpacity(1.0);
    }

    public void changeTurn() {
        //Checks for whose turn it is, and then flips the boolean to change turns
        if (board.getBluePlayerTurn()) {
            board.setBluePlayerTurn(false);
            gameText.setText("Red Player's Turn");
        }
        else {
            board.setBluePlayerTurn(true);
            gameText.setText("Blue Player's Turn");
        }
    }

}
