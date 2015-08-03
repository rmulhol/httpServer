package com.httpServer.Config;

import com.httpServer.Handlers.*;
import com.httpServer.Handlers.FileIO.MyFileReader;
import com.httpServer.Router.RouteRegistrar;

import java.io.File;

public class RouteConfig {

    public static void setupRoutes() {
        RouteRegistrar.addRoute("/", "GET", new RootHandler());

        File[] listOfFiles = MyFileReader.readDirectoryContents();
        for (File file : listOfFiles) {
            String uri = "/" + file.getName();
            RouteRegistrar.addRoute(uri, "GET", new ReadFileHandler());
        }

        RouteRegistrar.addRoute("/form", "PUT", new EditFileHandler());
        RouteRegistrar.addRoute("/form", "POST", new EditFileHandler());
        RouteRegistrar.addRoute("/form", "DELETE", new EditFileHandler());

        RouteRegistrar.addRoute("/logs", "GET", new LogsHandler());

        RouteRegistrar.addRoute("/method_options", "OPTIONS", new MethodOptionsHandler());

        RouteRegistrar.addRoute("/parameters", "GET", new ParametersHandler());

        RouteRegistrar.addRoute("/partial_content.txt", "GET", new PartialContentHandler());

        RouteRegistrar.addRoute("/patch-content.txt", "PATCH", new PatchContentHandler());

        RouteRegistrar.addRoute("/redirect", "GET", new RedirectHandler());
    }
}
