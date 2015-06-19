package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.FileIO.MyFileReader;
import com.httpServer.Handler.FileIO.MyFileWriter;
import com.httpServer.Handler.Route.Route;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;

public class FileWritingHandlerTest {

    @Test
    public void setFileWritesBodyToFormIfPutFormRequest() throws IOException {
        HashMap<String, String> putFormRequest = new HashMap<String, String>();
        putFormRequest.put("method", "PUT");
        putFormRequest.put("uri", "/form");
        putFormRequest.put("body", "test content");
        Route putFormRoute = new Route(putFormRequest);

        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
        new FileWritingHandler(putFormRoute).setFile();
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void setFileWritesBodyToFormIfPostFormRequest() throws IOException {
        HashMap<String, String> postFormRequest = new HashMap<String, String>();
        postFormRequest.put("method", "POST");
        postFormRequest.put("uri", "/form");
        postFormRequest.put("body", "test content");
        Route postFormRoute = new Route(postFormRequest);

        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
        new FileWritingHandler(postFormRoute).setFile();
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void setFileDeletesFormContentsIfDeleteFormRequest() {
        HashMap<String, String> deleteFormRequest = new HashMap<String, String>();
        deleteFormRequest.put("method", "DELETE");
        deleteFormRequest.put("uri", "/form");
        Route deleteFormRoute = new Route(deleteFormRequest);

        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
        new FileWritingHandler(deleteFormRoute).setFile();
        assertArrayEquals("\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void setFilePatchesPatchContentIfPatchRequest() {
        HashMap<String, String> patchRequest = new HashMap<String, String>();
        patchRequest.put("method", "PATCH");
        patchRequest.put("uri", "/patch-content.txt");
        patchRequest.put("body", "test content");
        Route patchRoute = new Route(patchRequest);

        assertArrayEquals("default content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
        new FileWritingHandler(patchRoute).setFile();
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
        MyFileWriter.editFile("/public/patch-content.txt", "default content");
        assertArrayEquals("default content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
    }


    @Test
    public void setFileDoesNothingIfNotAValidWriteRequest() {
        HashMap<String, String> putFile1Request = new HashMap<String, String>();
        putFile1Request.put("method", "PUT");
        putFile1Request.put("uri", "/file1");
        putFile1Request.put("body", "Illegal attempt");
        Route putFile1Route = new Route(putFile1Request);

        assertArrayEquals("file1 contents".getBytes(), MyFileReader.readFileContents("/file1"));
        new FileWritingHandler(putFile1Route).setFile();
        assertArrayEquals("file1 contents".getBytes(), MyFileReader.readFileContents("/file1"));
    }

}