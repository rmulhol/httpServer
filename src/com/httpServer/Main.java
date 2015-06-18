package com.httpServer;

import com.httpServer.RequestAdapter.RequestMapper;
import com.httpServer.RequestAdapter.RequestReader;
import com.httpServer.ResponseAdapter.ResponseJoiner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {

    private static final Logger serverLogger =
            Logger.getLogger(RequestReader.class.getName());



    public static void main(String[] args) throws IOException {
        try {
            FileHandler requestLogHandler = new FileHandler(System.getProperty("user.dir") + "/log/logs.txt");
            serverLogger.addHandler(requestLogHandler);
        } catch (IOException e) {
            serverLogger.log(Level.SEVERE, "Couldn't add handler to logger", e);
        }

        ServerSocket serverSocket = new ServerSocket(5000);

        serverLogger.log(Level.INFO, "Server starting... ");

        while (true) {
            Socket clientSocket = serverSocket.accept();

            OutputStream out = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String request = RequestReader.read(in);
            serverLogger.log(Level.INFO, request);

            HashMap<String, String> requestMap = RequestMapper.map(request);
            HashMap<String, byte[]> responseMap = com.httpServer.Handler.Handler.getResponse(requestMap);

            byte[] response = ResponseJoiner.join(responseMap);

            out.write(response);
            out.close();
        }
    }
}
