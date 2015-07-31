package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class FourOhFourHandler implements Handler {

    private Response fourOhFourResponse;

    @Override
    public Response respondToRequest(Request request) {
        this.fourOhFourResponse = new Response();
        setStatus();
        setHeader();
        setBody();
        return fourOhFourResponse;
    }

    private void setStatus() {
        this.fourOhFourResponse.setStatus(ResponseStatus.fourOhFour());
    }

    private void setHeader() {
        this.fourOhFourResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        this.fourOhFourResponse.setBody(ResponseBody.noBody());
    }
}
