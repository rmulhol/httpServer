package com.httpServer.Handlers;

import com.httpServer.Handlers.FileIO.MyFileWriter;
import com.httpServer.Handlers.ResponseContents.ResponseBody;
import com.httpServer.Handlers.ResponseContents.ResponseHeader;
import com.httpServer.Handlers.ResponseContents.ResponseStatus;
import com.httpServer.RequestAdapter.Request;
import com.httpServer.ResponseAdapter.Response;

public class PatchContentHandler implements Handler {

    private Response patchContentResponse;

    @Override
    public Response respondToRequest(Request request) {
        patchContentResponse = new Response();
        patchFile(request);
        setStatus();
        setHeader();
        setBody();
        return patchContentResponse;
    }

    private void setStatus() {
        patchContentResponse.setStatus(ResponseStatus.noContent());
    }

    private void setHeader() {
        patchContentResponse.setHeader(ResponseHeader.noHeader());
    }

    private void setBody() {
        patchContentResponse.setBody(ResponseBody.noBody());
    }

    void patchFile(Request request) {
        MyFileWriter.editFile("/public" + request.getUri(), request.getBody());
    }
}
