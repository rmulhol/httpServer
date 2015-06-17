package com.httpServer;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FileWritingHandlerTest {

    @Test
    public void setFileWritesBodyToFormIfPutFormRequest() throws IOException {
        HashMap<String, String> putFormRequest = new HashMap<String, String>();
        putFormRequest.put("method", "PUT");
        putFormRequest.put("uri", "/form");
        putFormRequest.put("body", "test content");

        HashMap<String, byte[]> putFormResponse = new HashMap<String, byte[]>();

        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
        new FileWritingHandler(putFormRequest, putFormResponse).setFile();
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"\n");
        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void setFileWritesBodyToFormIfPostFormRequest() throws IOException {
        HashMap<String, String> postFormRequest = new HashMap<String, String>();
        postFormRequest.put("method", "POST");
        postFormRequest.put("uri", "/form");
        postFormRequest.put("body", "test content");

        HashMap<String, byte[]> postFormResponse = new HashMap<String, byte[]>();

        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
        new FileWritingHandler(postFormRequest, postFormResponse).setFile();
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"\n");
        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void setFileDeletesFormContentsIfDeleteFormRequest() {
        HashMap<String, String> deleteFormRequest = new HashMap<String, String>();
        deleteFormRequest.put("method", "DELETE");
        deleteFormRequest.put("uri", "/form");

        HashMap<String, byte[]> deleteFormResponse = new HashMap<String, byte[]>();

        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
        new FileWritingHandler(deleteFormRequest, deleteFormResponse).setFile();
        assertArrayEquals("\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"\n");
        assertArrayEquals("\"My\"=\"Data\"\n\n".getBytes(), MyFileReader.readFileContents("/form"));
    }


    @Test
    public void setFileDoesNothingIfNotAValidWriteRequest() {
        HashMap<String, String> putFile1Request = new HashMap<String, String>();
        putFile1Request.put("method", "PUT");
        putFile1Request.put("uri", "/file1");
        putFile1Request.put("body", "Illegal attempt");

        HashMap<String, byte[]> putFile1Response = new HashMap<String, byte[]>();

        assertArrayEquals("file1 contents".getBytes(), MyFileReader.readFileContents("/file1"));
        new FileWritingHandler(putFile1Request, putFile1Response).setFile();
        assertArrayEquals("file1 contents".getBytes(), MyFileReader.readFileContents("/file1"));
    }

}