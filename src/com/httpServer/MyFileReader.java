package com.httpServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class MyFileReader {

    public static File[] readDirectoryContents() {
        File publicFolder = new File(System.getProperty("user.dir") + "/public");
        return publicFolder.listFiles();
    }

    public static byte[] readFileContents(String filePath)  {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "/public" + filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: File Could Not Be Read.".getBytes();
        }
    }
}
