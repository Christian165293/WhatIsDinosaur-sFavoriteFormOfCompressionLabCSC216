package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class HuffmanCoding {
    public static Map<Character, Integer> buildFrequencyTable(String text) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyTable.put(c, frequencyTable.getOrDefault(c, 0) + 1);
        }
        return frequencyTable;
    }

    public static Node buildHuffmanTree(Map<Character, Integer> frequencyTable) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            Node parent = new Node(null, left.frequency + Objects.requireNonNull(right).frequency, left, right);
            priorityQueue.add(parent);
        }

        return priorityQueue.poll();
    }

    public static Map<Character, String> buildCodeTable(Node root) {
        Map<Character, String> codeTable = new HashMap<>();
        buildCodeTableHelper(root, "", codeTable);
        return codeTable;
    }

    private static void buildCodeTableHelper(Node node, String code, Map<Character, String> codeTable) {
        if (node.isLeaf()) {
            if (node.character != null) {
                codeTable.put(node.character, code);
            }
            return;
        }
        buildCodeTableHelper(node.left, code + "0", codeTable);
        buildCodeTableHelper(node.right, code + "1", codeTable);
    }

    public static CompressedResult compress(String text) {
        if (text == null || text.isEmpty()) {
            return new CompressedResult(new HashMap<>(), new HashMap<>(), "0");
        }

        Map<Character, Integer> frequencyTable = buildFrequencyTable(text);
        Node root = buildHuffmanTree(frequencyTable);
        Map<Character, String> codeTable = buildCodeTable(root);

        StringBuilder compressedBits = new StringBuilder();
        for (char c : text.toCharArray()) {
            compressedBits.append(codeTable.get(c));
        }

        return new CompressedResult(frequencyTable, codeTable, compressedBits.toString());
    }

    public static String decompress(String compressedBits, Map<Character, Integer> frequencyTable) {
        if (compressedBits == null || compressedBits.isEmpty() || frequencyTable.isEmpty()) {
            return "";
        }

        Node root = buildHuffmanTree(frequencyTable);
        Node current = root;
        StringBuilder decompressedText = new StringBuilder();

        for (char bit : compressedBits.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.isLeaf()) {
                decompressedText.append(current.character);
                current = root;
            }
        }

        return decompressedText.toString();
    }

}
