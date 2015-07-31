package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class RedirectHandler implements Handler {

    private Response redirectResponse;


    @Override
    public Response respondToRequest(Request request) {
        redirectResponse = new Response();
        setStatus();
        setHeader();
        setBody();
        return redirectResponse;
    }

    private void setStatus() {
        redirectResponse.setStatus(ResponseStatus.redirect());
    }

    private void setHeader() {
        redirectResponse.setHeader(ResponseHeader.location());
    }

    private void setBody() {
        redirectResponse.setBody(ResponseBody.noBody());
    }
}
