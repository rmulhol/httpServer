package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.FileIO.LogsAssembler;
import com.httpServer.Handler.ResponseContents.ResponseBody;
import com.httpServer.Handler.Route.Route;
import com.httpServer.ResponseAdapter.Response;

public class BodyHandler {

    private final Route requestRoute;
    private final Response response;

    public BodyHandler(Route requestRoute, Response response) {
        this.requestRoute = requestRoute;
        this.response = response;
    }

    public void setBody() {
        if (requestRoute.requiresDirectoryContentsBody()) {
            response.setBody(ResponseBody.publicDirectoryContents());
        } else if (requestRoute.requiresFileContentsBody()) {
            response.setBody(ResponseBody.fileContents(requestRoute.request.getUri()));
        } else if (requestRoute.requiresAuthenticationRequiredBody()) {
            response.setBody(ResponseBody.authenticationRequired());
        } else if (requestRoute.requiresLogsBody()) {
            response.setBody(LogsAssembler.readLogs());
        } else if (requestRoute.requiresParametersBody()) {
            response.setBody(ResponseBody.parameters(requestRoute.request.getUri()));
        } else if (requestRoute.requiresPartialContentBody()) {
            response.setBody(ResponseBody.partialContent(requestRoute.request.getRange()));
        } else {
            response.setBody(ResponseBody.noBody());
        }
    }
}
