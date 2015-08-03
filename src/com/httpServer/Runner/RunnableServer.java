package com.httpServer.Runner;

import com.httpServer.Handlers.Handler;
import com.httpServer.Main;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.RequestAdapter.RequestBuilder;
import com.httpServer.RequestAdapter.RequestReader;
import com.httpServer.ResponseAdapter.Response;
import com.httpServer.ResponseAdapter.ResponseJoiner;
import com.httpServer.Router.RouteRegistrar;

import java.io.*;
import java.net.Socket;
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

            String rawRequest = RequestReader.read(in);
            serverLogger.log(Level.INFO, rawRequest);

            Request request = RequestBuilder.buildRequest(rawRequest);
            Handler handler = RouteRegistrar.getRoute(request);
            Response response = handler.respondToRequest(request);

            byte[] output = ResponseJoiner.join(response);

            out.write(output);
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
