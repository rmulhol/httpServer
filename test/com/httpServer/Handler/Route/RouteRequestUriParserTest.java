package com.httpServer.Handler.Route;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RouteRequestUriParserTest {

    @Test
    public void setsAvailableResourceUri() {
        HashMap<String, String> availableResourceRequest = new HashMap<String, String>();
        availableResourceRequest.put("method", "GET");
        availableResourceRequest.put("uri", "/file1");
        Route availableResourceRoute = new Route(availableResourceRequest);

        assertTrue(availableResourceRoute.isAvailableResourceRequest());
    }

    @Test
    public void setsFormUri() {
        HashMap<String, String> formRequest = new HashMap<String, String>();
        formRequest.put("method", "GET");
        formRequest.put("uri", "/form");
        Route formRoute = new Route(formRequest);

        assertTrue(formRoute.isFormRequest());
    }

    @Test
    public void setsLogsUri() {
        HashMap<String, String> logsRequest = new HashMap<String, String>();
        logsRequest.put("method", "GET");
        logsRequest.put("uri", "/logs");
        Route logsRoute = new Route(logsRequest);

        assertTrue(logsRoute.isLogsRequest());
    }

    @Test
    public void setsMethodOptionsUri() {
        HashMap<String, String> methodOptionsRequest = new HashMap<String, String>();
        methodOptionsRequest.put("method", "GET");
        methodOptionsRequest.put("uri", "/method_options");
        Route methodOptionsRoute = new Route(methodOptionsRequest);

        assertTrue(methodOptionsRoute.isMethodOptionsRequest());
    }

    @Test
    public void setsParametersUri() {
        HashMap<String, String> parametersRequest = new HashMap<String, String>();
        parametersRequest.put("method", "GET");
        parametersRequest.put("uri", "/parameters?query=true");
        Route parametersRoute = new Route(parametersRequest);

        assertTrue(parametersRoute.isParametersRequest());
    }

    @Test
    public void setsPartialContentUri() {
        HashMap<String, String> partialContentRequest = new HashMap<String, String>();
        partialContentRequest.put("method", "GET");
        partialContentRequest.put("uri", "/partial_content.txt");
        Route partialContentRoute = new Route(partialContentRequest);

        assertTrue(partialContentRoute.isPartialContentRequest());
    }

    @Test
    public void setsPatchContentUri() {
        HashMap<String, String> patchRequest = new HashMap<String, String>();
        patchRequest.put("method", "GET");
        patchRequest.put("uri", "/patch-content.txt");
        Route patchRoute = new Route(patchRequest);

        assertTrue(patchRoute.isPatchContentRequest());
    }

    @Test
    public void setsRedirectUri() {
        HashMap<String, String> redirectRequest = new HashMap<String, String>();
        redirectRequest.put("method", "GET");
        redirectRequest.put("uri", "/redirect");
        Route redirectRoute = new Route(redirectRequest);

        assertTrue(redirectRoute.isRedirectRequest());
    }

    @Test
    public void setsRootUri() {
        HashMap<String, String> rootRequest = new HashMap<String, String>();
        rootRequest.put("method", "GET");
        rootRequest.put("uri", "/");
        Route rootRoute = new Route(rootRequest);

        assertTrue(rootRoute.isRootRequest());
    }
}