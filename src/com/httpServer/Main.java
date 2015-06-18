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

class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server starting... ");
        while (true) {
            Socket clientSocket = serverSocket.accept();

            OutputStream out = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String request = RequestReader.read(in);

            System.out.println(request);

            HashMap<String, String> requestMap = RequestMapper.map(request);
            HashMap<String, byte[]> responseMap = com.httpServer.Handler.Handler.getResponse(requestMap);

            byte[] response = ResponseJoiner.join(responseMap);

            out.write(response);
            out.close();
        }
    }
}
