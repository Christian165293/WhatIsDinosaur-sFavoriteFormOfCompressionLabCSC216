package org.example;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanCodingTest {

    @Test
    void testBuildFrequencyTable() {
        String text = "aabbbcd";
        Map<Character, Integer> frequencyTable = HuffmanCoding.buildFrequencyTable(text);

        assertEquals(2, frequencyTable.get('a'));
        assertEquals(3, frequencyTable.get('b'));
        assertEquals(1, frequencyTable.get('c'));
        assertEquals(1, frequencyTable.get('d'));
        assertEquals(4, frequencyTable.size());
    }

    @Test
    void testBuildFrequencyTableEmptyString() {
        Map<Character, Integer> frequencyTable = HuffmanCoding.buildFrequencyTable("");
        assertTrue(frequencyTable.isEmpty());
    }

    @Test
    void testBuildHuffmanTree() {
        Map<Character, Integer> frequencyTable = Map.of(
                'a', 5,
                'b', 9,
                'c', 12,
                'd', 13,
                'e', 16,
                'f', 45
        );

        Node root = HuffmanCoding.buildHuffmanTree(frequencyTable);
        assertNotNull(root);
        assertEquals(100, root.frequency); // Sum of all frequencies
    }

    @Test
    void testBuildCodeTable() {
        // Build a simple tree: root -> (left: 'a', right: 'b')
        Node left = new Node('a', 1);
        Node right = new Node('b', 1);
        Node root = new Node(null, 2, left, right);

        Map<Character, String> codeTable = HuffmanCoding.buildCodeTable(root);
        assertEquals(2, codeTable.size());
        assertEquals("0", codeTable.get('a'));
        assertEquals("1", codeTable.get('b'));
    }

    @Test
    void testCompress() {
        String text = "abracadabra";
        CompressedResult result = HuffmanCoding.compress(text);

        assertNotNull(result);
        assertFalse(result.frequencyTable.isEmpty());
        assertFalse(result.codeTable.isEmpty());
        assertFalse(result.compressedBits.isEmpty());

        // Verify we can decompress back to original
        String decompressed = HuffmanCoding.decompress(result.compressedBits, result.frequencyTable);
        assertEquals(text, decompressed);
    }

    @Test
    void testCompressEmptyString() {
        CompressedResult result = HuffmanCoding.compress("");
        assertTrue(result.frequencyTable.isEmpty());
        assertTrue(result.codeTable.isEmpty());
        assertEquals("0", result.compressedBits);
    }

    @Test
    void testDecompressEmptyInput() {
        String decompressed = HuffmanCoding.decompress("", Map.of());
        assertEquals("", decompressed);
    }

}
