package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.RequestParser;
import com.httpServer.Handler.ResponseContents.ResponseStatus;

import java.util.HashMap;

public class StatusHandler {

    private final RequestParser request;
    private final HashMap<String, byte[]> response;

    public StatusHandler(HashMap<String, String> request, HashMap<String, byte[]> response) {
        this.request = new RequestParser(request);
        this.response = response;
    }

    public void setStatus() {
        if (isOk()) {
            response.put("status", ResponseStatus.ok());
        } else if (request.isPatchRequest()) {
            response.put("status", ResponseStatus.patchContent());
        } else if (request.isGetPartialContent()) {
            response.put("status", ResponseStatus.partialContent());
        } else if (request.isRedirect()) {
            response.put("status", ResponseStatus.redirect());
        } else if (request.isNotAllowed()) {
            response.put("status", ResponseStatus.notAllowed());
        } else if (request.isUnauthorized()) {
            response.put("status", ResponseStatus.unauthorized());
        } else {
            response.put("status", ResponseStatus.fourOhFour());
        }
    }

    private boolean isOk() {
        return request.isRootRequest() || request.isMethodOptions() ||
                request.isParameters() || request.isGetDirectoryFile() ||
                request.isEditForm() || request.isDeleteForm();
    }
}
