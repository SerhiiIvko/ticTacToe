package ticTacToe.menu;

import ticTacToe.gameLogic.WinnerChecker;
import ticTacToe.history.LogManager;
import ticTacToe.properties.ConsoleWriter;

import java.util.Scanner;

public class TicTacToeGame {
    private static final int SIZE = 3;
    private static int playerIndex = 2;
    private static boolean inGame = true;

    public static int getSIZE() {
        return SIZE;
    }

    public static void mainMenu() {
        boolean isRunning = true;
        while (isRunning && inGame) {
            ConsoleWriter.printOut(ConsoleWriter.MAIN_MENU);
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.nextLine();
            switch (userChoice) {
                case "1":
                    startGame();
                    break;
                case "2":
                    new LogManager().loadHistoryInMemory();
                    break;
                case "3":
                    ConsoleWriter.printOut(ConsoleWriter.QUIT_MESSAGE);
                    scanner.close();
                    isRunning = false;
                    inGame = false;
                    break;
                default:
                    ConsoleWriter.printOut(ConsoleWriter.INCORRECT_PARAMS_MESSAGE);
            }
        }
    }

    private static void startGame() {
        Scanner scanner = new Scanner(System.in);
        String[][] field = new String[SIZE][SIZE];
        String cellPattern = ConsoleWriter.EMPTY_CELL_PATTERN;
        int quitIndex = 0;
        createField(field, cellPattern);
        showField(field);
        ConsoleWriter.printOut(ConsoleWriter.PL1_WELCOME_MESSAGE);
        String player1 = scanner.nextLine();
        ConsoleWriter.printOut(ConsoleWriter.PL2_WELCOME_MESSAGE);
        String player2 = scanner.nextLine();
        while ((playerIndex < 11) && (quitIndex == 0)) {
            if (playerIndex % 2 == 0) {
                ConsoleWriter.printOut("\n" + player1 + ConsoleWriter.PUT_X);
            } else {
                ConsoleWriter.printOut("\n" + player2 + ConsoleWriter.PUT_O);
            }
            ConsoleWriter.printOut(ConsoleWriter.INPUT_CELL_NUMBER);
            switch (scanner.next()) {
                case "1": {
                    if (field[0][0].equalsIgnoreCase(cellPattern)) {
                        field[0][0] = addInputInArray(playerIndex);
                    } else {
                        ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT_CELL_NOT_EMPTY);
                    }
                    break;
                }
                case "2": {
                    if (field[0][1].equalsIgnoreCase(cellPattern)) {
                        field[0][1] = addInputInArray(playerIndex);
                    } else {
                        ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT_CELL_NOT_EMPTY);
                    }
                    break;
                }
                case "3": {
                    if (field[0][2].equalsIgnoreCase(cellPattern)) {
                        field[0][2] = addInputInArray(playerIndex);
                    } else {
                        ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT_CELL_NOT_EMPTY);
                    }
                    break;
                }
                case "4": {
                    if (field[1][0].equalsIgnoreCase(cellPattern)) {
                        field[1][0] = addInputInArray(playerIndex);
                    } else {
                        ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT_CELL_NOT_EMPTY);
                    }
                    break;
                }
                case "5": {
                    if (field[1][1].equalsIgnoreCase(cellPattern)) {
                        field[1][1] = addInputInArray(playerIndex);
                    } else {
                        ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT_CELL_NOT_EMPTY);
                    }
                    break;
                }
                case "6": {
                    if (field[1][2].equalsIgnoreCase(cellPattern)) {
                        field[1][2] = addInputInArray(playerIndex);
                    } else {
                        ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT_CELL_NOT_EMPTY);
                    }
                    break;
                }
                case "7": {
                    if (field[2][0].equalsIgnoreCase(cellPattern)) {
                        field[2][0] = addInputInArray(playerIndex);
                    } else {
                        ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT_CELL_NOT_EMPTY);
                    }
                    break;
                }
                case "8": {
                    if (field[2][1].equalsIgnoreCase(cellPattern)) {
                        field[2][1] = field[2][2] = addInputInArray(playerIndex);
                    } else {
                        ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT_CELL_NOT_EMPTY);
                    }
                    break;
                }
                case "9": {
                    if (field[2][2].equalsIgnoreCase(cellPattern)) {
                        field[2][2] = addInputInArray(playerIndex);
                    } else {
                        ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT_CELL_NOT_EMPTY);
                    }
                    break;
                }
                default:
                    ConsoleWriter.printOut(ConsoleWriter.ERROR_INPUT);
                    break;
            }
            showField(field);
            quitIndex = new WinnerChecker(field, quitIndex, player1, player2).checkWinner();
        }
        System.out.println();
        scanner.close();
    }

    private static String addInputInArray(int cellIndex) {
        String userInput = cellIndex % 2 == 0 ? ConsoleWriter.X_PATTERN : ConsoleWriter.O_PATTERN;
        TicTacToeGame.playerIndex++;
        return userInput;
    }

    private static void createField(String[][] field, String cellPattern) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = (cellPattern);
            }
        }
    }

    private static void showField(String[][] field) {
        ConsoleWriter.printOut(ConsoleWriter.CURRENT_FIELD_STATEMENT);
        for (int p = 0; p < SIZE; p++) {
            System.out.println("\n");
            for (int l = 0; l < SIZE; l++) {
                System.out.print(field[p][l]);
            }
        }
        ConsoleWriter.printOut("\n");
    }

    public static void exit() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            ConsoleWriter.printOut(ConsoleWriter.CONTINUE_QUESTION);
            input = scanner.nextLine();
            if (!exitCondition(input)) {
                ConsoleWriter.printOut("Something wrong. Try again!");
            }
            if (input.equalsIgnoreCase(ConsoleWriter.YES) || input.equalsIgnoreCase("Yes")) {
                ConsoleWriter.printOut("\n");
                TicTacToeGame.mainMenu();
            } else if (input.equalsIgnoreCase(ConsoleWriter.NO) || input.equalsIgnoreCase("No")) {
                ConsoleWriter.printOut(ConsoleWriter.GAME_OVER);
                scanner.close();
                inGame = false;
                break;
            }
        } while (exitCondition(input));
    }

    private static boolean exitCondition(String input) {
        return input.equalsIgnoreCase(ConsoleWriter.NO) || input.equalsIgnoreCase("No")
            || input.equalsIgnoreCase(ConsoleWriter.YES) || input.equalsIgnoreCase("Yes");
    }
}