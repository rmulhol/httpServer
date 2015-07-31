package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatchContentHandlerTest {

    @Test
    public void respondsToPatchContentRequest() {
        Request patchContentRequest = new Request();
        patchContentRequest.setMethod("PATCH");
        patchContentRequest.setUri("/../test/com/httpServer/Handlers/TestData/test_data.txt");
        patchContentRequest.setBody("This is a test");
        Response patchContentResponse = new PatchContentHandler().respondToRequest(patchContentRequest);

        assertArrayEquals(ResponseStatus.noContent(), patchContentResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), patchContentResponse.getHeader());
        assertArrayEquals(ResponseBody.noBody(), patchContentResponse.getBody());
    }
}