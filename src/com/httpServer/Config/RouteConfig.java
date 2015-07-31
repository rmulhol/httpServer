package com.httpServer.Config;

import com.httpServer.Handlers.*;
import com.httpServer.Router.RouteRegistrar;

public class RouteConfig {

    public static void setupRoutes() {
        RouteRegistrar.addRoute("/", "GET", new RootHandler());
        RouteRegistrar.addRoute("/method_options", "OPTIONS", new MethodOptionsHandler());
        RouteRegistrar.addRoute("/redirect", "GET", new RedirectHandler());
        RouteRegistrar.addRoute("/logs", "GET", new LogsHandler());
        RouteRegistrar.addRoute("/parameters", "GET", new ParametersHandler());

        RouteRegistrar.addRoute("/form", "GET", new ReadFileHandler());
        RouteRegistrar.addRoute("/form", "PUT", new EditFileHandler());
        RouteRegistrar.addRoute("/form", "POST", new EditFileHandler());
        RouteRegistrar.addRoute("/form", "DELETE", new EditFileHandler());

        RouteRegistrar.addRoute("/file1", "GET", new ReadFileHandler());
        RouteRegistrar.addRoute("/file2", "GET", new ReadFileHandler());
        RouteRegistrar.addRoute("/image.gif", "GET", new ReadFileHandler());
        RouteRegistrar.addRoute("/image.jpeg", "GET", new ReadFileHandler());
        RouteRegistrar.addRoute("/image.png", "GET", new ReadFileHandler());
        RouteRegistrar.addRoute("/partial_content.txt", "GET", new PartialContentHandler());
        RouteRegistrar.addRoute("/patch-content.txt", "GET", new ReadFileHandler());
        RouteRegistrar.addRoute("/patch-content.txt", "PATCH", new PatchContentHandler());
        RouteRegistrar.addRoute("/text-file.txt", "GET", new ReadFileHandler());
    }
}
