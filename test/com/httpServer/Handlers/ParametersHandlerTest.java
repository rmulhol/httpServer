package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParametersHandlerTest {

    @Test
    public void respondsToParametersRequest() {
        Request parametersRequest = new Request();
        parametersRequest.setMethod("GET");
        parametersRequest.setUri("/parameters");
        parametersRequest.setParameters("query=true");
        Response parametersResponse = new ParametersHandler().respondToRequest(parametersRequest);

        assertArrayEquals(ResponseStatus.ok(), parametersResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), parametersResponse.getHeader());
        assertArrayEquals(ResponseBody.parameters("query=true"), parametersResponse.getBody());
    }
}