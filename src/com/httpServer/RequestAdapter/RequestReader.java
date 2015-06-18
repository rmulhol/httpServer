package com.httpServer.RequestAdapter;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {

    public static String read(BufferedReader in) {
        try {
            String request = "";
            request += in.readLine() + "\r\n";
            while (in.ready()) {
                request += (char) in.read();
            }
            return request;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error: Couldn't read request";
    }

}
