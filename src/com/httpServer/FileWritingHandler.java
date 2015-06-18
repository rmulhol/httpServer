package com.httpServer;

import java.util.HashMap;

class FileWritingHandler {

    private final RequestParser request;

    FileWritingHandler(HashMap<String, String> request) {
        this.request = new RequestParser(request);
    }

    public void setFile() {
        if (request.isEditForm()) {
            MyFileWriter.editFile("/public/form", request.body);
        } else if (request.isDeleteForm()) {
            MyFileWriter.deleteFileContents("/public/form");
        } else if (request.isPatchRequest()) {
            MyFileWriter.editFile("/public/patch-content.txt", request.body);
        }
    }
}
