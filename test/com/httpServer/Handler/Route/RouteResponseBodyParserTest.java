package com.httpServer.Handler.Route;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RouteResponseBodyParserTest {

    @Test
    public void setsDirectoryContentsRequired() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");
        Route rootRoute = new Route(rootRequest);

        assertTrue(rootRoute.requiresDirectoryContentsBody());
    }

    @Test
    public void setsFileContentsRequired() {
        HashMap<String, String> fileContentsRequest = new HashMap<String, String>();
        fileContentsRequest.put("method", "GET");
        fileContentsRequest.put("uri", "/file1");
        Route fileContentsRoute = new Route(fileContentsRequest);

        assertTrue(fileContentsRoute.requiresFileContentsBody());
    }

    @Test
    public void setsAuthenticationRequired() {
        HashMap<String, String> unauthorizedRequest = new HashMap<String, String>();
        unauthorizedRequest.put("method", "GET");
        unauthorizedRequest.put("uri", "/logs");
        Route unauthorizedRoute = new Route(unauthorizedRequest);

        assertTrue(unauthorizedRoute.requiresAuthenticationRequiredBody());
    }

    @Test
    public void setsLogsRequired() {
        HashMap<String, String> logsRequest = new HashMap<String, String>();
        logsRequest.put("method", "GET");
        logsRequest.put("uri", "/logs");
        logsRequest.put("authorization", "true");
        Route logsRoute = new Route(logsRequest);

        assertTrue(logsRoute.requiresLogsBody());
    }

    @Test
    public void setsParametersRequired() {
        HashMap<String, String> parametersRequest = new HashMap<String, String>();
        parametersRequest.put("method", "GET");
        parametersRequest.put("uri", "/parameters?query=true");
        Route parametersRoute = new Route(parametersRequest);

        assertTrue(parametersRoute.requiresParametersBody());
    }

    @Test
    public void setsPartialContentRequired() {
        HashMap<String, String> partialContentRequest = new HashMap<String, String>();
        partialContentRequest.put("method", "GET");
        partialContentRequest.put("uri", "/partial_content.txt");
        partialContentRequest.put("range", "0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        assertTrue(partialContentRoute.requiresPartialContentBody());
    }
}