package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseHeader;
import com.httpServer.Handler.Route.Route;
import com.httpServer.ResponseAdapter.Response;

public class HeaderHandler {

    private final Route request;
    private final Response response;

    public HeaderHandler(Route request, Response response) {
        this.request = request;
        this.response = response;
    }

    public void setHeader() {
        if (request.requiresMethodOptionsHeader()) {
            response.setHeader(ResponseHeader.methodOptions());
        } else if (request.requiresLocationHeader()) {
            response.setHeader(ResponseHeader.location());
        } else {
            response.setHeader(ResponseHeader.noHeader());
        }
    }
}
