package com.example.sos_game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateGameController implements Initializable {

    //Data container used to send data between scenes
    DataContainer data = DataContainer.getInstance();

    //Creating GUI Elements
    @FXML
    private Spinner<Integer> boardSize;
    SpinnerValueFactory<Integer> boardSizeSVF
            = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 10, 1);

    @FXML
    private ToggleGroup gameType;

    @FXML
    private RadioButton generalGame;

    @FXML
    private RadioButton simpleGame;

    @FXML
    private Button startGameButton;

    @FXML
    void startGame(MouseEvent event) throws IOException {
        /*
        Setting up the "Create Game" button to send user to the "SOS Game Scene"
        and send user data to the Data Container
         */

        //Getting and Setting user input for Board Size
         int tempBoardSize = boardSize.getValue();
         data.setBoardSize(tempBoardSize);

         //Getting and Setting user input for Game Type
         if(gameType.getSelectedToggle().equals(generalGame)) {
             data.setGameType("General Game");
         }
         else if(gameType.getSelectedToggle().equals(simpleGame)) {
             data.setGameType("Simple Game");
         }

         //Setting up and sending user to the next scene
        Stage gameStage = (Stage) startGameButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SOSGame.class.getResource("SOSGame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        gameStage.setTitle("SOS Game");
        gameStage.setScene(scene);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting board size spinner to have 3-10 values
        boardSize.setValueFactory(boardSizeSVF);
    }
}
