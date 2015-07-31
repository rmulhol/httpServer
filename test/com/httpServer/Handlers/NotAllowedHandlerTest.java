package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotAllowedHandlerTest {

    @Test
    public void respondsToNotAllowedRequest() {
        Request notAllowedRequest = new Request();
        notAllowedRequest.setMethod("POST");
        notAllowedRequest.setUri("/file1");
        Response notAllowedResponse = new NotAllowedHandler().respondToRequest(notAllowedRequest);

        assertArrayEquals(ResponseStatus.notAllowed(), notAllowedResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), notAllowedResponse.getHeader());
        assertArrayEquals(ResponseBody.noBody(), notAllowedResponse.getBody());
    }

}