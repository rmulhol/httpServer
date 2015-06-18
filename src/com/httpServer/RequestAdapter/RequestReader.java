package com.httpServer.RequestAdapter;

import com.httpServer.Handler.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestReader {

    private static final Logger requestLogger =
            Logger.getLogger(RequestReader.class.getName());

    public static String read(BufferedReader in) {
        try {
            String request = "";
            request += in.readLine() + "\r\n";
            while (in.ready()) {
                request += (char) in.read();
            }
            requestLogger.log(Level.INFO, request);
            return request;
        } catch (IOException e) {
            requestLogger.log(Level.SEVERE, "Error reading request", e);
            e.printStackTrace();
        }
        return "Error: Couldn't read request";
    }

}
