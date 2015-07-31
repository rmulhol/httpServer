package com.httpServer.Handlers;

import com.httpServer.Handlers.FileIO.MyFileWriter;
import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class EditFileHandler implements Handler {

    private Response editFileResponse;

    @Override
    public Response respondToRequest(Request request) {
        editFileResponse = new Response();
        editFile(request);
        setStatus();
        setHeader();
        setBody();
        return editFileResponse;
    }

    private void setStatus() {
        editFileResponse.setStatus(ResponseStatus.ok());
    }

    private void setHeader() {
        editFileResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        editFileResponse.setBody(ResponseBody.noBody());
    }

    void editFile(Request request) {
        if (request.getMethod().equals("DELETE")) {
            MyFileWriter.deleteFileContents("/public" + request.getUri());
        } else {
            MyFileWriter.editFile("/public" + request.getUri(), request.getBody());
        }
    }
}
