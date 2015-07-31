package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class RootHandlerTest {

    @Test
    public void respondsToGetRootRequest() {
        Request getRootRequest = new Request();
        getRootRequest.setMethod("GET");
        getRootRequest.setUri("/");
        Response getRootResponse = new RootHandler().respondToRequest(getRootRequest);

        assertArrayEquals(ResponseStatus.ok(), getRootResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), getRootResponse.getHeader());
        assertArrayEquals(ResponseBody.publicDirectoryContents(), getRootResponse.getBody());
    }

}