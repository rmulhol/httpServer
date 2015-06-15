package com.httpServer;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class RequestParser {

    private final HashMap<String, String> request;
    final String uri;

    RequestParser(HashMap<String, String> request) {
        this.request = request;
        this.uri = request.get("uri");
    }

    public boolean isRootRequest() {
        return request.get("method").equals("GET") && request.get("uri").equals("/");
    }

    public boolean isGetDirectoryFile() {
        File thisFile = new File(System.getProperty("user.dir") + "/public" + uri);
        return request.get("method").equals("GET") &&
                Arrays.asList(MyFileReader.readDirectoryContents()).contains(thisFile);
    }

    public boolean isMethodOptions() {
        return request.get("method").equals("GET") && request.get("uri").equals("/method_options");
    }

    public boolean isParameters() {
        return request.get("method").equals("GET") && request.get("uri").contains("/parameters");
    }

    public boolean isRedirect() {
        return request.get("method").equals("GET") && request.get("uri").equals("/redirect");
    }

    public boolean isUnauthorized() {
        return request.get("method").equals("GET") && request.get("uri").equals("/logs") &&
                !request.containsKey("authorization");
    }

    public boolean isNotAllowed() {
        String requestMethod = request.get("method");
        return (requestMethod.equals("PUT") || requestMethod.equals("POST") || requestMethod.equals("DELETE")) &&
                !request.get("uri").equals("/form");
    }
}