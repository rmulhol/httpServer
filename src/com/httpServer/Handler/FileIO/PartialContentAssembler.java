package com.httpServer.Handler.FileIO;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PartialContentAssembler {

    public static byte[] assemble(String range) {
        int[] rangeArray = parseRange(range);
        return readPartialContent(rangeArray);
    }

    private static int[] parseRange(String range) {
        String[] rangeBoundaries = range.split("-");
        int[] rangeArray = new int[2];
        if(rangeBoundaries.length == 1) {
            rangeArray[0] = Integer.parseInt(rangeBoundaries[0]);
            rangeArray[1] = -1;
        } else {
            try {
                rangeArray[0] = Integer.parseInt(rangeBoundaries[0]);
                rangeArray[1] = Integer.parseInt(rangeBoundaries[1]);
            } catch (NumberFormatException e) {
                rangeArray[0] = -1;
                rangeArray[1] = Integer.parseInt(rangeBoundaries[1]);
            }
        }
        return rangeArray;
    }

    private static byte[] readPartialContent(int[] bounds) {
        File partialContent = new File(System.getProperty("user.dir") + "/public/partial_content.txt");

        if (bounds[1] == -1) {
            return readPartialContentWithFirstBound(partialContent, bounds[0]);
        } else if (bounds[0] == -1) {
            return readPartialContentWithLastBound(partialContent, bounds[1]);
        } else {
            return readPartialContent(partialContent, bounds[0], bounds[1]);
        }
    }

    private static byte[] readPartialContent(File partialContent, long start, long end) {
        try {
            RandomAccessFile partialContentBytes = new RandomAccessFile(partialContent, "r");
            byte[] range = new byte[(int) (end - start + 1)];
            partialContentBytes.seek(start);
            partialContentBytes.read(range);
            return range;
        } catch (IOException e) {
            System.out.println("Server Failure: Couldn't read partial content");
        }
        return new byte[1];
    }

    private static byte[] readPartialContentWithFirstBound(File partialContent, int start) {
        return readPartialContent(partialContent, start, partialContent.length() - 1);
    }

    private static byte[] readPartialContentWithLastBound(File partialContent, int start) {
        return readPartialContent(partialContent, partialContent.length() - start, partialContent.length() - 1);
    }

}
