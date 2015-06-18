package com.httpServer;

import com.httpServer.RequestAdapter.RequestReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {

    static final Logger serverLogger =
            Logger.getLogger(RequestReader.class.getName());

    public static void main(String[] args) throws IOException {
        try {
            FileHandler requestLogHandler = new FileHandler(System.getProperty("user.dir") + "/log/logs.txt");
            serverLogger.addHandler(requestLogHandler);
        } catch (IOException e) {
            serverLogger.log(Level.SEVERE, "Couldn't add handler to logger", e);
        }

        ServerSocket serverSocket = new ServerSocket(5000);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        serverLogger.log(Level.INFO, "Server starting... ");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            RunnableServer server = new RunnableServer(clientSocket);
            threadPool.execute(server);
        }
    }
}
