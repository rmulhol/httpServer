package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReadFileHandlerTest {

    @Test
    public void respondsToGetFileRequest() {
        Request getFileRequest = new Request();
        getFileRequest.setMethod("GET");
        getFileRequest.setUri("/../test/com/httpServer/Handlers/TestData/test_data.txt");
        Response getFileResponse = new ReadFileHandler().respondToRequest(getFileRequest);

        assertArrayEquals(ResponseStatus.ok(), getFileResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), getFileResponse.getHeader());
        assertArrayEquals(ResponseBody.fileContents(getFileRequest.getUri()), getFileResponse.getBody());
    }
}