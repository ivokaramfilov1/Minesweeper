package com.company;

import java.util.Random;
import java.util.Scanner;

public class Helper {

    static int walls;
    static int mines;
    static int x;
    static int y;

    static boolean inBounds(int row, int col) {
        return (row >= 0) && (row < walls) && (col >= 0) && (col < walls);
    }

    static boolean isMine(int row, int col, char[][] board) {
        return board[row][col] == '*';
    }

    static void openCell() {
        System.out.println("Enter your move, (row, column) -> ");
        Scanner s = new Scanner(System.in);
        x = s.nextInt();
        y = s.nextInt();
    }

    static void printBoard(char[][] arr) {
        System.out.print(" ");
        for (int i = 0; i < walls; i++) {
            System.out.printf("  %d", i);
        }
        System.out.println("\n");

        for (int i = 0; i < walls; i++) {
            System.out.printf("%d  ", i);
            for (int j = 0; j < walls; j++) {
                System.out.printf("%c  ", arr[i][j]);
            }
            System.out.println("\n");
        }
    }

    static int minesCounter(int row, int col, char[][] board) {
        int counter = 0;
        if (inBounds(row - 1, col)) {
            if (isMine(row - 1, col, board)) {
                counter++;
            }
        }
        if (inBounds(row + 1, col)) {
            if (isMine(row + 1, col, board)) {
                counter++;
            }
        }
        if (inBounds(row, col + 1)) {
            if (isMine(row, col + 1, board)) {
                counter++;
            }
        }
        if (inBounds(row, col - 1)) {
            if (isMine(row, col - 1, board)) {
                counter++;
            }
        }
        if (inBounds(row - 1, col + 1)) {
            if (isMine(row - 1, col + 1, board)) {
                counter++;
            }
        }
        if (inBounds(row - 1, col - 1)) {
            if (isMine(row - 1, col - 1, board)) {
                counter++;
            }
        }
        if (inBounds(row + 1, col + 1)) {
            if (isMine(row + 1, col + 1, board)) {
                counter++;
            }
        }
        if (inBounds(row + 1, col - 1)) {
            if (isMine(row + 1, col - 1, board)) {
                counter++;
            }
        }
        return counter;
    }

    static boolean play(char[][] board, char[][] realBoard,
                        int[][] min, int row, int col) {
        if (board[row][col] != '-') {
            return false;
        }
        if (realBoard[row][col] == '*') {
            board[row][col] = '*';
            for (int i = 0; i < mines; i++) {
                board[min[i][0]][min[i][1]] = '*';
            }
            printBoard(board);
            System.out.println("You lost!");
            return true;
        } else {
            int count = minesCounter(row, col, realBoard);
            board[row][col] = (char) (count + '0');
            if (count != 0) {
                if (inBounds(row - 1, col)) {
                    if (!isMine(row - 1, col, realBoard))
                        return play(board, realBoard, min, row - 1, col);
                }
                if (inBounds(row + 1, col)) {
                    if (!isMine(row + 1, col, realBoard))
                        return play(board, realBoard, min, row + 1, col);
                }
                if (inBounds(row, col - 1)) {
                    if (!isMine(row, col - 1, realBoard))
                        return play(board, realBoard, min, row, col - 1);
                }
                if (inBounds(row, col + 1)) {
                    if (!isMine(row, col + 1, realBoard))
                        return play(board, realBoard, min, row, col + 1);
                }
                if (inBounds(row - 1, col - 1)) {
                    if (!isMine(row - 1, col - 1, realBoard))
                        return play(board, realBoard, min, row - 1, col - 1);
                }
                if (inBounds(row - 1, col + 1)) {
                    if (!isMine(row - 1, col + 1, realBoard))
                        return play(board, realBoard, min, row - 1, col + 1);
                }
                if (inBounds(row + 1, col - 1)) {
                    if (!isMine(row + 1, col - 1, realBoard))
                        return play(board, realBoard, min, row + 1, col - 1);
                }
                if (inBounds(row + 1, col + 1)) {
                    if (!isMine(row + 1, col + 1, realBoard))
                        return play(board, realBoard, min, row + 1, col + 1);
                }
            }
            return false;
        }
    }

    static void putMines(int[][] min, char[][] realBoard) {
        boolean[] mine = new boolean[mines];
        Random random1 = new Random();
        for (int i = 0; i < mines; i++) {

            int random = Math.abs(random1.nextInt() % walls);
            int randomSec = Math.abs(random1.nextInt() % walls);
            if (!mine[random]) {
                min[i][0] = random;
                min[i][1] = randomSec;
                realBoard[random][randomSec] = '*';
                mine[random] = true;
            }
        }
    }

    static void drawField(char[][] realBoard, char[][] board) {
        for (int i = 0; i < walls; i++) {
            for (int j = 0; j < walls; j++) {
                board[i][j] = realBoard[i][j] = '-';
            }
        }
    }
}
