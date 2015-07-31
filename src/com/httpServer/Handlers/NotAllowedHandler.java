package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class NotAllowedHandler implements Handler {

    private Response notAllowedResponse;

    @Override
    public Response respondToRequest(Request request) {
        notAllowedResponse = new Response();
        setStatus();
        setHeader();
        setBody();
        return notAllowedResponse;
    }

    private void setStatus() {
        notAllowedResponse.setStatus(ResponseStatus.notAllowed());
    }

    private void setHeader() {
        notAllowedResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        notAllowedResponse.setBody(ResponseBody.noBody());
    }
}
