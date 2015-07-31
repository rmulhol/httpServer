package com.httpServer.Handlers.FileIO;

import org.junit.Test;

import static org.junit.Assert.*;

public class PartialContentAssemblerTest {

    @Test
    public void partialContentAssemblerExists() {
        assertNotNull(new PartialContentAssembler());
    }

    @Test
    public void assemblesPartialContentSpecifiedByPassedRange() {
        assertArrayEquals("This ".getBytes(), PartialContentAssembler.assemble("0-4"));
    }

    @Test
    public void assemblesPartialContentWithOnlyFirstBound() {
        byte[] expectedContent = " 206.\n".getBytes();
        assertArrayEquals(expectedContent, PartialContentAssembler.assemble("71-"));
    }

    @Test
    public void assemblesPartialContentWithOnlyLastBound() {
        assertArrayEquals(" 206.\n".getBytes(), PartialContentAssembler.assemble("-6"));
    }
}