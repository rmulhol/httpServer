package com.httpServer;

import java.util.*;

class ParameterDecoder {

    public static String decode(String parameters) {
        String out = parameters.replace("=", " = ");
        Set<Map.Entry<String, String>> codeConversions = symbolCodeConversions();
        for(Map.Entry<String, String> entry : codeConversions) {
            out = out.replace(entry.getKey(), entry.getValue());
        }
        return out;
    }

    private static Set<Map.Entry<String, String>> symbolCodeConversions() {
        Set<Map.Entry<String, String>> codeConversions = new HashSet<Map.Entry<String, String>>();
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%20", " "));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%2C", ","));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%3C", "<"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%3E", ">"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%3D", "="));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%3B", ";"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%2B", "+"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%26", "&"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%40", "@"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%23", "#"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%24", "$"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%5B", "["));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%5D", "]"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%3A", ":"));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%22", "\""));
        codeConversions.add(new AbstractMap.SimpleImmutableEntry<String, String>("%3F", "?"));
        return Collections.unmodifiableSet(codeConversions);
    }
}
