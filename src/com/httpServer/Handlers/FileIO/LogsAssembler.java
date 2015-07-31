package com.httpServer.Handlers.FileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogsAssembler {

    public static byte[] readLogs()  {
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "/log/logs.txt");
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: File Could Not Be Read.".getBytes();
        }
    }
}
