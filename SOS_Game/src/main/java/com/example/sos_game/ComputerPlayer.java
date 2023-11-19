package com.example.sos_game;

import java.util.Random;

public class ComputerPlayer {

    int playerVal;

    public void computerPlayerInit(int playerVal) {
        this.playerVal = playerVal;
    }

    public int firstMove(int boardSize) {
        Random random = new Random();
        int moveIndex = random.nextInt(boardSize * boardSize);
        int letterVal = random.nextInt(2);
        if (letterVal == 0) {
            return moveIndex * -1;
        }
        else {
            return moveIndex;
        }
    }

    public int pickCell(int buttonIndex, int[][] boardState, int boardSize) {
        int x = buttonIndex % boardSize;
        int y = buttonIndex / boardSize;
        boolean spaceRight = false;
        boolean spaceLeft = false;
        boolean spaceAbove = false;
        boolean spaceBelow = false;
        boolean spaceFarRight = false;
        boolean spaceFarLeft = false;
        boolean spaceFarAbove = false;
        boolean spaceFarBelow = false;
        if (x + 1 < boardSize) {
            spaceRight = true;
            if (x + 2 < boardSize) {
                spaceFarRight = true;
            }
        }
        if (x - 1 >= 0) {
            spaceLeft = true;
            if (x - 2 >= 0) {
                spaceFarLeft = true;
            }
        }
        if (y + 1 < boardSize) {
            spaceBelow = true;
            if (y + 2 < boardSize) {
                spaceFarBelow = true;
            }
        }
        if (y - 1 >= 0) {
            spaceAbove = true;
            if (y - 2 >= 0) {
                spaceFarAbove = true;
            }
        }

        if (boardState[x][y] == 1) {

            if (spaceFarRight) {
                if (boardState[x + 1][y] == -1) {
                    if (sSafe(x + 2, y, boardState, boardSize)) {
                        return pointToIndex(x + 2, y, boardSize);
                    }
                }
                else if (boardState[x + 1][y] == 0 && boardState[x + 2][y] == 1) {
                    if (oSafe(x + 1, y, boardState, boardSize)) {
                        return pointToIndex(x + 1, y, boardSize) * -1;
                    }
                }
            }

            if (spaceFarLeft) {
                if (boardState[x - 1][y] == -1) {
                    if (sSafe(x - 2, y, boardState, boardSize)) {
                        return pointToIndex(x - 2, y, boardSize);
                    }
                }
                if (boardState[x - 1][y] == 0 && boardState[x - 2][y] == 1) {
                    if (oSafe(x - 1, y, boardState, boardSize)) {
                        return pointToIndex(x - 1, y, boardSize) * -1;
                    }
                }
            }

            if (spaceFarBelow) {
                if (boardState[x][y + 1] == -1) {
                    if (sSafe(x, y + 2, boardState, boardSize)) {
                        return pointToIndex(x, y + 2, boardSize);
                    }
                }
                if (boardState[x][y + 1] == 0 && boardState[x][y + 2] == 1) {
                    if (oSafe(x, y + 1, boardState, boardSize)) {
                        return pointToIndex(x, y + 1, boardSize) * -1;
                    }
                }
            }

            if (spaceFarAbove) {
                if (boardState[x][y - 1] == -1) {
                    if (sSafe(x, y - 2, boardState, boardSize)) {
                        return pointToIndex(x, y - 2, boardSize);
                    }
                }
                if (boardState[x][y - 1] == 0 && boardState[x][y - 2] == 1) {
                    if (oSafe(x, y - 1, boardState, boardSize)) {
                        return pointToIndex(x, y - 1, boardSize) * -1;
                    }
                }
            }

            if (spaceFarRight && spaceFarBelow) {
                if (boardState[x + 1][y + 1] == -1) {
                    if (sSafe(x + 2, y + 2, boardState, boardSize)) {
                        return pointToIndex(x + 2, y + 2, boardSize);
                    }
                }
                if (boardState[x + 1][y + 1] == 0 && boardState[x + 2][y + 2] == 1) {
                    if (oSafe(x + 1, y + 1, boardState, boardSize)) {
                        return pointToIndex(x + 1, y + 1, boardSize) * -1;
                    }
                }
            }

            if (spaceFarRight && spaceFarAbove) {
                if (boardState[x + 1][y - 1] == -1) {
                    if (sSafe(x + 2, y - 2, boardState, boardSize)) {
                        return pointToIndex(x + 2, y - 2, boardSize);
                    }
                }
                if (boardState[x + 1][y - 1] == 0 && boardState[x + 2][y - 2] == 1) {
                    if (oSafe(x + 1, y - 1, boardState, boardSize)) {
                        return pointToIndex(x + 1, y - 1, boardSize) * -1;
                    }
                }
            }

            if (spaceFarLeft && spaceFarBelow) {
                if (boardState[x - 1][y + 1] == -1) {
                    if (sSafe(x - 2, y + 2, boardState, boardSize)) {
                        return pointToIndex(x - 2, y + 2, boardSize);
                    }
                }
                if (boardState[x - 1][y + 1] == 0 && boardState[x - 2][y + 2] == 1) {
                    if (oSafe(x - 1, y + 1, boardState, boardSize)) {
                        return pointToIndex(x - 1, y + 1, boardSize) * -1;
                    }
                }
            }

            if (spaceFarLeft && spaceFarAbove) {
                if (boardState[x - 1][y - 1] == -1) {
                    if (sSafe(x - 2, y - 2, boardState, boardSize)) {
                        return pointToIndex(x - 2, y - 2, boardSize);
                    }
                }
                if (boardState[x - 1][y - 1] == 0 && boardState[x - 2][y - 2] == 1) {
                    if (oSafe(x - 1, y - 1, boardState, boardSize)) {
                        return pointToIndex(x - 1, y - 1, boardSize) * -1;
                    }
                }
            }
        }

        else if (boardState[x][y] == -1) {
            if (spaceLeft && spaceRight && spaceAbove && spaceBelow) {
                if (boardState[x - 1][y - 1] == 1) {
                    if (sSafe(x + 1, y + 1, boardState, boardSize)) {
                        return pointToIndex(x + 1, y + 1, boardSize);
                    }
                }
                if (boardState[x + 1][y + 1] == 1) {
                    if (sSafe(x - 1, y - 1, boardState, boardSize)) {
                        return pointToIndex(x - 1, y - 1, boardSize);
                    }
                }
                if (boardState[x + 1][y - 1] == 1) {
                    if (sSafe(x - 1, y + 1, boardState, boardSize)) {
                        return pointToIndex(x - 1, y + 1, boardSize);
                    }
                }
                if (boardState[x - 1][y + 1] == 1) {
                    if (sSafe(x + 1, y - 1, boardState, boardSize)) {
                        return pointToIndex(x + 1, y - 1, boardSize);
                    }
                }
            }
            if (spaceLeft && spaceRight) {
                if (boardState[x - 1][y] == 1) {
                    if (sSafe(x + 1, y, boardState, boardSize)) {
                        return pointToIndex(x + 1, y, boardSize);
                    }
                }
                if (boardState[x + 1][y] == 1) {
                    if (sSafe(x - 1, y, boardState, boardSize)) {
                        return pointToIndex(x - 1, y, boardSize);
                    }
                }
            }
            if (spaceAbove && spaceBelow) {
                if (boardState[x][y - 1] == 1) {
                    if (sSafe(x, y + 1, boardState, boardSize)) {
                        return pointToIndex(x, y + 1, boardSize);
                    }
                }
                if (boardState[x][y + 1] == 1) {
                    if (sSafe(x, y - 1, boardState, boardSize)) {
                        return pointToIndex(x, y - 1, boardSize);
                    }
                }
            }
        }

        //go through the board state and check every cell for ssafe or osafe move
        //if no safe move found, go through board state and check for empty cell to return
        for (int run = 0; run <= 1; run++){
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (boardState[i][j] == 0) {
                        if (sSafe(i, j, boardState, boardSize) || run == 1) {
                            return pointToIndex(i, j, boardSize);
                        }
                        if (oSafe(i, j, boardState, boardSize)) {
                            return pointToIndex(i, j, boardSize) * -1;
                        }
                    }
                }
            }
        }

        return -1;
    }

    public boolean sSafe(int x, int y, int[][] boardState, int boardSize) {

        if (boardState[x][y] != 0) {
            return false;
        }

        boolean spaceFarRight = false;
        boolean spaceFarLeft = false;
        boolean spaceFarAbove = false;
        boolean spaceFarBelow = false;

        if (x + 2 < boardSize) {
            spaceFarRight = true;
        }
        if (x - 2 >= 0) {
            spaceFarLeft = true;
        }
        if (y + 2 < boardSize) {
            spaceFarBelow = true;
        }
        if (y - 2 >= 0) {
            spaceFarAbove = true;
        }

        if (spaceFarRight) {
            if (boardState[x + 1][y] == -1 && boardState[x + 2][y] == 0 || boardState[x + 1][y] == 0 && boardState[x + 2][y] == 1) {
                return false;
            }
        }
        if (spaceFarLeft) {
            if (boardState[x - 1][y] == -1 && boardState[x - 2][y] == 0 || boardState[x - 1][y] == 0 && boardState[x - 2][y] == 1) {
                return false;
            }
        }
        if (spaceFarBelow) {
            if (boardState[x][y + 1] == -1 && boardState[x][y + 2] == 0 || boardState[x][y + 1] == 0 && boardState[x][y + 2] == 1) {
                return false;
            }
        }
        if (spaceFarAbove) {
            if (boardState[x][y - 1] == -1 && boardState[x][y - 2] == 0 || boardState[x][y - 1] == 0 && boardState[x][y - 2] == 1) {
                return false;
            }
        }
        if (spaceFarRight && spaceFarBelow) {
            if (boardState[x + 1][y + 1] == -1 && boardState[x + 2][y + 2] == 0 || boardState[x + 1][y + 1] == 0 && boardState[x + 2][y + 2] == 1) {
                return false;
            }
        }
        if (spaceFarLeft && spaceFarBelow) {
            if (boardState[x - 1][y + 1] == -1 && boardState[x - 2][y + 2] == 0 || boardState[x - 1][y + 1] == 0 && boardState[x - 2][y + 2] == 1) {
                return false;
            }
        }
        if (spaceFarRight && spaceFarAbove) {
            if (boardState[x + 1][y - 1] == -1 && boardState[x + 2][y - 2] == 0 || boardState[x + 1][y - 1] == 0 && boardState[x + 2][y - 2] == 1) {
                return false;
            }
        }
        if (spaceFarLeft && spaceFarAbove) {
            if (boardState[x - 1][y - 1] == -1 && boardState[x - 2][y - 2] == 0 || boardState[x - 1][y - 1] == 0 && boardState[x - 2][y - 2] == 1) {
                return false;
            }
        }

        return true;
    }

    public boolean oSafe(int x, int y, int[][] boardState, int boardSize) {

        if (boardState[x][y] != 0) {
            return false;
        }

        boolean spaceRight = false;
        boolean spaceLeft = false;
        boolean spaceAbove = false;
        boolean spaceBelow = false;

        if (x + 1 < boardSize) {
            spaceRight = true;
        }
        if (x - 1 >= 0) {
            spaceLeft = true;
        }
        if (y + 1 < boardSize) {
            spaceBelow = true;
        }
        if (y - 1 >= 0) {
            spaceAbove = true;
        }

        if (spaceRight && spaceLeft && spaceBelow && spaceAbove) {
            if (boardState[x + 1][y + 1] == 1 && boardState[x - 1][y - 1] == 0 || boardState[x + 1][y + 1] == 0 && boardState[x - 1][y - 1] == 1) {
                return false;
            }
            if (boardState[x + 1][y - 1] == 1 && boardState[x - 1][y + 1] == 0 || boardState[x + 1][y - 1] == 0 && boardState[x - 1][y + 1] == 1) {
                return false;
            }
        }
        if (spaceRight && spaceLeft) {
            if (boardState[x + 1][y] == 1 && boardState[x - 1][y] == 0 || boardState[x + 1][y] == 0 && boardState[x - 1][y] == 1) {
                return false;
            }
        }
        if (spaceBelow && spaceAbove) {
            if (boardState[x][y + 1] == 1 && boardState[x][y - 1] == 0 || boardState[x][y + 1] == 0 && boardState[x][y - 1] == 1) {
                return false;
            }
        }

        return true;
    }

    public int pointToIndex(int x, int y, int boardSize) {
        return (y * boardSize) + x + 1;
    }

    public int getPlayerVal() {
        return playerVal;
    }
}
