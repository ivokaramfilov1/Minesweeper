package com.company;

import java.util.Scanner;

import static com.company.Helper.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Difficulty Level");
        System.out.println("Press 0 for BEGINNER (9 * 9 Cells and 10 Mines)\n");
        System.out.println("Press 1 for INTERMEDIATE (16 * 16 Cells and 40 Mines)\n");
        System.out.println("Press 2 for ADVANCED (24 * 24 Cells and 99 Mines)\n");
        int level = Integer.parseInt(sc.nextLine());
        if (level == 0) {
            walls = 9;
            mines = 10;
        }if (level == 1) {
            walls = 16;
            mines = 40;
        }if (level == 2) {
            walls = 24;
            mines = 99;
        }

        boolean gameOver = false;
        char[][] realBoard = new char[walls][walls];
        char[][] board = new char[walls][walls];

        int[][] mine = new int[mines][2];
        drawField(realBoard, board);
        putMines(mine, realBoard);
        int moves = 0;
        while (!gameOver) {
            System.out.println("Current Status of Board:\n");
            printBoard(board);
            openCell();
            if (moves == 0) {
                if (isMine(x, y, realBoard)) {
                    realBoard[x][y] = '-';
                }
            }
            moves++;
            gameOver = play(board, realBoard, mine, x, y);
            if (!gameOver && moves == (walls * walls - mines)) {
                System.out.println("You won! \n");
                gameOver = true;
            }
        }
    }
}