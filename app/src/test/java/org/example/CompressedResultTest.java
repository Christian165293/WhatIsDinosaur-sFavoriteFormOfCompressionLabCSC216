package org.example;


import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CompressedResultTest {

    @Test
    void testCompressedResultCreation() {
        Map<Character, Integer> freqTable = Map.of('a', 1);
        Map<Character, String> codeTable = Map.of('a', "0");
        String bits = "0";

        CompressedResult result = new CompressedResult(freqTable, codeTable, bits);

        assertEquals(freqTable, result.frequencyTable);
        assertEquals(codeTable, result.codeTable);
        assertEquals(bits, result.compressedBits);
    }

    @Test
    void testCompressedResultWithEmptyData() {
        Map<Character, Integer> freqTable = Map.of();
        Map<Character, String> codeTable = Map.of();
        String bits = "";

        CompressedResult result = new CompressedResult(freqTable, codeTable, bits);

        assertTrue(result.frequencyTable.isEmpty());
        assertTrue(result.codeTable.isEmpty());
        assertTrue(result.compressedBits.isEmpty());
    }
}