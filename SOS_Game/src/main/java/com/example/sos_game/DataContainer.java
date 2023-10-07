package com.example.sos_game;

public class DataContainer {
    //Data container is a public class that allows for sharing data between scenes without using a txt file

    private static final DataContainer instance = new DataContainer();

    private int boardSize;

    private String gameType;

    private DataContainer(){}

    public static DataContainer getInstance() {
        return instance;
    }

    public int getBoardSize() {
        return boardSize;
    }
    public String getGameType() {
        return gameType;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}

/* Reference used for this strategy of Data Storage: https://www.youtube.com/watch?v=MsgiJdf5njc */