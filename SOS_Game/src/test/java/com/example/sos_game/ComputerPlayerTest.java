package com.example.sos_game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    @Test
    void testFirstMove() {
        ComputerPlayer testComputerPlayer = new ComputerPlayer();
        int testInt = testComputerPlayer.firstMove(3);
        testInt = testInt * -1;
        assertTrue(testInt < 9);
    }

    @Test
    void testPickCell() {
        ComputerPlayer testComputerPlayer = new ComputerPlayer();
        int testButtonIndex = 4;
        int testBoardSize = 3;
        int[][] testBoardState = new int[testBoardSize][testBoardSize];
        testBoardState[0][0] = 0;
        testBoardState[0][1] = 0;
        testBoardState[0][2] = 0;
        testBoardState[1][0] = 0;
        testBoardState[1][1] = -1;
        testBoardState[1][2] = 0;
        testBoardState[2][0] = 0;
        testBoardState[2][1] = 0;
        testBoardState[2][2] = 0;
        int testMoveIndex = testComputerPlayer.pickCell(testButtonIndex, testBoardState, testBoardSize);
        assertEquals(-1, testMoveIndex);
    }

    @Test
    void testPointToIndex() {
        ComputerPlayer testComputerPlayer = new ComputerPlayer();
        int testIndex = testComputerPlayer.pointToIndex(1, 1, 3);
        testIndex = testIndex -1;
        assertEquals(4, testIndex);
    }

}