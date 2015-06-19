package com.httpServer.Handler.Route;

class RouteResponseBodyParser {

    private final Route route;

    RouteResponseBodyParser(Route route) {
        this.route = route;
    }

    public void parseBody() {
        route.setRequiresDirectoryContentsBody(requiresDirectoryContentsBody());
        route.setRequiresFileContentsBody(requiresFileContentsBody());
        route.setRequiresAuthenticationRequiredBody(requiresAuthenticationRequiredBody());
        route.setRequiresLogsBody(requiresLogsBody());
        route.setRequiresParametersBody(requiresParametersBody());
        route.setRequiresPartialContentBody(requiresPartialContentBody());
    }

    private boolean requiresDirectoryContentsBody() {
        return route.isGetRequest() && route.isRootRequest();
    }

    private boolean requiresFileContentsBody() {
        return route.isGetRequest() && route.isAvailableResourceRequest();
    }

    private boolean requiresAuthenticationRequiredBody() {
        return route.isGetRequest() && route.isLogsRequest() && !route.isAuthorized();
    }

    private boolean requiresLogsBody() {
        return route.isGetRequest() && route.isLogsRequest() && route.isAuthorized();
    }

    private boolean requiresParametersBody() {
        return route.isGetRequest() && route.isParametersRequest();
    }

    private boolean requiresPartialContentBody() {
        return route.isGetRequest() && route.isPartialContentRequest() && route.hasRange();
    }
}