package com.httpServer.Handler.FileIO;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class MyFileWriterTest {

    private String readFileContents(String filePath) {
        try {
            Path path = Paths.get(filePath);
            byte[] fileContents = Files.readAllBytes(path);
            return new String(fileContents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error: Couldn't read file content";
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
    public void testDeleteFileContents() {
        String testFilePath = System.getProperty("user.dir") + "/test/com/httpServer/test_data/test_data.txt";
        assertEquals("This is a test\n", readFileContents(testFilePath));

        MyFileWriter.deleteFileContents("/test/com/httpServer/test_data/test_data.txt");
        assertEquals("\n", readFileContents(testFilePath));

        MyFileWriter.editFile("/test/com/httpServer/test_data/test_data.txt", "This is a test");
        assertEquals("This is a test\n", readFileContents(testFilePath));
    }

}