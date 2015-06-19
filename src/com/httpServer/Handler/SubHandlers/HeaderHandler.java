package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseHeader;
import com.httpServer.Handler.Route.Route;

import java.util.HashMap;

public class HeaderHandler {

    private final Route request;
    private final HashMap<String, byte[]> response;

    public HeaderHandler(Route request, HashMap<String, byte[]> response) {
        this.request = request;
        this.response = response;
    }

    public void setHeader() {
        if (request.requiresMethodOptionsHeader()) {
            response.put("header", ResponseHeader.methodOptions());
        } else if (request.requiresLocationHeader()) {
            response.put("header", ResponseHeader.location());
        } else {
            response.put("header", ResponseHeader.noHeader());
        }
    }
}
