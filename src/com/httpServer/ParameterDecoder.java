package com.httpServer;

import java.util.*;

public class ParameterDecoder {

    public static String decode(String parameters) {
        String out = parameters.replace("=", " = ");
        for(Map.Entry<String, String> entry : symbolCodeConversions()) {
            out = out.replace(entry.getKey(), entry.getValue());
        }
        return out;
    }

    private static Set<Map.Entry<String, String>> symbolCodeConversions() {
        HashMap<String, String> symbolCodeConversions = new HashMap<String, String>();
        symbolCodeConversions.put("%20", " ");
        symbolCodeConversions.put("%2C", ",");
        symbolCodeConversions.put("%3C", "<");
        symbolCodeConversions.put("%3E", ">");
        symbolCodeConversions.put("%3D", "=");
        symbolCodeConversions.put("%3B", ";");
        symbolCodeConversions.put("%2B", "+");
        symbolCodeConversions.put("%26", "&");
        symbolCodeConversions.put("%40", "@");
        symbolCodeConversions.put("%23", "#");
        symbolCodeConversions.put("%24", "$");
        symbolCodeConversions.put("%5B", "[");
        symbolCodeConversions.put("%5D", "]");
        symbolCodeConversions.put("%3A", ":");
        symbolCodeConversions.put("%22", "\"");
        symbolCodeConversions.put("%3F", "?");
        return Collections.unmodifiableSet(symbolCodeConversions.entrySet());
    }
}
