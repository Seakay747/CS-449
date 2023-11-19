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
    private RadioButton computerBlue;

    @FXML
    private RadioButton computerBoth;

    @FXML
    private RadioButton computerNone;

    @FXML
    private ToggleGroup computerPlayers;

    @FXML
    private RadioButton computerRed;

    @FXML
    private ToggleGroup gameType;

    @FXML
    private RadioButton generalGame;

    @FXML
    private RadioButton recordFalse;

    @FXML
    private ToggleGroup recordGame;

    @FXML
    private RadioButton recordTrue;

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

         //Getting and Setting user input for Computer Players
         if(computerPlayers.getSelectedToggle().equals(computerNone)) {
             data.setComputerPlayer(0);
         }
        else if(computerPlayers.getSelectedToggle().equals(computerRed)) {
            data.setComputerPlayer(1);
        }
        else if(computerPlayers.getSelectedToggle().equals(computerBlue)) {
            data.setComputerPlayer(2);
        }
        else if(computerPlayers.getSelectedToggle().equals(computerBoth)) {
            data.setComputerPlayer(3);
        }

        //Getting and Setting user input for Record Game
        if(recordGame.getSelectedToggle().equals(recordTrue)) {
            data.setRecordGame(true);
        }
        else {
            data.setRecordGame(false);
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
