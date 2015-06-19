package com.httpServer.Handler.Route;

class RouteFileWritingParser {

    private final Route route;

    RouteFileWritingParser(Route route) {
        this.route = route;
    }

    public void parseFileWritingOperation() {
        route.setRequiresFormEdit(requiresFormEdit());
        route.setRequiresFormDelete(requiresFormDelete());
        route.setRequiresPatchContentPatch(requiresPatchContentPatch());
    }

    private boolean requiresFormEdit() {
        return route.isFormRequest() && (route.isPutRequest() || route.isPostRequest());
    }

    private boolean requiresFormDelete() {
        return route.isFormRequest() && route.isDeleteRequest();
    }

    private boolean requiresPatchContentPatch() {
        return route.isPatchContentRequest() && route.isPatchRequest();
    }

}