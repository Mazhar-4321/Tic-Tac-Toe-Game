package com.company;

import java.util.Scanner;

public class TicTacToeGame {
    private final Scanner scanner = new Scanner(System.in);
    private final int USER = 0;
    private final int COMPUTER = 1;
    private char[] board;
    private char playerLetter;
    private char computerLetter;
    private int currentPlayer;
    private int indexSelectedByPlayer;
    private int noOfSpacesOccupied;

    public TicTacToeGame() {
        board = new char[10];
        currentPlayer = USER;
    }

    public void allowPlayerToGiveInput() {
        System.out.println("Please Input a Character X or O");
        while (true) {
            String inputString = scanner.next();
            if (inputString.length() > 1) {
                System.out.println("Please Enter Character X or O");
                continue;
            }
            if (inputString.equalsIgnoreCase("x") || inputString.equalsIgnoreCase("o")) {
                playerLetter = inputString.charAt(0);
                computerLetter = inputString.charAt(0) == 'o' ? 'x' : 'o';
                break;
            }
            System.out.println("Please Enter Character X or O");
        }


    }

    public void showBoard() {
        String lines = "___________";
        int currentBoardIndex = 1;
        for (int i = 1; i <= 7; i++) {
            if (i % 2 == 1) {
                System.out.println(lines);
                continue;
            }
            for (int j = 1; j <= 12; j++) {
                if (j % 4 == 1) {
                    System.out.print(board[currentBoardIndex++]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void allowUserToMakeMoves() {
        System.out.println("Please Select A Digit From 1 to 9");
        int index = scanner.nextInt();
        while (index < 1 || index > 9) {
            System.out.println("Invalid Index , Enter a Digit From 1 to 9");
            index = scanner.nextInt();
        }
        while (validateIndexForFreeSpace(index)) {
            System.out.println("Position" + index + " is Already Occupied, Please Enter a Different Number");
            index = scanner.nextInt();
        }
        noOfSpacesOccupied += 1;
        indexSelectedByPlayer = index;
    }

    private int getRandomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min) % 2;
    }

    private boolean validateIndexForFreeSpace(int index) {
        return board[index] == ' ';
    }

    public void tossToDecideFirstPlayer() {
        if (getRandomNumber(0, 9) == 1) {
            System.out.println("Computer is the Player 1");
            currentPlayer = COMPUTER;
        } else {
            System.out.println("User is the Player 1");
        }
    }

    public void determineResultOfGame() {
        char playerOrComputerLetter;
        if (currentPlayer == USER) {
            playerOrComputerLetter = playerLetter;
        } else {
            playerOrComputerLetter = computerLetter;
        }
        int rowStartAndEndIndexes[];
        rowStartAndEndIndexes = getRowStartAndEndIndex();
        int columnStartAndEndIndexes[];
        columnStartAndEndIndexes = getColumnStartAndEndIndex();
        if (checkForAllWinningSequences(playerOrComputerLetter, rowStartAndEndIndexes[0], rowStartAndEndIndexes[1], 1, 0) == 3
                || checkForAllWinningSequences(playerOrComputerLetter, 3, 7, 2, 0) == 3
                || checkForAllWinningSequences(playerOrComputerLetter, columnStartAndEndIndexes[0], columnStartAndEndIndexes[1], 3, 0) == 3
                || checkForAllWinningSequences(playerOrComputerLetter, 1, 9, 4, 0) == 3
        ) {
            System.out.println(currentPlayer == USER ? "Player Won" : "Computer Won");
            return;
        }
        if (noOfSpacesOccupied == 9) {
            System.out.println("Game Drawn");
            return;
        }
        currentPlayer = currentPlayer == USER ? COMPUTER : USER;
    }

    private void addIntelligenceToComputer() {
        checkIfComputerOrPlayerWins(computerLetter, 1);
    }

    private void blockOpponentWinningMoves() {
        checkIfComputerOrPlayerWins(playerLetter, -1);
    }

    private int checkIfComputerOrPlayerWins(char computerOrPlayerLetter, int intelligence) {
        return checkForAllWinningSequences(computerOrPlayerLetter, 1, 3, 1, intelligence) == 3 ? 3 :
                checkForAllWinningSequences(computerOrPlayerLetter, 4, 6, 1, intelligence) == 3 ? 3 :
                        checkForAllWinningSequences(computerOrPlayerLetter, 7, 9, 1, intelligence) == 3 ? 3 :
                                checkForAllWinningSequences(computerOrPlayerLetter, 1, 7, 3, intelligence) == 3 ? 3 :
                                        checkForAllWinningSequences(computerOrPlayerLetter, 2, 8, 3, intelligence) == 3 ? 3 :
                                                checkForAllWinningSequences(computerOrPlayerLetter, 3, 9, 3, intelligence) == 3 ? 3 :
                                                        checkForAllWinningSequences(computerOrPlayerLetter, 1, 9, 4, intelligence) == 3 ? 3 :
                                                                checkForAllWinningSequences(computerOrPlayerLetter, 3, 7, 2, intelligence) == 3 ? 3 : -1;
    }

    private boolean makeCornerMovesIfPossible() {
        if (board[0] != computerLetter || board[0] != playerLetter) {
            board[0] = computerLetter;
            return true;
        }
        if (board[2] != computerLetter || board[2] != playerLetter) {
            board[2] = computerLetter;
            return true;
        }
        if (board[6] != computerLetter || board[6] != playerLetter) {
            board[6] = computerLetter;
            return true;
        }
        if (board[8] != computerLetter || board[8] != playerLetter) {
            board[8] = computerLetter;
            return true;
        }
        return false;
    }

    private int[] getColumnStartAndEndIndex() {
        int[] columnIndexes = new int[2];
        columnIndexes[0] = indexSelectedByPlayer == 1 || indexSelectedByPlayer == 2 || indexSelectedByPlayer == 3 ? indexSelectedByPlayer :
                indexSelectedByPlayer == 4 || indexSelectedByPlayer == 5 || indexSelectedByPlayer == 6 ? indexSelectedByPlayer - 3 : indexSelectedByPlayer - 6;
        columnIndexes[1] = columnIndexes[0] + 6;
        return columnIndexes;
    }

    private int[] getRowStartAndEndIndex() {
        int[] rowIndexes = new int[2];
        rowIndexes[0] = indexSelectedByPlayer >= 1 && indexSelectedByPlayer <= 3 ? 1 : (indexSelectedByPlayer >= 4 && indexSelectedByPlayer <= 6) ? 4 : 7;
        rowIndexes[1] = rowIndexes[0] + 2;
        return rowIndexes;
    }

    private int checkForAllWinningSequences(char letter, int startIndex, int endIndex, int jumps, int intelligence) {
        int count = 0;
        int availableSpot = -1;
        while (startIndex <= endIndex) {
            if (board[startIndex] != letter) {
                startIndex += jumps;
                availableSpot = startIndex;
                continue;
            }
            count += 1;
            startIndex += jumps;
        }
        if (count == 2 && intelligence == 1) {
            board[availableSpot] = letter;
            return 3;
        }
        if (count == 2 && intelligence == -1) {
            board[availableSpot] = computerLetter;
            return 3;
        }
        return count;
    }


}
