package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseStatus;
import com.httpServer.Handler.Route.Route;
import com.httpServer.ResponseAdapter.Response;

public class StatusHandler {

    private final Route requestRoute;
    private final Response response;

    public StatusHandler(Route requestRoute, Response response) {
        this.requestRoute = requestRoute;
        this.response = response;
    }

    public void setStatus() {
        if (requestRoute.isOkStatus()) {
            response.setStatus(ResponseStatus.ok());
        } else if (requestRoute.isNoContentStatus()) {
            response.setStatus(ResponseStatus.noContent());
        } else if (requestRoute.isPartialContentStatus()) {
            response.setStatus(ResponseStatus.partialContent());
        } else if (requestRoute.isRedirectStatus()) {
            response.setStatus(ResponseStatus.redirect());
        } else if (requestRoute.isNotAllowedStatus()) {
            response.setStatus(ResponseStatus.notAllowed());
        } else if (requestRoute.isUnauthorizedStatus()) {
            response.setStatus(ResponseStatus.unauthorized());
        } else {
            response.setStatus(ResponseStatus.fourOhFour());
        }
    }
}
