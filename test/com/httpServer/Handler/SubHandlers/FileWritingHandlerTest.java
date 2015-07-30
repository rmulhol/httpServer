package com.httpServer.Handler.SubHandlers;

import com.httpServer.Handler.FileIO.MyFileReader;
import com.httpServer.Handler.FileIO.MyFileWriter;
import com.httpServer.Handler.Route.Route;
import com.httpServer.RequestAdapter.Request;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class FileWritingHandlerTest {

    @Test
    public void setFileWritesBodyToFormIfPutFormRequest() throws IOException {
        Request putFormRequest = new Request();
        putFormRequest.setMethod("PUT");
        putFormRequest.setUri("/form");
        putFormRequest.setBody("test content");
        Route putFormRoute = new Route(putFormRequest);

        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
        new FileWritingHandler(putFormRoute).setFile();
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void setFileWritesBodyToFormIfPostFormRequest() throws IOException {
        Request postFormRequest = new Request();
        postFormRequest.setMethod("POST");
        postFormRequest.setUri("/form");
        postFormRequest.setBody("test content");
        Route postFormRoute = new Route(postFormRequest);

        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
        new FileWritingHandler(postFormRoute).setFile();
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void setFileDeletesFormContentsIfDeleteFormRequest() {
        Request deleteFormRequest = new Request();
        deleteFormRequest.setMethod("DELETE");
        deleteFormRequest.setUri("/form");
        Route deleteFormRoute = new Route(deleteFormRequest);

        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
        new FileWritingHandler(deleteFormRoute).setFile();
        assertArrayEquals("\n".getBytes(), MyFileReader.readFileContents("/form"));
        MyFileWriter.editFile("/public/form", "\"My\"=\"Data\"");
        assertArrayEquals("\"My\"=\"Data\"\n".getBytes(), MyFileReader.readFileContents("/form"));
    }

    @Test
    public void setFilePatchesPatchContentIfPatchRequest() {
        Request patchRequest = new Request();
        patchRequest.setMethod("PATCH");
        patchRequest.setUri("/patch-content.txt");
        patchRequest.setBody("test content");
        Route patchRoute = new Route(patchRequest);

        assertArrayEquals("default content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
        new FileWritingHandler(patchRoute).setFile();
        assertArrayEquals("test content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
        MyFileWriter.editFile("/public/patch-content.txt", "default content");
        assertArrayEquals("default content\n".getBytes(), MyFileReader.readFileContents("/patch-content.txt"));
    }


    @Test
    public void setFileDoesNothingIfNotAValidWriteRequest() {
        Request putFile1Request = new Request();
        putFile1Request.setMethod("PUT");
        putFile1Request.setUri("/file1");
        putFile1Request.setBody("Illegal attempt");
        Route putFile1Route = new Route(putFile1Request);

        assertArrayEquals("file1 contents".getBytes(), MyFileReader.readFileContents("/file1"));
        new FileWritingHandler(putFile1Route).setFile();
        assertArrayEquals("file1 contents".getBytes(), MyFileReader.readFileContents("/file1"));
    }

}