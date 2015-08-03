package com.httpServer.RequestAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestReader {

    private static final Logger requestLogger =
            Logger.getLogger(RequestReader.class.getName());

    public static String read(BufferedReader in) {
        String request = "";
        try {
            request += in.readLine() + "\r\n";
            while (in.ready()) {
                request += (char) in.read();
            }
            return request;
        } catch (IOException e) {
            requestLogger.log(Level.SEVERE, "Error reading request", e);
            e.printStackTrace();
            return "Error reading request";
        }
    }
}
