package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class MethodOptionsHandlerTest {

    @Test
    public void respondsToMethodOptionsRequest() {
        Request methodOptionsRequest = new Request();
        methodOptionsRequest.setMethod("OPTIONS");
        methodOptionsRequest.setUri("/method_options");
        Response methodOptionsResponse = new MethodOptionsHandler().respondToRequest(methodOptionsRequest);

        assertArrayEquals(ResponseStatus.ok(), methodOptionsResponse.getStatus());
        assertArrayEquals(ResponseHeader.methodOptions(), methodOptionsResponse.getHeader());
        assertArrayEquals(ResponseBody.noBody(), methodOptionsResponse.getBody());
    }

}