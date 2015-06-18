package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.FileIO.MyFileWriter;
import com.httpServer.Handler.RequestParser;

import java.util.HashMap;

public class FileWritingHandler {

    private final RequestParser request;

    public FileWritingHandler(HashMap<String, String> request) {
        this.request = new RequestParser(request);
    }

    public void setFile() {
        if (request.isEditForm()) {
            MyFileWriter.editFile("/public/form", request.getBody());
        } else if (request.isDeleteForm()) {
            MyFileWriter.deleteFileContents("/public/form");
        } else if (request.isPatchRequest()) {
            MyFileWriter.editFile("/public/patch-content.txt", request.getBody());
        }
    }
}
