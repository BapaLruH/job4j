package ru.job4j.list;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NodeTest {

    @Test
    public void whenNodeHasLoopFourAndOne() {
        Node<Integer> node = new Node<>(1);
        Node<Integer> node1 = new Node<>(2);
        Node<Integer> node2 = new Node<>(3);
        Node<Integer> node3 = new Node<>(4);
        Node<Integer> node4 = new Node<>(5);
        node.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node1);
        assertTrue(node.hasLoop());
    }

    @Test
    public void whenNodeHasLoopThreeAndTwo() {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node1);
        assertTrue(node1.hasLoop());
    }

    @Test
    public void whenNodeNotHasLoop() {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        node1.setNext(node2);
        node2.setNext(node3);
        assertFalse(node1.hasLoop());
    }
}