package ticTacToe.history;

import ticTacToe.properties.AppProperties;
import ticTacToe.properties.ConsoleWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogManager {

    public void loadHistoryInMemory() {
        LogManager logManager = new LogManager();
        String gameResult = logManager.showHistory();
        if (gameResult != null && !gameResult.trim().isEmpty()) {
            ConsoleWriter.printOut(logManager.showHistory());
        } else {
            ConsoleWriter.printOut(ConsoleWriter.PLAY_FIRST_GAME);
        }
    }

    public void saveHistory(String resultField) {
        try {
            File file = new File(AppProperties.HISTORY_FILE_PATH);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(resultField);
            fileWriter.close();
        } catch (IOException e) {
            ConsoleWriter.printOut(e.getMessage());
        }
    }

    private String showHistory() {
        String gameLog = null;
        try {
            gameLog = new String(Files.readAllBytes(Paths.get(AppProperties.HISTORY_FILE_PATH)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            ConsoleWriter.printOut(e.getMessage());
        }
        return gameLog;
    }
}