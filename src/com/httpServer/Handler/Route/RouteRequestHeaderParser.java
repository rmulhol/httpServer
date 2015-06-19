package com.httpServer.Handler.Route;

class RouteRequestHeaderParser {

    private final Route route;

    RouteRequestHeaderParser(Route route) {
        this.route = route;
    }

    public void parseRequestHeader() {
        route.setAuthorized(isAuthorized());
        route.setHasRange(hasRange());
    }

    private boolean isAuthorized() {
        return route.request.containsKey("authorization");
    }

    private boolean hasRange() {
        return route.request.containsKey("range");
    }
}
