package com.httpServer.Handler.ResponseContents;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParameterDecoderTest {

    @Test
    public void testParsesRootRequest() {
        assertEquals("GET / HTTP/1.1", ParameterDecoder.decode("GET / HTTP/1.1"));
    }

    @Test
    public void testParsesSpaceCode() {
        assertEquals("GET /some text HTTP/1.1", ParameterDecoder.decode("GET /some%20text HTTP/1.1"));
    }

    @Test
    public void testParsesCommaCode() {
        assertEquals("GET /some,text HTTP/1.1", ParameterDecoder.decode("GET /some%2Ctext HTTP/1.1"));
    }

    @Test
    public void testParsesLessThanCode() {
        assertEquals("GET /some<text HTTP/1.1", ParameterDecoder.decode("GET /some%3Ctext HTTP/1.1"));
    }

    @Test
    public void testParsesGreaterThanCode() {
        assertEquals("GET /some>text HTTP/1.1", ParameterDecoder.decode("GET /some%3Etext HTTP/1.1"));
    }

    @Test
    public void testParsesEqualsSignCode() {
        assertEquals("GET /some=text HTTP/1.1", ParameterDecoder.decode("GET /some%3Dtext HTTP/1.1"));
    }

    @Test
    public void testAddsSpacesToOnlyEqualsSignLiteral() {
        assertEquals("GET /some = =text HTTP/1.1", ParameterDecoder.decode("GET /some=%3Dtext HTTP/1.1"));
    }

    @Test
    public void testParsesSemicolonCode() {
        assertEquals("GET /some;text HTTP/1.1", ParameterDecoder.decode("GET /some%3Btext HTTP/1.1"));
    }

    @Test
    public void testParsesPlusSignCode() {
        assertEquals("GET /some+text HTTP/1.1", ParameterDecoder.decode("GET /some%2Btext HTTP/1.1"));
    }

    @Test
    public void testParsesAmpersandCode() {
        assertEquals("GET /some&text HTTP/1.1", ParameterDecoder.decode("GET /some%26text HTTP/1.1"));
    }

    @Test
    public void testParsesAtSymbolCode() {
        assertEquals("GET /some@text HTTP/1.1", ParameterDecoder.decode("GET /some%40text HTTP/1.1"));
    }

    @Test
    public void testParsesNumberSignCode() {
        assertEquals("GET /some#text HTTP/1.1", ParameterDecoder.decode("GET /some%23text HTTP/1.1"));
    }

    @Test
    public void testParsesDollarSignCode() {
        assertEquals("GET /some$text HTTP/1.1", ParameterDecoder.decode("GET /some%24text HTTP/1.1"));
    }

    @Test
    public void testParsesOpeningBracketCode() {
        assertEquals("GET /some[text HTTP/1.1", ParameterDecoder.decode("GET /some%5Btext HTTP/1.1"));
    }

    @Test
    public void testParsesClosingBracketCode() {
        assertEquals("GET /some]text HTTP/1.1", ParameterDecoder.decode("GET /some%5Dtext HTTP/1.1"));
    }

    @Test
    public void testParsesColonCode() {
        assertEquals("GET /some:text HTTP/1.1", ParameterDecoder.decode("GET /some%3Atext HTTP/1.1"));
    }

    @Test
    public void testParsesDoubleQuotesCode() {
        assertEquals("GET /some\"text HTTP/1.1", ParameterDecoder.decode("GET /some%22text HTTP/1.1"));
    }

    @Test
    public void testParsesQuestionMarkCode() {
        assertEquals("GET /some?text HTTP/1.1", ParameterDecoder.decode("GET /some%3Ftext HTTP/1.1"));
    }
}