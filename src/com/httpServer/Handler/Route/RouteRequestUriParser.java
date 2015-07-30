package com.httpServer.Handler.Route;

import com.httpServer.Handler.FileIO.MyFileReader;

import java.io.File;
import java.util.Arrays;

class RouteRequestUriParser {

    private final Route route;
    private final String uri;

    RouteRequestUriParser(Route route) {
        this.route = route;
        this.uri = route.request.getUri();
    }

    public void parseRouteUri() {
        route.setAvailableResourceRequest(isAvailableResourceRequest());
        route.setFormRequest(isFormRequest());
        route.setLogsRequest(isLogsRequest());
        route.setMethodOptionsRequest(isMethodOptionsRequest());
        route.setParametersRequest(isParametersRequest());
        route.setPartialContentRequest(isPartialContentRequest());
        route.setPatchContentRequest(isPatchContentRequest());
        route.setRedirectRequest(isRedirectRequest());
        route.setRootRequest(isRootRequest());
    }

    private boolean isAvailableResourceRequest() {
        File thisFile = new File(System.getProperty("user.dir") + "/public" + uri);
        return !uri.equals("/logs") && !uri.equals("/partial_content.txt") &&
                Arrays.asList(MyFileReader.readDirectoryContents()).contains(thisFile);
    }

    private boolean isFormRequest() {
        return uri.equals("/form");
    }

    private boolean isLogsRequest() {
        return uri.equals("/logs");
    }

    private boolean isMethodOptionsRequest() {
        return uri.equals("/method_options");
    }

    private boolean isParametersRequest() {
        return uri.contains("/parameters");
    }

    private boolean isPartialContentRequest() {
        return uri.equals("/partial_content.txt");
    }

    private boolean isPatchContentRequest() {
        return uri.equals("/patch-content.txt");
    }

    private boolean isRedirectRequest() {
        return uri.equals("/redirect");
    }

    private boolean isRootRequest() {
        return uri.equals("/");
    }
}
