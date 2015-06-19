package com.httpServer.Handler;

import com.httpServer.Handler.Route.Route;
import com.httpServer.Handler.SubHandlers.BodyHandler;
import com.httpServer.Handler.SubHandlers.FileWritingHandler;
import com.httpServer.Handler.SubHandlers.HeaderHandler;
import com.httpServer.Handler.SubHandlers.StatusHandler;

import java.util.HashMap;

public class Handler {

    public static HashMap<String, byte[]> getResponse(HashMap<String, String> request) {
        HashMap<String, byte[]> response = new HashMap<String, byte[]>();
        Route requestRoute = new Route(request);

        new StatusHandler(requestRoute, response).setStatus();
        new HeaderHandler(requestRoute, response).setHeader();
        new BodyHandler(requestRoute, response).setBody();
        new FileWritingHandler(requestRoute).setFile();

        return response;
    }

}