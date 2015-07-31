package com.httpServer.Handlers.FileIO;

import java.io.IOException;
import java.io.PrintWriter;

public class MyFileWriter {

    public static void editFile(String filePath, String content) {
        try {
            PrintWriter editWriter = new PrintWriter(System.getProperty("user.dir") + filePath, "UTF-8");
            editWriter.println(content);
            editWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFileContents(String filePath) {
        try {
            PrintWriter patchWriter = new PrintWriter(System.getProperty("user.dir") + filePath, "UTF-8");
            patchWriter.println("");
            patchWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
