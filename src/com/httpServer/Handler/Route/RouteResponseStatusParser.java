package com.httpServer.Handler.Route;

class RouteResponseStatusParser {

    private final Route route;

    RouteResponseStatusParser(Route route) {
        this.route = route;
    }

    public void parseStatus() {
        route.setOkStatus(isOk());
        route.setNotAllowedStatus(isNotAllowed());
        route.setPartialContentStatus(isPartialContent());
        route.setNoContentStatus(isNoContent());
        route.setUnauthorizedStatus(isUnauthorized());
        route.setRedirectStatus(isRedirect());
    }

    private boolean isOk() {
        return isValidGetRequest() || isValidFormRequest() ||
                (route.isOptionsRequest() && route.isMethodOptionsRequest());
    }

    private boolean isNotAllowed() {
        return !route.isFormRequest() &&
                (route.isPutRequest() || route.isPostRequest() || route.isDeleteRequest());
    }

    private boolean isPartialContent() {
        return route.isGetRequest() && route.isPartialContentRequest() && route.hasRange();
    }

    private boolean isNoContent() {
        return route.isPatchRequest() && route.isPatchContentRequest();
    }

    private boolean isUnauthorized() {
        return route.isLogsRequest() && !route.isAuthorized();
    }

    private boolean isRedirect() {
        return route.isGetRequest() && route.isRedirectRequest();
    }

    private boolean isValidGetRequest() {
        return route.isGetRequest() &&
                ((route.isLogsRequest() && route.isAuthorized()) ||
                (route.isRootRequest() || route.isParametersRequest() ||
                 route.isAvailableResourceRequest()));
    }

    private boolean isValidFormRequest() {
        return route.isFormRequest() &&
                (route.isGetRequest() || route.isPostRequest() ||
                 route.isPutRequest() || route.isDeleteRequest());
    }
}