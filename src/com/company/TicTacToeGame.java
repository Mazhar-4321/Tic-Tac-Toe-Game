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

    private boolean validateIndexForFreeSpace(int index) {
        return board[index] == ' ';
    }
}
