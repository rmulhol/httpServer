package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartialContentHandlerTest {

    @Test
    public void respondsToPartialContentRequest() {
        Request partialContentRequest = new Request();
        partialContentRequest.setMethod("GET");
        partialContentRequest.setUri("/../test/com/httpServer/Handlers/TestData/test_data.txt");
        partialContentRequest.setRange("0-4");
        Response partialContentResponse = new PartialContentHandler().respondToRequest(partialContentRequest);

        assertArrayEquals(ResponseStatus.partialContent(), partialContentResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), partialContentResponse.getHeader());
        assertArrayEquals("This ".getBytes(), partialContentResponse.getBody());
    }
}