package com.httpServer.Handlers;

import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditFileHandlerTest {

    @Test
    public void respondsToEditFileRequest() {
        Request editFileRequest = new Request();
        editFileRequest.setMethod("POST");
        editFileRequest.setUri("/../test/com/httpServer/Handlers/TestData/test_data.txt");
        editFileRequest.setBody("This is a test");
        Response editFileResponse = new EditFileHandler().respondToRequest(editFileRequest);

        assertArrayEquals(ResponseStatus.ok(), editFileResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), editFileResponse.getHeader());
        assertArrayEquals(ResponseBody.noBody(), editFileResponse.getBody());
    }

    @Test
    public void respondsToDeleteFileRequest() {
        Request deleteFileRequest = new Request();
        deleteFileRequest.setMethod("DELETE");
        deleteFileRequest.setUri("/../test/com/httpServer/Handlers/TestData/test_data.txt");
        Response deleteFileResponse = new EditFileHandler().respondToRequest(deleteFileRequest);

        assertArrayEquals(ResponseStatus.ok(), deleteFileResponse.getStatus());
        assertArrayEquals(ResponseHeader.noHeader(), deleteFileResponse.getHeader());
        assertArrayEquals(ResponseBody.noBody(), deleteFileResponse.getBody());
    }

}