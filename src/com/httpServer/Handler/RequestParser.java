package com.httpServer.Handler;

import com.httpServer.Handler.FileIO.MyFileReader;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class RequestParser {

    private final HashMap<String, String> request;

    public RequestParser(HashMap<String, String> request) {
        this.request = request;
    }

    public boolean isRootRequest() {
        return getMethod().equals("GET") && getUri().equals("/");
    }

    public boolean isGetDirectoryFile() {
        File thisFile = new File(System.getProperty("user.dir") + "/public" + getUri());
        return getMethod().equals("GET") &&
                // need to specifically exclude this file to make order irrelevant for status parsing,
                // since get partial content should return a 206, rather than a 200;
                // probably also relevant for body parsing - only want to return specified range, not whole file;
                !getUri().equals("/partial_content.txt") &&
                Arrays.asList(MyFileReader.readDirectoryContents()).contains(thisFile);
    }

    public boolean isMethodOptions() {
        return getMethod().equals("OPTIONS") && getUri().equals("/method_options");
    }

    public boolean isParameters() {
        return getMethod().equals("GET") && getUri().contains("/parameters");
    }

    public boolean isGetPartialContent() {
        return getMethod().equals("GET") && getUri().equals("/partial_content.txt");
    }

    public boolean isRedirect() {
        return getMethod().equals("GET") && getUri().equals("/redirect");
    }

    public boolean isUnauthorized() {
        return getMethod().equals("GET") && getUri().equals("/logs") &&
                !request.containsKey("authorization");
    }

    public boolean isPatchRequest() {
        return getMethod().equals("PATCH") && getUri().equals("/patch-content.txt");
    }

    public boolean isNotAllowed() {
        String requestMethod = getMethod();
        return (requestMethod.equals("PUT") || requestMethod.equals("POST") || requestMethod.equals("DELETE")) &&
                !getUri().equals("/form");
    }

    public boolean isEditForm() {
        String requestMethod = getMethod();
        return (requestMethod.equals("PUT") || requestMethod.equals("POST")) && getUri().equals("/form");
    }

    public boolean isDeleteForm() {
        return getMethod().equals("DELETE") && getUri().equals("/form");
    }

    String getMethod() {
        return request.get("method");
    }

    public String getUri() {
        return request.get("uri");
    }

    public String getBody() {
        return request.get("body");
    }

    public String getRange() {
        return request.get("range");
    }
}