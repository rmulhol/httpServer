package com.httpServer;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MyFileReaderTest {

    @Test
    public void testReadDirectoryContentProvidesPublicDirectoryContent() {
        File file1 = new File(System.getProperty("user.dir") + "/public/file1");
        assert(Arrays.asList(MyFileReader.readDirectoryContents()).contains(file1));
    }

    @Test
    public void testReadFileReturnsFileContent() throws Exception {
        String file1Contents = new String(MyFileReader.readFileContents("/file1"));
        assertEquals("file1 contents", file1Contents);
    }

}