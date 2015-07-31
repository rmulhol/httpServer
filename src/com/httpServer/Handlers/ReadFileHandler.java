package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class ReadFileHandler implements Handler {

    private Response readFileResponse;
    private Request request;

    @Override
    public Response respondToRequest(Request request) {
        readFileResponse = new Response();
        this.request = request;
        setStatus();
        setHeader();
        setBody();
        return readFileResponse;
    }

    private void setStatus() {
        readFileResponse.setStatus(ResponseStatus.ok());
    }

    private void setHeader() {
        readFileResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        readFileResponse.setBody(ResponseBody.fileContents(request.getUri()));
    }
}
