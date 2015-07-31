package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedirectHandlerTest {

    @Test
    public void respondsToGetRedirectRequest() {
        Request getRedirectRequest = new Request();
        getRedirectRequest.setMethod("GET");
        getRedirectRequest.setUri("/redirect");
        Response getRedirectResponse = new RedirectHandler().respondToRequest(getRedirectRequest);

        assertArrayEquals(ResponseStatus.redirect(), getRedirectResponse.getStatus());
        assertArrayEquals(ResponseHeader.location(), getRedirectResponse.getHeader());
        assertArrayEquals(ResponseBody.noBody(), getRedirectResponse.getBody());
    }

}