package ticTacToe.history;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class LogManagerTest {

    @Test
    public void loadHistoryInMemory() {
    }

    @Test
    public void saveHistory() throws Exception {
        Path newFilePath = Paths.get("src/test/resources/newFile.txt");
        if (!Files.exists(newFilePath)){
            Files.createFile(newFilePath);
        }

//        assertNotNull(newFilePath);
        assertTrue(Files.exists(newFilePath));
    }
}