package com.httpServer.Handler.Route;

class RouteRequestMethodParser {

    private final Route route;
    private final String method;

    RouteRequestMethodParser(Route route) {
        this.route = route;
        this.method = route.request.get("method");
    }

    public void parseRouteMethod() {
        route.setDeleteRequest(isDeleteRequest());
        route.setGetRequest(isGetRequest());
        route.setOptionsRequest(isOptionsRequest());
        route.setPatchRequest(isPatchRequest());
        route.setPostRequest(isPostRequest());
        route.setPutRequest(isPutRequest());
    }

    private boolean isDeleteRequest() {
        return method.equals("DELETE");
    }

    private boolean isGetRequest() {
        return method.equals("GET");
    }

    private boolean isOptionsRequest() {
        return method.equals("OPTIONS");
    }

    private boolean isPatchRequest() {
        return method.equals("PATCH");
    }

    private boolean isPostRequest() {
        return method.equals("POST");
    }

    private boolean isPutRequest() {
        return method.equals("PUT");
    }
}