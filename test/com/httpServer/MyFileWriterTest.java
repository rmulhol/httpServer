package com.httpServer;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MyFileWriterTest {

    private String readFileContents(String filePath) {
        byte[] fileContents = null;
        try {
            Path path = Paths.get(filePath);
            fileContents = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(fileContents);
    }

    @Test
    public void testEditFile() {
        String testFilePath = System.getProperty("user.dir") + "/test/com/httpServer/test_data/test_data.txt";
        assertEquals("This is a test\n", readFileContents(testFilePath));

        MyFileWriter.editFile("/test/com/httpServer/test_data/test_data.txt", "This is new test data");
        assertEquals("This is new test data\n", readFileContents(testFilePath));

        MyFileWriter.editFile("/test/com/httpServer/test_data/test_data.txt", "This is a test");
        assertEquals("This is a test\n", readFileContents(testFilePath));
    }

    @Test
    public void testAppendToFile() {
        String testFilePath = System.getProperty("user.dir") + "/test/com/httpServer/test_data/test_data.txt";
        assertEquals("This is a test\n", readFileContents(testFilePath));

        MyFileWriter.appendToFile("/test/com/httpServer/test_data/test_data.txt", "This is more test data");
        assertEquals("This is a test\nThis is more test data\n", readFileContents(testFilePath));

        MyFileWriter.editFile("/test/com/httpServer/test_data/test_data.txt", "This is a test");
        assertEquals("This is a test\n", readFileContents(testFilePath));
    }

    @Test
    public void testDeleteFileContents() {
        String testFilePath = System.getProperty("user.dir") + "/test/com/httpServer/test_data/test_data.txt";
        assertEquals("This is a test\n", readFileContents(testFilePath));

        MyFileWriter.deleteFileContents("/test/com/httpServer/test_data/test_data.txt");
        assertEquals("\n", readFileContents(testFilePath));

        MyFileWriter.editFile("/test/com/httpServer/test_data/test_data.txt", "This is a test");
        assertEquals("This is a test\n", readFileContents(testFilePath));
    }

}