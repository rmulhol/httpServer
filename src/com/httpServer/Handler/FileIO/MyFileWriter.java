package com.httpServer.Handler.FileIO;

import java.io.*;

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
