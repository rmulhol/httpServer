package com.httpServer.Handlers;

import com.httpServer.Handlers.FileIO.LogsAssembler;
import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class LogsHandler implements Handler {

    private Response logsResponse;
    private Request request;

    @Override
    public Response respondToRequest(Request request) {
        logsResponse = new Response();
        this.request = request;
        setStatus();
        setHeader();
        setBody();
        return logsResponse;
    }

    private void setStatus() {
        if (isAuthorized()) {
            logsResponse.setStatus(ResponseStatus.ok());
        } else {
            logsResponse.setStatus(ResponseStatus.unauthorized());
        }
    }

    private void setHeader() {
        logsResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        if (isAuthorized()) {
            logsResponse.setBody(LogsAssembler.readLogs());
        } else {
            logsResponse.setBody(ResponseBody.authenticationRequired());
        }
    }

    private boolean isAuthorized() {
        return this.request.getAuthorization();
    }
}
