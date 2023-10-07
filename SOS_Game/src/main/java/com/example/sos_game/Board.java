package com.example.sos_game;

import java.util.ArrayList;

public class Board {
    //Custom structure made to track the game board's state and to handle logic outside the GUI

    /*
    Board state are the values in the board;
    Empty Cell = 0,
    S Cell = 1,
    O Cell = -1
     */
    int[][] boardState;

    //The n by n size of the board, where n is equal to boardSize
    int boardSize;

    //The game type that is being played
    String gameType;

    boolean bluePlayerTurn;

    public void boardInit(int boardSize, String gameType) {
        //Initializes the board and fills it with Empty Cells

        //Sets the Board objects local variables to the ones picked in the GUI
        this.boardSize = boardSize;
        this.gameType = gameType;
        bluePlayerTurn = true;

        //Goes x (row) by y (column) filling the board with 0s
        boardState = new int[boardSize][boardSize];
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++){
                boardState[x][y] = 0;
            }
        }
    }


    public void registerMove(int buttonIndex, char playerSymbol) {
        //Updates the board state to reflect a user move

        //Splits the index of the button into x (or row) and y (or column) components
        int boardVal;
        int x = buttonIndex % boardSize;
        int y = buttonIndex / boardSize;

        //Sets board val to 1 if S or -1 if O
        if (playerSymbol == 'S') {
            boardVal = 1;
        }
        else {
            boardVal = -1;
        }

        //Updates the cell, which corresponds to the button a user clicked in the GUI
        //Sets the updated cell to reflect the S or the O placed by the user, using 1 and -1 respectively
        boardState[x][y] = boardVal;
    }

    public boolean pointScored() {
        // TO-DO
        return false;
    }

    public void printBoardState() {
        //Created for testing purposes, feel free to ignore

        System.out.println();
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++){
                System.out.print(boardState[x][y] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getBoardState() {
        //returns a 2D array of values that represents the board state
        return boardState;
    }

    public int getBoardSize() {
        //returns the board size that was set during initializing the board
        return boardSize;
    }

    public String getGameType() {
        //returns the game type that was set during initializing the board
        return gameType;
    }

    public boolean getBluePlayerTurn() {
        return bluePlayerTurn;
    }

    public void setBluePlayerTurn(boolean bluePlayerTurn) {
        this.bluePlayerTurn = bluePlayerTurn;
    }
}
