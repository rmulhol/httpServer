package com.httpServer.Handler;

import com.httpServer.Handler.SubHandlers.BodyHandler;
import com.httpServer.Handler.SubHandlers.FileWritingHandler;
import com.httpServer.Handler.SubHandlers.HeaderHandler;
import com.httpServer.Handler.SubHandlers.StatusHandler;

import java.util.HashMap;

class Handler {

    public static HashMap<String, byte[]> getResponse(HashMap<String, String> request) {
        HashMap<String, byte[]> response = new HashMap<String, byte[]>();

        new StatusHandler(request, response).setStatus();
        new HeaderHandler(request, response).setHeader();
        new BodyHandler(request, response).setBody();
        new FileWritingHandler(request).setFile();

        return response;
    }

}