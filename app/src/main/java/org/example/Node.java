package org.example;

public class Node implements Comparable<Node>{
    Character character;
    int frequency;
    Node left;
    Node right;

    public Node(Character character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public Node(Character character, int frequency, Node left, Node right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node other) {
        return this.frequency - other.frequency;
    }
}
