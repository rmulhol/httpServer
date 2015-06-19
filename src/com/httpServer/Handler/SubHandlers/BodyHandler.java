package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.FileIO.LogsAssembler;
import com.httpServer.Handler.ResponseContents.ResponseBody;
import com.httpServer.Handler.Route.Route;

import java.util.HashMap;

public class BodyHandler {

    private final Route requestRoute;
    private final HashMap<String, byte[]> response;

    public BodyHandler(Route requestRoute, HashMap<String, byte[]> response) {
        this.requestRoute = requestRoute;
        this.response = response;
    }

    public void setBody() {
        if (requestRoute.requiresDirectoryContentsBody()) {
            response.put("body", ResponseBody.publicDirectoryContents());
        } else if (requestRoute.requiresFileContentsBody()) {
            response.put("body", ResponseBody.fileContents(requestRoute.request.get("uri")));
        } else if (requestRoute.requiresAuthenticationRequiredBody()) {
            response.put("body", ResponseBody.authenticationRequired());
        } else if (requestRoute.requiresLogsBody()) {
            response.put("body", LogsAssembler.readLogs());
        } else if (requestRoute.requiresParametersBody()) {
            response.put("body", ResponseBody.parameters(requestRoute.request.get("uri")));
        } else if (requestRoute.requiresPartialContentBody()) {
            response.put("body", ResponseBody.partialContent(requestRoute.request.get("range")));
        } else {
            response.put("body", ResponseBody.noBody());
        }
    }
}
