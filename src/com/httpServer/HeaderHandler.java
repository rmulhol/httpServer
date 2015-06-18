package com.httpServer;

import java.util.HashMap;

class HeaderHandler {

    private final RequestParser request;
    private final HashMap<String, byte[]> response;

    HeaderHandler(HashMap<String, String> request, HashMap<String, byte[]> response) {
        this.request = new RequestParser(request);
        this.response = response;
    }

    public void setHeader() {
        if (request.isMethodOptions()) {
            response.put("header", ResponseHeader.methodOptions());
        } else if (request.isRedirect()) {
            response.put("header", ResponseHeader.location());
        } else {
            response.put("header", ResponseHeader.noHeader());
        }
    }
}
