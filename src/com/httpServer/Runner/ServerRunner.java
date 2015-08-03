package com.httpServer.Runner;

import com.httpServer.Config.ServerConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class ServerRunner {

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(ServerConfig.getPort());
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        com.httpServer.Main.serverLogger.log(Level.INFO, "Server starting on port " + ServerConfig.getPort());

        while (true) {
            Socket clientSocket = serverSocket.accept();
            RunnableServer server = new RunnableServer(clientSocket);
            threadPool.execute(server);
        }
    }

}
