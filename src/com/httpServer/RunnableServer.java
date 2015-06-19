package com.httpServer;

import com.httpServer.RequestAdapter.RequestMapper;
import com.httpServer.RequestAdapter.RequestReader;
import com.httpServer.ResponseAdapter.ResponseJoiner;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class RunnableServer implements Runnable {

    private final Socket clientSocket;
    private final Logger serverLogger;

    RunnableServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.serverLogger = Main.serverLogger;
    }

    @Override
    public void run() {
        OutputStream out = null;
        BufferedReader in = null;
        try {
            out = clientSocket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String request = RequestReader.read(in);
            serverLogger.log(Level.INFO, request);

            HashMap<String, String> requestMap = RequestMapper.map(request);
            HashMap<String, byte[]> responseMap = com.httpServer.Handler.Handler.getResponse(requestMap);

            byte[] response = ResponseJoiner.join(responseMap);

            out.write(response);
        } catch (IOException e) {
            serverLogger.log(Level.SEVERE, "Couldn't complete request/response cycle", e);
            e.printStackTrace();
        } finally {
            close(in);
            close(out);
        }
    }

    private void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            serverLogger.log(Level.SEVERE, "Couldn't close stream", e);
            e.printStackTrace();
        }
    }
}
