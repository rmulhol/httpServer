package com.httpServer.RequestAdapter;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestMapper {

    public static HashMap<String, String> map(String request) {
        HashMap<String, String> requestMap = new HashMap<String, String>();
        String[] requestArray = parseRequestArray(request);
        String[] requestLine = parseRequestLine(requestArray);

        setMethod(requestLine[0], requestMap);
        setUri(requestLine[1], requestMap);
        setBody(requestArray[requestArray.length - 1], requestMap);

        setRange(request, requestMap);
        setAuthorization(request, requestMap);

        return requestMap;
    }

    private static String[] parseRequestArray(String request) {
        return request.split("\r\n");
    }

    private static String[] parseRequestLine(String[] requestArray) {
        return requestArray[0].split(" ");
    }

    private static void setMethod(String method, HashMap<String, String> requestMap) {
        requestMap.put("method", method);
    }

    private static void setUri(String uri, HashMap<String, String> requestMap) {
        requestMap.put("uri", uri);
    }

    private static void setBody(String body, HashMap<String, String> requestMap) {
        requestMap.put("body", body);
    }

    private static void setRange(String request, HashMap<String, String> requestMap) {
        if (request.contains("Range")) {
            Pattern rangeLinePattern = Pattern.compile("(Range).+");
            Matcher rangeLineMatcher = rangeLinePattern.matcher(request);
            rangeLineMatcher.find();
            String rangeLine = rangeLineMatcher.group();
            String[] rangeLineParts = rangeLine.split("=");
            requestMap.put("range", rangeLineParts[1]);
        }
    }

    private static void setAuthorization(String request, HashMap<String, String> requestMap) {
        if (request.contains("Authorization")) {
            requestMap.put("authorization", "true");
        }
    }
}
