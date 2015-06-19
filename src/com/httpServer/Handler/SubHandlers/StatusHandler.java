package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.ResponseContents.ResponseStatus;
import com.httpServer.Handler.Route.Route;

import java.util.HashMap;

public class StatusHandler {

    private final Route requestRoute;
    private final HashMap<String, byte[]> response;

    public StatusHandler(Route requestRoute, HashMap<String, byte[]> response) {
        this.requestRoute = requestRoute;
        this.response = response;
    }

    public void setStatus() {
        if (requestRoute.isOkStatus()) {
            response.put("status", ResponseStatus.ok());
        } else if (requestRoute.isNoContentStatus()) {
            response.put("status", ResponseStatus.noContent());
        } else if (requestRoute.isPartialContentStatus()) {
            response.put("status", ResponseStatus.partialContent());
        } else if (requestRoute.isRedirectStatus()) {
            response.put("status", ResponseStatus.redirect());
        } else if (requestRoute.isNotAllowedStatus()) {
            response.put("status", ResponseStatus.notAllowed());
        } else if (requestRoute.isUnauthorizedStatus()) {
            response.put("status", ResponseStatus.unauthorized());
        } else {
            response.put("status", ResponseStatus.fourOhFour());
        }
    }
}
