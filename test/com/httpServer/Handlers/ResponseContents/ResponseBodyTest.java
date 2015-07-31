package com.httpServer.Handlers.ResponseContents;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseBodyTest {
    @Test
    public void publicDirectoryLinksReturnsLinksToFilesInPublicDirectory() {
        String directoryContents = "<p><a href='/file1'>file1</a></p>\r\n" +
                "<p><a href='/file2'>file2</a></p>\r\n" +
                "<p><a href='/form'>form</a></p>\r\n" +
                "<p><a href='/image.gif'>image.gif</a></p>\r\n" +
                "<p><a href='/image.jpeg'>image.jpeg</a></p>\r\n" +
                "<p><a href='/image.png'>image.png</a></p>\r\n" +
                "<p><a href='/partial_content.txt'>partial_content.txt</a></p>\r\n" +
                "<p><a href='/patch-content.txt'>patch-content.txt</a></p>\r\n" +
                "<p><a href='/text-file.txt'>text-file.txt</a></p>\r\n";

        assertArrayEquals(directoryContents.getBytes(), ResponseBody.publicDirectoryContents());
    }

    @Test
    public void fileContentsReturnsContentsOfFile() {
        assertArrayEquals("file1 contents".getBytes(), ResponseBody.fileContents("/file1"));
    }

    @Test
    public void noBodyReturnsEmpty() {
        assertArrayEquals("".getBytes(), ResponseBody.noBody());
    }

    @Test
    public void parametersReturnsDecodedParameters() {
        String parametersUri = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C" +
                "%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F" +
                "&variable_2=stuff";

        byte[] expectedOutput = ("/parameters?variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is " +
                "that all\"?&variable_2 = stuff").getBytes();

        assertArrayEquals(expectedOutput, ResponseBody.parameters(parametersUri));
    }

    @Test
    public void partialContentReturnsContentSpecifiedByPassedRange() {
        assertArrayEquals("This ".getBytes(), ResponseBody.partialContent("0-4"));
    }

    @Test
    public void authenticationRequiredReturnsBody() {
        assertArrayEquals("Authentication required".getBytes(), ResponseBody.authenticationRequired());
    }
}