package com.company;

import java.util.Scanner;

public class TicTacToeGame {
    private final Scanner scanner = new Scanner(System.in);
    private char[] board;
    private char playerLetter;
    private char computerLetter;

    public TicTacToeGame() {
        board = new char[10];
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
}
