package com.httpServer;

import java.util.HashMap;

public class Handler {

    public static HashMap<String, byte[]> getResponse(HashMap<String, String> request) {
        HashMap<String, byte[]> response = new HashMap<String, byte[]>();

        new StatusHandler(request, response).setStatus();
        new HeaderHandler(request, response).setHeader();
        new BodyHandler(request, response).setBody();

        return response;
    }

}
