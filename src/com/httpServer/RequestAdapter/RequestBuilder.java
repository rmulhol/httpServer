package com.httpServer.RequestAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestBuilder {

    public static Request buildRequest(String rawRequest) {
        Request request = new Request();
        String[] requestArray = parseRequestArray(rawRequest);
        String[] requestLine = parseRequestLine(requestArray);
        String[] uri_with_parameters = requestLine[1].split("\\?");

        setMethod(requestLine[0], request);
        setUri(uri_with_parameters[0], request);
        setParameters(uri_with_parameters, request);
        setBody(requestArray[requestArray.length - 1], request);

        setRange(rawRequest, request);
        setAuthorization(rawRequest, request);

        return request;
    }

    private static String[] parseRequestArray(String request) {
        return request.split("\r\n");
    }

    private static String[] parseRequestLine(String[] requestArray) {
        return requestArray[0].split(" ");
    }

    private static void setMethod(String method, Request request) {
        request.setMethod(method);
    }

    private static void setUri(String uri, Request request) {
        request.setUri(uri);
    }

    private static void setParameters(String[] uriWithParameters, Request request) {
        if (uriWithParameters.length > 1) {
            request.setParameters(uriWithParameters[1]);
        }
    }

    private static void setBody(String body, Request request) {
        request.setBody(body);
    }

    private static void setRange(String rawRequest, Request request) {
        if (rawRequest.contains("Range")) {
            Pattern rangeLinePattern = Pattern.compile("(Range).+");
            Matcher rangeLineMatcher = rangeLinePattern.matcher(rawRequest);
            rangeLineMatcher.find();
            String rangeLine = rangeLineMatcher.group();
            String[] rangeLineParts = rangeLine.split("=");
            request.setRange(rangeLineParts[1]);
        }
    }

    private static void setAuthorization(String rawRequest, Request request) {
        if (rawRequest.contains("Authorization")) {
            request.setAuthorization();
        }
    }
}
