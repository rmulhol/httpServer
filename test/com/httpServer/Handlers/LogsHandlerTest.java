package com.httpServer.Handlers;

import com.httpServer.Handlers.FileIO.LogsAssembler;
import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogsHandlerTest {

    @Test
    public void respondsToUnauthorizedLogsRequest() {
        Request unauthorizedLogsRequest = new Request();
        unauthorizedLogsRequest.setMethod("GET");
        unauthorizedLogsRequest.setUri("/logs");
        Response unauthorizedLogsResponse = new LogsHandler().respondToRequest(unauthorizedLogsRequest);

        assertArrayEquals(ResponseStatus.unauthorized(), unauthorizedLogsResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), unauthorizedLogsResponse.getHeader());
        assertArrayEquals(ResponseBody.authenticationRequired(), unauthorizedLogsResponse.getBody());
    }

    @Test
    public void respondsToAuthorizedLogsRequest() {
        Request authorizedLogsRequest = new Request();
        authorizedLogsRequest.setMethod("GET");
        authorizedLogsRequest.setUri("/logs");
        authorizedLogsRequest.setAuthorization();
        Response authorizedLogsResponse = new LogsHandler().respondToRequest(authorizedLogsRequest);

        assertArrayEquals(ResponseStatus.ok(), authorizedLogsResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), authorizedLogsResponse.getHeader());
        assertArrayEquals(LogsAssembler.readLogs(), authorizedLogsResponse.getBody());
    }
}