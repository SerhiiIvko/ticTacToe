package ticTacToe.gameLogic;

import ticTacToe.menu.TicTacToeGame;
import ticTacToe.history.LogManager;
import ticTacToe.properties.ConsoleWriter;

import java.util.Date;

public class WinnerChecker {
    private final String[][] field;
    private int quit;
    private final String player;
    private final String player2;
    private final LogManager logManager = new LogManager();
    private String resultField = "";

    public WinnerChecker(String[][] field, int quit, String player, String player2) {
        this.field = field;
        this.quit = quit;
        this.player = player;
        this.player2 = player2;
    }

    public int checkWinner() {
        String winner = resultField.concat(field[0][0] + field[0][1] + field[0][2] + "\n"
                + field[1][0] + field[1][1] + field[1][2] + "\n" + field[2][0] + field[2][1] + field[2][2])
                .concat("\nWinner: " + player + "\n" + new Date().toString() + "\n");
        for (int t = 0; t < TicTacToeGame.getSIZE(); t++) {
            if (isWinCombination1(field, ConsoleWriter.O_PATTERN)) {
                doGameCycle(winner);
                break;
            } else if (isWinCombination1(field, ConsoleWriter.X_PATTERN)) {
                doGameCycle(winner);
                break;
            }
            if (isWinCombination2(field, ConsoleWriter.X_PATTERN)) {
                doGameCycle(winner);
                break;
            } else if (isWinCombination2(field, ConsoleWriter.O_PATTERN)) {
                doGameCycle(winner);
                break;
            }
        }
        return quit;
    }

    private String showWinner(String player) {
        return "\nWin " + player;
    }

    private boolean isWinCombination1(String[][] field, String ticOrTac) {
        boolean isWin = false;
        for (int t = 0; t < TicTacToeGame.getSIZE(); t++) {
            if (((field[t][0]).equalsIgnoreCase(ticOrTac)
                    & (field[t][1]).equalsIgnoreCase(ticOrTac)
                    & field[t][2].equalsIgnoreCase(ticOrTac)) ||
                    ((field[0][t]).equalsIgnoreCase(ticOrTac)
                            & (field[1][t]).equalsIgnoreCase(ticOrTac)
                            & field[2][t].equalsIgnoreCase(ticOrTac))) {
                isWin = true;
                break;
            }
        }
        return isWin;
    }

    private boolean isWinCombination2(String[][] field, String ticOrTac) {
        boolean isWin = false;
        for (int t = 0; t < TicTacToeGame.getSIZE(); t++) {
            if (((field[0][0]).equalsIgnoreCase(ticOrTac)
                    & (field[1][1]).equalsIgnoreCase(ticOrTac)
                    & field[2][2].equalsIgnoreCase(ticOrTac)) ||
                    ((field[0][2]).equalsIgnoreCase(ticOrTac)
                            & (field[1][1]).equalsIgnoreCase(ticOrTac)
                            & field[2][0].equalsIgnoreCase(ticOrTac))) {
                isWin = true;
                break;
            }
        }
        return isWin;
    }

    private void doGameCycle(String winner) {
        resultField = winner;
        logManager.saveHistory(resultField);
        quit++;
        System.out.println(showWinner(player));
        TicTacToeGame.exit();
    }
}