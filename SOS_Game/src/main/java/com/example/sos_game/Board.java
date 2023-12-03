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

    //Whether the game is going (0), tied (-1), a blue win (1), or a red win (2)
    int gameState = 0;

    int turnCount = 0;

    //The n by n size of the board, where n is equal to boardSize
    int boardSize;

    //The game type that is being played
    String gameType;

    boolean bluePlayerTurn;

    int bluePlayerScore = 0;
    int redPlayerScore = 0;

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

        //Sets board val to 1 if S or -1 if O, updates the board state to reflect the player move, and then checks fo S-O-S
        if (playerSymbol == 'S') {
            boardVal = 1;
            boardState[x][y] = boardVal;
            sCheck(x, y);
        }
        else {
            boardVal = -1;
            boardState[x][y] = boardVal;
            oCheck(x, y);
        }

        //Register that the turn has been taken
        turnCount += 1;

        //Update the game state in the event that a player has won/tied
        updateGameState();


    }

    //Checks the board after an S move to see if an SOS was made
    void sCheck(int x, int y) {
        boolean spaceAbove = false;
        boolean spaceBelow = false;
        boolean spaceRight = false;
        boolean spaceLeft = false;

        if (y - 1 >= 0 && y - 2 >= 0) {
            spaceAbove = true;
            if (boardState[x][y-1] == -1 && boardState[x][y-2] == 1) {
                scorePoint();
            }
        }
        if (y + 1 < boardSize && y + 2 < boardSize) {
            spaceBelow = true;
            if (boardState[x][y+1] == -1 && boardState[x][y+2] == 1) {
                scorePoint();
            }
        }
        if (x - 1 >= 0 && x - 2 >= 0) {
            spaceLeft = true;
            if (boardState[x-1][y] == -1 && boardState[x-2][y] == 1) {
                scorePoint();
            }
        }
        if (x + 1 < boardSize && x + 2 < boardSize) {
            spaceRight = true;
            if (boardState[x+1][y] == -1 && boardState[x+2][y] == 1) {
                scorePoint();
            }
        }
        if (spaceAbove && spaceLeft) {
            if (boardState[x-1][y-1] == -1 && boardState[x-2][y-2] == 1)  {
                scorePoint();
            }
        }
        if (spaceAbove && spaceRight) {
            if (boardState[x+1][y-1] == -1 && boardState[x+2][y-2] == 1) {
                scorePoint();
            }
        }
        if (spaceBelow && spaceLeft) {
            if (boardState[x-1][y+1] == -1 && boardState[x-2][y+2] == 1) {
                scorePoint();
            }
        }
        if (spaceBelow && spaceRight) {
            if (boardState[x+1][y+1] == -1 && boardState[x+2][y+2] == 1) {
                scorePoint();
            }
        }
    }

    //Checks the board after an O move to see if an SOS was made
    void oCheck(int x, int y) {
        int horizontal = 0;
        int vertical = 0;
        int leftDiagonal = 0;
        int rightDiagonal = 0;

        if (x + 1 < boardSize && x - 1 >= 0 && y + 1 < boardSize && y - 1 >= 0) {
            horizontal = boardState[x-1][y] + boardState[x][y] + boardState[x+1][y];
            vertical = boardState[x][y-1] + boardState[x][y] + boardState[x][y+1];
            leftDiagonal = boardState[x-1][y-1] + boardState[x][y] + boardState[x+1][y+1];
            rightDiagonal = boardState[x+1][y-1] + boardState[x][y] + boardState[x-1][y+1];
        }
        else if (x + 1 < boardSize && x - 1 >= 0) {
            horizontal = boardState[x-1][y] + boardState[x][y] + boardState[x+1][y];
        }
        else if (y + 1 < boardSize && y - 1 >= 0) {
            vertical = boardState[x][y-1] + boardState[x][y] + boardState[x][y+1];
        }

        if (horizontal == 1) {
            scorePoint();
        }
        if (vertical == 1) {
            scorePoint();
        }
        if (leftDiagonal == 1) {
            scorePoint();
        }
        if (rightDiagonal == 1) {
            scorePoint();
        }
    }

    void scorePoint() {
        if (bluePlayerTurn) {
            bluePlayerScore += 1;
        }
        else {
            redPlayerScore += 1;
        }
    }

    void updateGameState() {
        if (gameType == "Simple Game" && turnCount < boardSize * boardSize) {
            if (bluePlayerScore > redPlayerScore) {
                gameState = 1;
            }
            else if (redPlayerScore > bluePlayerScore) {
                gameState = 2;
            }
        }
        else if (turnCount >= boardSize * boardSize) {
            if (bluePlayerScore > redPlayerScore) {
                gameState = 1;
            }
            else if (redPlayerScore > bluePlayerScore) {
                gameState = 2;
            }
            else {
                gameState = -1;
            }
        }
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
        System.out.println("B:" + bluePlayerScore + " R:" + redPlayerScore + " Game State:" + gameState);
    }

    public int[][] getBoardState() {
        //returns a 2D array of values that represents the board state
        return boardState;
    }

    public int getGameState() {
        //returns an integer that represents the game's state
        return gameState;
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
