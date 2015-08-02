package com.httpServer.Handlers.FileIO;

import com.httpServer.Config.ServerConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFileReader {

    public static File[] readDirectoryContents() {
        File publicFolder = new File(System.getProperty("user.dir") + ServerConfig.getDirectory());
        return publicFolder.listFiles();
    }

    public static byte[] readFileContents(String filePath)  {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + ServerConfig.getDirectory() + filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: File Could Not Be Read.".getBytes();
        }
    }
}
