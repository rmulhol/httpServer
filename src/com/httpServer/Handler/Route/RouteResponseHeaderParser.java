package com.httpServer.Handler.Route;

class RouteResponseHeaderParser {

    private final Route route;

    RouteResponseHeaderParser(Route route) {
        this.route = route;
    }

    public void parseResponseHeader() {
        route.setRequiresLocationHeader(requiresLocationHeader());
        route.setRequiresMethodOptionsHeader(requiresMethodOptionsHeader());
    }

    private boolean requiresLocationHeader() {
        return route.isGetRequest() && route.isRedirectRequest();
    }

    private boolean requiresMethodOptionsHeader() {
        return route.isOptionsRequest() && route.isMethodOptionsRequest();
    }
}
