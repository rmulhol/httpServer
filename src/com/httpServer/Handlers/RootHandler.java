package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class RootHandler implements Handler {

    private Response rootResponse;

    @Override
    public Response respondToRequest(Request request) {
        rootResponse = new Response();
        setStatus();
        setHeader();
        setBody();
        return rootResponse;
    }

    private void setStatus() {
        rootResponse.setStatus(ResponseStatus.ok());
    }

    private void setHeader() {
        rootResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        rootResponse.setBody(ResponseBody.publicDirectoryContents());
    }
}
