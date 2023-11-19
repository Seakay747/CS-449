package com.example.sos_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {


    @Test
    void testCreateEmpty3x3Board() {
        Board testBoard = new Board();
        testBoard.boardInit(3, "Simple Game");
        int[][] testArray = testBoard.getBoardState();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                assertEquals(0, testArray[x][y]);
            }
        }
    }

    @Test
    void testCreateEmpty10x10Board() {
        Board testBoard = new Board();
        testBoard.boardInit(10, "General Game");
        int[][] testArray = testBoard.getBoardState();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                assertEquals(0, testArray[x][y]);
            }
        }
    }

    @Test
    void testRegisterMove_S() {
        Board testBoard = new Board();
        Random random = new Random();
        int buttonIndex = random.nextInt(9);
        testBoard.boardInit(3, "Simple Game");
        testBoard.registerMove(buttonIndex, 'S');
        int[][] testArray = testBoard.getBoardState();
        int x = buttonIndex % 3;
        int y = buttonIndex / 3;
        assertEquals(1, testArray[x][y]);
    }

    @Test
    void testRegisterMove_O() {
        Board testBoard = new Board();
        Random random = new Random();
        int buttonIndex = random.nextInt(100);
        testBoard.boardInit(10, "General Game");
        testBoard.registerMove(buttonIndex, 'O');
        int[][] testArray = testBoard.getBoardState();
        int x = buttonIndex % 10;
        int y = buttonIndex / 10;
        assertEquals(-1, testArray[x][y]);
    }

    @Test
    void testReneralGameWin() {
        Board testBoard = new Board();
        testBoard.boardInit(3, "General Game");
        testBoard.registerMove(0, 'S');
        testBoard.registerMove(1, 'O');
        testBoard.registerMove(2, 'S');
        testBoard.registerMove(3, 'S');
        testBoard.registerMove(4, 'O');
        testBoard.registerMove(5, 'S');
        testBoard.registerMove(6, 'S');
        testBoard.registerMove(7, 'O');
        testBoard.registerMove(8, 'S');
        assertEquals(1, testBoard.getGameState());
    }

    @Test
    void testGeneralGameTie() {
        Board testBoard = new Board();
        testBoard.boardInit(3, "General Game");
        testBoard.registerMove(0, 'S');
        testBoard.registerMove(1, 'S');
        testBoard.registerMove(2, 'S');
        testBoard.registerMove(3, 'S');
        testBoard.registerMove(4, 'S');
        testBoard.registerMove(5, 'S');
        testBoard.registerMove(6, 'S');
        testBoard.registerMove(7, 'S');
        testBoard.registerMove(8, 'S');
        assertEquals(-1, testBoard.getGameState());
    }

    @Test
    void testSimpleGameWin() {
        Board testBoard = new Board();
        testBoard.boardInit(5, "Simple Game");
        testBoard.setBluePlayerTurn(false);
        testBoard.registerMove(6, 'S');
        testBoard.registerMove(12, 'O');
        testBoard.registerMove(18, 'S');
        assertEquals(2, testBoard.getGameState());
    }

    @Test
    void testSimpleGameTie() {
        Board testBoard = new Board();
        testBoard.boardInit(3, "Simple Game");
        testBoard.registerMove(0, 'S');
        testBoard.registerMove(1, 'S');
        testBoard.registerMove(2, 'S');
        testBoard.registerMove(3, 'S');
        testBoard.registerMove(4, 'S');
        testBoard.registerMove(5, 'S');
        testBoard.registerMove(6, 'S');
        testBoard.registerMove(7, 'S');
        testBoard.registerMove(8, 'S');
    }

    @Test
    void testGet3x3BoardSize() {
        Board testBoard = new Board();
        testBoard.boardInit(3, "Simple Game");
        int boardSize = testBoard.getBoardSize();
        assertEquals(3, boardSize);
    }

    @Test
    void testGet10x10BoardSize() {
        Board testBoard = new Board();
        testBoard.boardInit(10, "General Game");
        int boardSize = testBoard.getBoardSize();
        assertEquals(10, boardSize);
    }

    @Test
    void testGetGameTypeSimple() {
        Board testBoard = new Board();
        testBoard.boardInit(3, "Simple Game");
        String gameType = testBoard.getGameType();
        assertEquals("Simple Game", gameType);
    }

    @Test
    void testGetGameTypeGeneral() {
        Board testBoard = new Board();
        testBoard.boardInit(10, "General Game");
        String gameType = testBoard.getGameType();
        assertEquals("General Game", gameType);
    }
}