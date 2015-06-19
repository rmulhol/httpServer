package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.FileIO.MyFileWriter;
import com.httpServer.Handler.Route.Route;

public class FileWritingHandler {

    private final Route requestRoute;

    public FileWritingHandler(Route requestRoute) {
        this.requestRoute = requestRoute;
    }

    public void setFile() {
        if (requestRoute.requiresFormEdit()) {
            MyFileWriter.editFile("/public/form", requestRoute.request.get("body"));
        } else if (requestRoute.requiresFormDelete()) {
            MyFileWriter.deleteFileContents("/public/form");
        } else if (requestRoute.requiresPatchContentPatch()) {
            MyFileWriter.editFile("/public/patch-content.txt", requestRoute.request.get("body"));
        }
    }
}
