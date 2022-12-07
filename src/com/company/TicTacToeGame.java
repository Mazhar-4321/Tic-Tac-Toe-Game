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
        indexSelectedByPlayer = index;
    }

    public void tossToDecideFirstPlayer() {
        if (getRandomNumber(0, 9) == 1) {
            System.out.println("Computer is the Player 1");
            currentPlayer = COMPUTER;
        } else {
            System.out.println("User is the Player 1");
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min) % 2;
    }

    public void determineResultOfGame() {
        char playerOrComputerLetter;
        if (currentPlayer == USER) {
            playerOrComputerLetter = playerLetter;
        } else {
            playerOrComputerLetter = computerLetter;
        }
        int rowStartIndex = indexSelectedByPlayer >= 1 && indexSelectedByPlayer <= 3 ? 1 : (indexSelectedByPlayer >= 4 && indexSelectedByPlayer <= 6) ? 4 : 7;
        int rowEndIndex = rowStartIndex + 2;
        if (checkColumn(playerOrComputerLetter, indexSelectedByPlayer - 3, indexSelectedByPlayer + 3) ||
                checkRow(playerOrComputerLetter, rowStartIndex, rowEndIndex)) {
            System.out.println(currentPlayer == USER ? "Player Won" : "Computer Won");
            return;
        }
        if (indexSelectedByPlayer == 5 && (checkForwardDiagonal(playerOrComputerLetter, 1, 9) || checkReverseDiagonal(playerOrComputerLetter, 3, 7))) {
            System.out.println(currentPlayer == USER ? "Player Won" : "Computer Won");
            return;
        }
        if ((indexSelectedByPlayer == 3 || indexSelectedByPlayer == 7) && checkReverseDiagonal(playerOrComputerLetter, 3, 7)) {
            System.out.println(currentPlayer == USER ? "Player Won" : "Computer Won");
            return;
        }
        if ((indexSelectedByPlayer == 1 || indexSelectedByPlayer == 9) && (checkForwardDiagonal(playerOrComputerLetter, 1, 9))) {
            System.out.println(currentPlayer == USER ? "Player Won" : "Computer Won");
            return;
        }
        if (noOfSpacesOccupied == 9) {
            System.out.println("Game Drawn");
            return;
        }
        currentPlayer = currentPlayer == USER ? COMPUTER : USER;
    }

    private boolean checkColumn(char letter, int startIndex, int endIndex) {
        boolean result = true;
        while (startIndex <= endIndex) {
            if (board[startIndex] != letter) {
                return false;
            }
            startIndex += 3;
        }
        return result;
    }

    private boolean checkRow(char letter, int startIndex, int endIndex) {
        boolean result = true;
        while (startIndex <= endIndex) {
            if (board[startIndex] != letter) {
                return false;
            }
            startIndex += 1;
        }
        return result;
    }

    private boolean checkForwardDiagonal(char letter, int startIndex, int endIndex) {
        boolean result = true;
        while (startIndex <= endIndex) {
            if (board[startIndex] != letter) {
                return false;
            }
            startIndex += 4;
        }
        return result;
    }

    private boolean checkReverseDiagonal(char letter, int startIndex, int endIndex) {
        boolean result = true;
        while (startIndex <= endIndex) {
            if (board[startIndex] != letter) {
                return false;
            }
            startIndex += 2;
        }
        return result;
    }

    private boolean validateIndexForFreeSpace(int index) {
        return board[index] == ' ';
    }
}
