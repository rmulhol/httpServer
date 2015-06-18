package com.httpServer;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

class RequestParser {

    private final HashMap<String, String> request;
    final String uri;
    final String body;

    RequestParser(HashMap<String, String> request) {
        this.request = request;
        this.uri = request.get("uri");
        this.body = request.get("body");
    }

    public boolean isRootRequest() {
        return request.get("method").equals("GET") && request.get("uri").equals("/");
    }

    public boolean isGetDirectoryFile() {
        File thisFile = new File(System.getProperty("user.dir") + "/public" + uri);
        return request.get("method").equals("GET") &&
                // need to specifically exclude this file to make order irrelevant for status parsing,
                // since get partial content should return a 206, rather than a 200;
                // probably also relevant for body parsing - only want to return specified range, not whole file;
                !request.get("uri").equals("/partial_content.txt") &&
                Arrays.asList(MyFileReader.readDirectoryContents()).contains(thisFile);
    }

    public boolean isMethodOptions() {
        return request.get("method").equals("GET") && request.get("uri").equals("/method_options");
    }

    public boolean isParameters() {
        return request.get("method").equals("GET") && request.get("uri").contains("/parameters");
    }

    public boolean isGetPartialContent() {
        return request.get("method").equals("GET") && request.get("uri").equals("/partial_content.txt");
    }

    public boolean isRedirect() {
        return request.get("method").equals("GET") && request.get("uri").equals("/redirect");
    }

    public boolean isUnauthorized() {
        return request.get("method").equals("GET") && request.get("uri").equals("/logs") &&
                !request.containsKey("authorization");
    }

    public boolean isPatchRequest() {
        return request.get("method").equals("PATCH") && request.get("uri").equals("/patch-content.txt");
    }

    public boolean isNotAllowed() {
        String requestMethod = request.get("method");
        return (requestMethod.equals("PUT") || requestMethod.equals("POST") || requestMethod.equals("DELETE")) &&
                !request.get("uri").equals("/form");
    }

    public boolean isEditForm() {
        String requestMethod = request.get("method");
        return (requestMethod.equals("PUT") || requestMethod.equals("POST")) && request.get("uri").equals("/form");
    }

    public boolean isDeleteForm() {
        return request.get("method").equals("DELETE") && request.get("uri").equals("/form");
    }

    public String getRange() {
        return request.get("range");
    }
}