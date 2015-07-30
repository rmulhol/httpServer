package com.httpServer.Handler;

import com.httpServer.Handler.Route.Route;
import com.httpServer.Handler.SubHandlers.BodyHandler;
import com.httpServer.Handler.SubHandlers.FileWritingHandler;
import com.httpServer.Handler.SubHandlers.HeaderHandler;
import com.httpServer.Handler.SubHandlers.StatusHandler;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class Handler {

    public static Response getResponse(Request request) {
        Response response = new Response();
        Route requestRoute = new Route(request);

        new StatusHandler(requestRoute, response).setStatus();
        new HeaderHandler(requestRoute, response).setHeader();
        new BodyHandler(requestRoute, response).setBody();
        new FileWritingHandler(requestRoute).setFile();

        return response;
    }

}