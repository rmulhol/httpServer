package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class PartialContentHandler implements Handler {

    private Response partialContentResponse = new Response();
    private Request request;

    @Override
    public Response respondToRequest(Request request) {
        partialContentResponse = new Response();
        this.request = request;
        setStatus();
        setHeader();
        setBody();
        return partialContentResponse;
    }

    private void setStatus() {
        partialContentResponse.setStatus(ResponseStatus.partialContent());
    }

    private void setHeader() {
        partialContentResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        partialContentResponse.setBody(ResponseBody.partialContent(request.getRange()));
    }
}
