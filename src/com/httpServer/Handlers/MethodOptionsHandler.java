package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class MethodOptionsHandler implements Handler {

    private Response methodOptionsResponse;

    @Override
    public Response respondToRequest(Request request) {
        methodOptionsResponse = new Response();
        setStatus();
        setHeader();
        setBody();
        return methodOptionsResponse;
    }

    private void setStatus() {
        methodOptionsResponse.setStatus(ResponseStatus.ok());
    }

    private void setHeader() {
        methodOptionsResponse.setHeader(ResponseHeader.methodOptions());
    }

    private void setBody() {
        methodOptionsResponse.setBody(ResponseBody.noBody());
    }
}
