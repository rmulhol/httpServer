package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class FourOhFourHandlerTest {

    @Test
    public void respondsToUnrecognizedRequest() {
        Request unrecognizedRequest = new Request();
        unrecognizedRequest.setMethod("GET");
        unrecognizedRequest.setUri("/foobar");
        Response fourOhFourResponse = new FourOhFourHandler().respondToRequest(unrecognizedRequest);

        assertArrayEquals(ResponseStatus.fourOhFour(), fourOhFourResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), fourOhFourResponse.getHeader());
        assertArrayEquals(ResponseBody.noBody(), fourOhFourResponse.getBody());
    }
}