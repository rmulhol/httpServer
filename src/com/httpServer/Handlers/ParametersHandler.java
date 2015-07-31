package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class ParametersHandler implements Handler {
    private Response parameterResponse;
    private Request request;

    @Override
    public Response respondToRequest(Request request) {
        parameterResponse = new Response();
        this.request = request;
        setStatus();
        setHeader();
        setBody();
        return parameterResponse;
    }

    private void setStatus() {
        parameterResponse.setStatus(ResponseStatus.ok());
    }

    private void setHeader() {
        parameterResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        parameterResponse.setBody(ResponseBody.parameters(request.getParameters()));
    }
}
