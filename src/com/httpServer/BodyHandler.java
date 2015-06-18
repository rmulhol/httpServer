package com.httpServer;

import java.util.HashMap;

public class BodyHandler {

    private final RequestParser request;
    private final HashMap<String, byte[]> response;

    BodyHandler(HashMap<String, String> request, HashMap<String, byte[]> response) {
        this.request = new RequestParser(request);
        this.response = response;
    }

    public void setBody() {
        if (request.isRootRequest()) {
            response.put("body", ResponseBody.publicDirectoryContents());
        } else if (request.isGetDirectoryFile()) {
            response.put("body", ResponseBody.fileContents(request.uri));
        } else if (request.isUnauthorized()) {
            response.put("body", ResponseBody.authenticationRequired());
        } else if (request.isParameters()) {
            response.put("body", ResponseBody.parameters(request.uri));
        } else if (request.isGetPartialContent()) {
            response.put("body", ResponseBody.partialContent(request.getRange()));
        } else {
            response.put("body", ResponseBody.noBody());
        }
    }
}
