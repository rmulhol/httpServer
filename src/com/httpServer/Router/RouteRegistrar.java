package com.httpServer.Router;

import com.httpServer.Handlers.FourOhFourHandler;
import com.httpServer.Handlers.Handler;
import com.httpServer.Handlers.NotAllowedHandler;
import com.httpServer.RequestAdapter.Request;

import java.util.HashMap;

public class RouteRegistrar {

    private static HashMap<String, HashMap<String, Handler>> routes = new HashMap<String, HashMap<String, Handler>>();

    public static void addRoute(String uri, String method, Handler handler) {
        if (routes.containsKey(uri)) {
            HashMap<String, Handler> currentPaths = routes.get(uri);
            currentPaths.put(method, handler);
            routes.replace(uri, currentPaths);
        } else {
            HashMap<String, Handler> newPath = new HashMap<String, Handler>();
            newPath.put(method, handler);
            routes.put(uri, newPath);
        }
    }

    public static Handler getRoute(Request request) {
        if (routes.containsKey(request.getUri())) {
            HashMap<String, Handler> availablePaths = routes.get(request.getUri());
            if (availablePaths.containsKey(request.getMethod())) {
                return availablePaths.get(request.getMethod());
            } else {
                return new NotAllowedHandler();
            }
        } else {
            return new FourOhFourHandler();
        }
    }

    public static void clearRoutes() {
        routes = new HashMap<String, HashMap<String, Handler>>();
    }

    public static HashMap<String, HashMap<String, Handler>> getRoutes() {
        return routes;
    }

}
