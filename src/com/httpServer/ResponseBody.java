package com.httpServer;

import java.io.File;

public class ResponseBody {

    public static byte[] publicDirectoryContents() {
        String fileLinks = "";
        File[] listOfFiles = MyFileReader.readDirectoryContents();
        for (File file : listOfFiles)
            fileLinks += fileLink(file);
        return fileLinks.getBytes();
    }

    public static byte[] fileContents(String filePath) {
        return MyFileReader.readFileContents(filePath);
    }

    public static byte[] noBody() {
        return "".getBytes();
    }

    public static byte[] parameters(String uri) {
        return ParameterDecoder.decode(uri).getBytes();
    }

    public static byte[] partialContent(String range) {
        return PartialContentAssembler.assemble(range);
    }

    public static byte[] authenticationRequired() {
        return "Authentication required".getBytes();
    }

    private static String fileLink(File file) {
        String fileName = file.getName();
        return "<p><a href='/" + fileName + "'>" + fileName + "</a></p>\r\n";
    }
}
