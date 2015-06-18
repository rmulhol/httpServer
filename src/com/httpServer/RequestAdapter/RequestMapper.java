package com.httpServer.RequestAdapter;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestMapper {

    public static HashMap<String, String> map(String request) {
        HashMap<String, String> requestMap = new HashMap<String, String>();
        String[] requestArray = request.split("\r\n");
        String[] requestLine = requestArray[0].split(" ");
        requestMap.put("method", requestLine[0]);
        requestMap.put("uri", requestLine[1]);
        requestMap.put("body", requestArray[requestArray.length - 1]);
        if (request.contains("Range")) {
            Pattern rangeLinePattern = Pattern.compile("(Range).+");
            Matcher rangeLineMatcher = rangeLinePattern.matcher(request);
            rangeLineMatcher.find();
            String rangeLine = rangeLineMatcher.group();
            String[] rangeLineParts = rangeLine.split("=");
            requestMap.put("range", rangeLineParts[1]);
        }
        if (request.contains("Authorization")) {
            requestMap.put("authorization", "true");
        }
        return requestMap;
    }

}
