package com.httpServer.Handler.Route;

import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouteResponseBodyParserTest {

    @Test
    public void setsDirectoryContentsRequired() {
        Request rootRequest = new Request();
        rootRequest.setMethod("GET");
        rootRequest.setUri("/");
        Route rootRoute = new Route(rootRequest);

        assertTrue(rootRoute.requiresDirectoryContentsBody());
    }

    @Test
    public void setsFileContentsRequired() {
        Request fileContentsRequest = new Request();
        fileContentsRequest.setMethod("GET");
        fileContentsRequest.setUri("/file1");
        Route fileContentsRoute = new Route(fileContentsRequest);

        assertTrue(fileContentsRoute.requiresFileContentsBody());
    }

    @Test
    public void setsAuthenticationRequired() {
        Request unauthorizedRequest = new Request();
        unauthorizedRequest.setMethod("GET");
        unauthorizedRequest.setUri("/logs");
        Route unauthorizedRoute = new Route(unauthorizedRequest);

        assertTrue(unauthorizedRoute.requiresAuthenticationRequiredBody());
    }

    @Test
    public void setsLogsRequired() {
        Request logsRequest = new Request();
        logsRequest.setMethod("GET");
        logsRequest.setUri("/logs");
        logsRequest.setAuthorization();
        Route logsRoute = new Route(logsRequest);

        assertTrue(logsRoute.requiresLogsBody());
    }

    @Test
    public void setsParametersRequired() {
        Request parametersRequest = new Request();
        parametersRequest.setMethod("GET");
        parametersRequest.setUri("/parameters?query=true");
        Route parametersRoute = new Route(parametersRequest);

        assertTrue(parametersRoute.requiresParametersBody());
    }

    @Test
    public void setsPartialContentRequired() {
        Request partialContentRequest = new Request();
        partialContentRequest.setMethod("GET");
        partialContentRequest.setUri("/partial_content.txt");
        partialContentRequest.setRange("0-4");
        Route partialContentRoute = new Route(partialContentRequest);

        assertTrue(partialContentRoute.requiresPartialContentBody());
    }
}