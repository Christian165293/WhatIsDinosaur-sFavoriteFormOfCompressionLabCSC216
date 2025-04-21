package org.example;

import java.util.Map;

public class CompressedResult {
    public Map<Character, Integer> frequencyTable;
    public Map<Character, String> codeTable;
    public String compressedBits;

    public CompressedResult(Map<Character, Integer> frequencyTable,
                             Map<Character, String> codeTable,
                             String compressedBits) {
        this.frequencyTable = frequencyTable;
        this.codeTable = codeTable;
        this.compressedBits = compressedBits;
    }
}
