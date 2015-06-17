package com.httpServer;

import java.util.HashMap;

public class FileWritingHandler {

    RequestParser request;
    HashMap<String, byte[]> response;

    FileWritingHandler(HashMap<String, String> request, HashMap<String, byte[]> response) {
        this.request = new RequestParser(request);
        this.response = response;
    }

    public void setFile() {
        if (request.isEditForm()) {
            MyFileWriter.editFile("/public/form", request.body);
        } else if (request.isDeleteForm()) {
            MyFileWriter.deleteFileContents("/public/form");
        }
    }
}
