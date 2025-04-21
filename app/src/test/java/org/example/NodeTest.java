package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testNodeCreation() {
        Node node = new Node('a', 5);
        assertEquals('a', node.character);
        assertEquals(5, node.frequency);
        assertNull(node.left);
        assertNull(node.right);
    }

    @Test
    void testNodeWithChildren() {
        Node left = new Node('a', 2);
        Node right = new Node('b', 3);
        Node parent = new Node(null, 5, left, right);

        assertNull(parent.character);
        assertEquals(5, parent.frequency);
        assertEquals(left, parent.left);
        assertEquals(right, parent.right);
    }

    @Test
    void testIsLeaf() {
        Node leaf = new Node('a', 1);
        assertTrue(leaf.isLeaf());

        Node left = new Node('a', 1);
        Node right = new Node('b', 1);
        Node nonLeaf = new Node(null, 2, left, right);
        assertFalse(nonLeaf.isLeaf());
    }

    @Test
    void testCompareTo() {
        Node node1 = new Node('a', 1);
        Node node2 = new Node('b', 2);
        Node node3 = new Node('c', 1);

        assertTrue(node1.compareTo(node2) < 0);
        assertTrue(node2.compareTo(node1) > 0);
        assertEquals(0, node1.compareTo(node3));
    }
}