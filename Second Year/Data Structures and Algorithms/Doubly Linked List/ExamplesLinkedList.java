/*
 * ExamplesLinkedList.java
 *
 * Author: Brendon Peters
 * Submitted on: 9/13/2024
 *
 * Academic Honesty Declaration:
 *
 * The following code represents my own work and I have neither received nor given assistance
 * that violates the collaboration policy posted with this assignment. I have not copied or modified code
 * from any other source other than the homework assignment, course textbook, or course lecture slides.
 * Any unauthorized collaboration or use of materials not permitted will be subjected to academic integrity policies of
 * WPI and CS 2223.
 *
 * I acknowledge that this homework assignment is based upon an assignment created by WPI and that any publishing or
 * posting of this code is prohibited unless I receive written permission from WPI.
 */
package wpialgs.hw02;

import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class provides test cases for our homework 2's {@link DoublyLinkedList} and {@link MoreListMethods}.
 *
 * @version 1.0
 */
public class ExamplesLinkedList {

    // Fields
    public List<String> EMPTY_LIST;

    // Constructor
    public ExamplesLinkedList() {
        EMPTY_LIST = Collections.emptyList();
    }

    // ===========================================================
    // Private Helper Methods
    // ===========================================================

    /**
     * A helper method that gets the string representation of the chained {@link TwoWayNode}s.
     *
     * @param start
     *            The starting node
     *
     * @return A string representation of the {@link TwoWayNode} list. The list remains the same.
     */
    private String nodeListAsString(TwoWayNode<String> start) {
        StringBuilder sb = new StringBuilder();

        // Traverse doubly-linked list and build a string
        sb.append("[");
        TwoWayNode<String> travNode = start;
        while (travNode != null) {
            sb.append(travNode.getDataVal());
            if (travNode.getNext() != null) {
                sb.append(", ");
            }

            travNode = travNode.getNext();
        }
        sb.append("]");

        return sb.toString();
    }

    // ===========================================================
    // JUnit Test Methods
    // ===========================================================

    // -----------------------------------------------------------
    // Constructor (Given)
    // -----------------------------------------------------------

    /**
     * Testing the constructor and checking we have an empty list.
     */
    @Test
    public void testConstructor() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        assertEquals(EMPTY_LIST.toString(), linkedList.toString());
        assertEquals(0, linkedList.getListLength());
    }

    // -----------------------------------------------------------
    // getHeadOfList (Given)
    // -----------------------------------------------------------

    /**
     * Testing {@link ILinkedList#getHeadOfList()} and checking that it returns {@code null} when the list is empty.
     */
    @Test
    public void testGetHeadOfListEmpty() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        assertNull(linkedList.getHeadOfList());
        assertEquals(EMPTY_LIST.toString(), linkedList.toString());
        assertEquals(0, linkedList.getListLength());
    }

    /**
     * Testing {@link ILinkedList#getHeadOfList()} and checking we have the correct node when the list is not empty.
     */
    @Test
    public void testGetHeadOfListNonEmpty() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");

        // Building expected output
        // 1. Create all 3 nodes
        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");

        // 2. Link by next node
        node1.insertAfter(node2);
        node2.insertAfter(node3);

        // 3. Link by prev node
        node2.insertBefore(node1);
        node3.insertBefore(node2);

        assertEquals(node1, linkedList.getHeadOfList());
        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(3, linkedList.getListLength());
    }

    // -----------------------------------------------------------
    // getTailOfList (Given)
    // -----------------------------------------------------------

    /**
     * Testing {@link ILinkedList#getTailOfList()} and checking that it returns {@code null} when the list is empty.
     */
    @Test
    public void testGetTailOfListEmpty() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        assertNull(linkedList.getTailOfList());
        assertEquals(EMPTY_LIST.toString(), linkedList.toString());
        assertEquals(0, linkedList.getListLength());
    }

    /**
     * Testing {@link ILinkedList#getTailOfList()} and checking we have the correct node when the list is not empty.
     */
    @Test
    public void testGetTailOfListNonEmpty() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");

        // Building expected output
        // 1. Create all 3 nodes
        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");

        // 2. Link by next node
        node1.insertAfter(node2);
        node2.insertAfter(node3);

        // 3. Link by prev node
        node2.insertBefore(node1);
        node3.insertBefore(node2);

        assertEquals(node3, linkedList.getTailOfList());
        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(3, linkedList.getListLength());
    }

    // -----------------------------------------------------------
    // isEmpty (Given)
    // -----------------------------------------------------------

    /**
     * Testing {@link ILinkedList#isEmpty()} and checking that it returns {@code true} when the list is empty.
     */
    @Test
    public void testIsEmptyListEmpty() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        assertTrue(linkedList.isEmpty());
        assertEquals(EMPTY_LIST.toString(), linkedList.toString());
        assertEquals(0, linkedList.getListLength());
    }

    /**
     * Testing {@link ILinkedList#isEmpty()} and checking that it returns {@code false} when the list is not empty.
     */
    @Test
    public void testIsEmptyListNonEmpty() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");

        // Building expected output
        // 1. Create all 3 nodes
        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");

        // 2. Link by next node
        node1.insertAfter(node2);
        node2.insertAfter(node3);

        // 3. Link by prev node
        node2.insertBefore(node1);
        node3.insertBefore(node2);

        assertFalse(linkedList.isEmpty());
        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(3, linkedList.getListLength());
    }

    // -----------------------------------------------------------
    // getListLength (Given)
    // -----------------------------------------------------------

    /**
     * Testing {@link ILinkedList#getListLength()} and checking that it returns {@code 0} when the list is empty.
     */
    @Test
    public void testGetListLengthEmpty() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        assertEquals(EMPTY_LIST.toString(), linkedList.toString());
        assertEquals(0, linkedList.getListLength());
    }

    /**
     * Testing {@link ILinkedList#getListLength()} and checking that it returns {@code 3} when the list has 3 items.
     */
    @Test
    public void testGetListLengthNonEmpty() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");

        // Building expected output
        // 1. Create all 3 nodes
        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");

        // 2. Link by next node
        node1.insertAfter(node2);
        node2.insertAfter(node3);

        // 3. Link by prev node
        node2.insertBefore(node1);
        node3.insertBefore(node2);

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(3, linkedList.getListLength());
    }

    // -----------------------------------------------------------
    // insertAtHead
    // -----------------------------------------------------------
    @Test
    public void headEmpty(){
            ILinkedList<String> linkedList = new DoublyLinkedList<>();
            linkedList.insertAtHead("A");
            assertFalse(linkedList.isEmpty());
            assertEquals("A", linkedList.getHeadOfList().getDataVal());
    }

    @Test
    public void headNotEmpty(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtHead("A");
        linkedList.insertAtHead("B");
        linkedList.insertAtHead("C");
        assertFalse(linkedList.isEmpty());
        assertEquals("C", linkedList.getHeadOfList().getDataVal());
    }
    // -----------------------------------------------------------
    // insertAtTail
    // -----------------------------------------------------------
    @Test
    public void TailEmpty(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        assertFalse(linkedList.isEmpty());
        assertEquals("A", linkedList.getTailOfList().getDataVal());
    }

    @Test
    public void TailNotEmpty(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        assertFalse(linkedList.isEmpty());
        assertEquals("C", linkedList.getTailOfList().getDataVal());
    }
    // -----------------------------------------------------------
    // clearList
    // -----------------------------------------------------------
    @Test
    public void clearEmpty(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.clearList();
        assertEquals(0,linkedList.getListLength());
    }

    @Test
    public void clearNotEmpty(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.clearList();
        assertEquals(0,linkedList.getListLength());
        assertNull(linkedList.getHeadOfList());
    }
    // -----------------------------------------------------------
    // findFirstOccurrenceNode
    // -----------------------------------------------------------
    @Test
    public void emptyFirstO(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        assertNull(linkedList.findFirstOccurrenceNode("A"));
    }

    @Test
    public void notEmptyFirstO(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("B");
        TwoWayNode<String> node2 = new TwoWayNode<>("A");
        TwoWayNode<String> node3 = new TwoWayNode<>("B");
        TwoWayNode<String> node4 = new TwoWayNode<>("A");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node2), nodeListAsString(linkedList.findFirstOccurrenceNode("A")));
    }

    // -----------------------------------------------------------
    // findLastOccurrenceNode
    // -----------------------------------------------------------
    @Test
    public void emptyLastO(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        assertNull(linkedList.findFirstOccurrenceNode("A"));
    }

    @Test
    public void notEmptyLastO(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("B");
        TwoWayNode<String> node2 = new TwoWayNode<>("A");
        TwoWayNode<String> node3 = new TwoWayNode<>("B");
        TwoWayNode<String> node4 = new TwoWayNode<>("A");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node4), nodeListAsString(linkedList.findLastOccurrenceNode("A")));
    }
    // -----------------------------------------------------------
    // replaceFirstOccurrenceNode
    // -----------------------------------------------------------
    @Test
    public void emptyRepFir(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.replaceFirstOccurrenceNode("A","B");
        assertEquals(0, linkedList.getListLength());
        assertNull(linkedList.getHeadOfList());
    }

    @Test
    public void nEmptyRepFir(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("B");
        TwoWayNode<String> node2 = new TwoWayNode<>("Z");
        TwoWayNode<String> node3 = new TwoWayNode<>("B");
        TwoWayNode<String> node4 = new TwoWayNode<>("A");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.replaceFirstOccurrenceNode("A", "Z");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node2), nodeListAsString(linkedList.findFirstOccurrenceNode("Z")));
    }
    // -----------------------------------------------------------
    // replaceLastOccurrenceNode
    // -----------------------------------------------------------
    @Test
    public void emptyRepLas(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.replaceFirstOccurrenceNode("A","B");
        assertEquals(0, linkedList.getListLength());
        assertNull(linkedList.getHeadOfList());
    }

    @Test
    public void nEmptyRepLas(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("B");
        TwoWayNode<String> node2 = new TwoWayNode<>("A");
        TwoWayNode<String> node3 = new TwoWayNode<>("B");
        TwoWayNode<String> node4 = new TwoWayNode<>("Z");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.replaceLastOccurrenceNode("A", "Z");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node4), nodeListAsString(linkedList.findLastOccurrenceNode("Z")));
    }
    // -----------------------------------------------------------
    // insertAfterFirstOccurrence
    // -----------------------------------------------------------
    @Test
    public void emptyInsAftFir(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAfterFirstOccurrence("A","B");
        assertEquals(0, linkedList.getListLength());
        assertNull(linkedList.getHeadOfList());
    }

    @Test
    public void insAftFirHead(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("B");
        TwoWayNode<String> node2 = new TwoWayNode<>("A");
        TwoWayNode<String> node3 = new TwoWayNode<>("B");
        TwoWayNode<String> node4 = new TwoWayNode<>("A");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertAfterFirstOccurrence("B","A");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node2), nodeListAsString(linkedList.findFirstOccurrenceNode("A")));
    }

    @Test
    public void insAftFirTail(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("D");

        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("D");
        TwoWayNode<String> node5 = new TwoWayNode<>("E");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertAfterFirstOccurrence(linkedList.getTailOfList().getDataVal(),"E");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node5), nodeListAsString(linkedList.findFirstOccurrenceNode("E")));
    }

    @Test
    public void insAftFirMid(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("E");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertAfterFirstOccurrence("C","E");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node4), nodeListAsString(linkedList.findFirstOccurrenceNode("E")));
    }
    // -----------------------------------------------------------
    // insertAfterLastOccurrence
    // -----------------------------------------------------------
    @Test
    public void emptyInsAftLas(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAfterLastOccurrence("A","B");
        assertEquals(0, linkedList.getListLength());
        assertNull(linkedList.getHeadOfList());
    }

    @Test
    public void insAftLasHead(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("B");
        TwoWayNode<String> node2 = new TwoWayNode<>("A");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("A");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertAfterLastOccurrence("B","A");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node2), nodeListAsString(linkedList.findFirstOccurrenceNode("A")));
    }

    @Test
    public void insAftLasTail(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("D");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("D");

        TwoWayNode<String> node1 = new TwoWayNode<>("D");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("D");
        TwoWayNode<String> node5 = new TwoWayNode<>("E");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertAfterLastOccurrence("D","E");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node5), nodeListAsString(linkedList.getTailOfList()));
    }

    @Test
    public void insAftLasMid(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("B");
        TwoWayNode<String> node4 = new TwoWayNode<>("E");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertAfterLastOccurrence("B","E");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node4), nodeListAsString(linkedList.findFirstOccurrenceNode("E")));
    }
    // -----------------------------------------------------------
    // insertBeforeFirstOccurrence
    // -----------------------------------------------------------
    @Test
    public void emptyInsBefFir(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertBeforeFirstOccurrence("A","B");
        assertEquals(0, linkedList.getListLength());
        assertNull(linkedList.getHeadOfList());
    }

    @Test
    public void insBefFirHead(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("A");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertBeforeLastOccurrence("B","A");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node1), nodeListAsString(linkedList.findFirstOccurrenceNode("A")));
    }

    @Test
    public void insBefFirTail(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("P");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("D");

        TwoWayNode<String> node1 = new TwoWayNode<>("P");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("E");
        TwoWayNode<String> node5 = new TwoWayNode<>("D");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertBeforeFirstOccurrence("D","E");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node4), nodeListAsString(linkedList.findFirstOccurrenceNode("E")));
    }

    @Test
    public void insBefFirMid(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("E");
        TwoWayNode<String> node3 = new TwoWayNode<>("B");
        TwoWayNode<String> node4 = new TwoWayNode<>("B");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertBeforeFirstOccurrence("B","E");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node2), nodeListAsString(linkedList.findFirstOccurrenceNode("E")));
    }
    // -----------------------------------------------------------
    // insertBeforeLastOccurrence
    // -----------------------------------------------------------
    @Test
    public void emptyInsBefLas(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertBeforeLastOccurrence("A","B");
        assertEquals(0, linkedList.getListLength());
        assertNull(linkedList.getHeadOfList());
    }

    @Test
    public void insBefLasHead(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("A");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertBeforeLastOccurrence("B","A");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node1), nodeListAsString(linkedList.findFirstOccurrenceNode("A")));
    }

    @Test
    public void insBefLasTail(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("D");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("D");

        TwoWayNode<String> node1 = new TwoWayNode<>("D");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("E");
        TwoWayNode<String> node5 = new TwoWayNode<>("D");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertBeforeLastOccurrence("D","E");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node4), nodeListAsString(linkedList.findFirstOccurrenceNode("E")));
    }

    @Test
    public void insBefLasMid(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");

        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("E");
        TwoWayNode<String> node4 = new TwoWayNode<>("B");
        TwoWayNode<String> node5 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);
        node4.insertAfter(node5);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);
        node5.insertBefore(node4);

        linkedList.insertBeforeLastOccurrence("B","E");

        assertEquals(nodeListAsString(node1), linkedList.toString());
        assertEquals(nodeListAsString(node3), nodeListAsString(linkedList.findFirstOccurrenceNode("E")));
    }
    // -----------------------------------------------------------
    // removeFirstOccurrenceNode
    // -----------------------------------------------------------
    @Test
    public void remFirHave(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("B");


        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("C");
        TwoWayNode<String> node3 = new TwoWayNode<>("B");

        node1.insertAfter(node2);
        node2.insertAfter(node3);

        node2.insertBefore(node1);
        node3.insertBefore(node2);

        linkedList.removeFirstOccurrenceNode("B");

        assertEquals(nodeListAsString(node1), linkedList.toString());
    }

    @Test
    public void remFirNotHave(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("B");


        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("B");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);

        linkedList.removeFirstOccurrenceNode("M");

        assertEquals(nodeListAsString(node1), linkedList.toString());
    }
    // -----------------------------------------------------------
    // removeLastOccurrenceNode
    // -----------------------------------------------------------
    @Test
    public void remLastHave(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("B");


        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");

        node1.insertAfter(node2);
        node2.insertAfter(node3);

        node2.insertBefore(node1);
        node3.insertBefore(node2);

        linkedList.removeLastOccurrenceNode("B");

        assertEquals(nodeListAsString(node1), linkedList.toString());
    }

    @Test
    public void remLastNotHave(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("A");
        linkedList.insertAtTail("B");
        linkedList.insertAtTail("C");
        linkedList.insertAtTail("B");


        TwoWayNode<String> node1 = new TwoWayNode<>("A");
        TwoWayNode<String> node2 = new TwoWayNode<>("B");
        TwoWayNode<String> node3 = new TwoWayNode<>("C");
        TwoWayNode<String> node4 = new TwoWayNode<>("B");

        node1.insertAfter(node2);
        node2.insertAfter(node3);
        node3.insertAfter(node4);

        node2.insertBefore(node1);
        node3.insertBefore(node2);
        node4.insertBefore(node3);

        linkedList.removeLastOccurrenceNode("M");

        assertEquals(nodeListAsString(node1), linkedList.toString());
    }
    // -----------------------------------------------------------
    // findMin
    // -----------------------------------------------------------
    @Test
    public void findMin(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("3");
        linkedList.insertAtTail("2");
        linkedList.insertAtTail("1");
        linkedList.insertAtTail("4");

        assertEquals("1",MoreListMethods.findMin(linkedList) );
    }
    // -----------------------------------------------------------
    // findMax
    // -----------------------------------------------------------
    @Test
    public void findMax(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("3");
        linkedList.insertAtTail("2");
        linkedList.insertAtTail("4");
        linkedList.insertAtTail("1");

        assertEquals("4",MoreListMethods.findMax(linkedList) );
    }
    // -----------------------------------------------------------
    // findMiddleNode
    // -----------------------------------------------------------
    @Test
    public void findMidOdd(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("1");
        linkedList.insertAtTail("2");
        linkedList.insertAtTail("3");

        assertEquals("2",MoreListMethods.findMiddleNode(linkedList).getDataVal() );
    }

    @Test
    public void findMidEven(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("1");
        linkedList.insertAtTail("2");
        linkedList.insertAtTail("3");
        linkedList.insertAtTail("4");

        assertEquals("3",MoreListMethods.findMiddleNode(linkedList).getDataVal());
    }
    // -----------------------------------------------------------
    // createDuplicateList
    // -----------------------------------------------------------
    @Test
    public void dupeTest(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("1");
        linkedList.insertAtTail("2");
        linkedList.insertAtTail("3");
        linkedList.insertAtTail("4");
        ILinkedList<String> dupe = MoreListMethods.createDuplicateList(linkedList);
        assertNotEquals(linkedList.hashCode(),dupe.hashCode());
    }
    // -----------------------------------------------------------
    // concatenateLists
    // -----------------------------------------------------------
    @Test
    public void concatTest(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("1");
        linkedList.insertAtTail("2");
        linkedList.insertAtTail("3");
        linkedList.insertAtTail("4");

        ILinkedList<String> linkedList2 = new DoublyLinkedList<>();
        linkedList2.insertAtTail("5");
        linkedList2.insertAtTail("6");
        linkedList2.insertAtTail("7");
        linkedList2.insertAtTail("8");

        ILinkedList<String> linkedList3 = new DoublyLinkedList<>();
        linkedList3.insertAtTail("1");
        linkedList3.insertAtTail("2");
        linkedList3.insertAtTail("3");
        linkedList3.insertAtTail("4");
        linkedList3.insertAtTail("5");
        linkedList3.insertAtTail("6");
        linkedList3.insertAtTail("7");
        linkedList3.insertAtTail("8");


        ILinkedList<String> concat = MoreListMethods.concatenateLists(linkedList,linkedList2);
        assertEquals(linkedList3.toString(),concat.toString());
    }
    // -----------------------------------------------------------
    // mergeSortedLists
    // -----------------------------------------------------------

    @Test
    public void mergeTest(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("1");
        linkedList.insertAtTail("3");
        linkedList.insertAtTail("5");

        ILinkedList<String> linkedList2 = new DoublyLinkedList<>();
        linkedList2.insertAtTail("2");
        linkedList2.insertAtTail("4");
        linkedList2.insertAtTail("6");

        ILinkedList<String> linkedList3 = new DoublyLinkedList<>();
        linkedList3.insertAtTail("1");
        linkedList3.insertAtTail("2");
        linkedList3.insertAtTail("3");
        linkedList3.insertAtTail("4");
        linkedList3.insertAtTail("5");
        linkedList3.insertAtTail("6");


        ILinkedList<String> merged = MoreListMethods.mergeSortedLists(linkedList, linkedList2);
        assertEquals(linkedList3.toString(),merged.toString());
    }

    @Test
    public void mergeTest2(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("1");
        linkedList.insertAtTail("3");
        linkedList.insertAtTail("5");

        ILinkedList<String> linkedList2 = new DoublyLinkedList<>();


        ILinkedList<String> linkedList3 = new DoublyLinkedList<>();
        linkedList3.insertAtTail("1");
        linkedList3.insertAtTail("3");
        linkedList3.insertAtTail("5");


        ILinkedList<String> merged = MoreListMethods.mergeSortedLists(linkedList, linkedList2);
        assertEquals(linkedList3.toString(),merged.toString());
    }

    @Test
    public void mergeTest3(){
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("3");
        ILinkedList<String> linkedList2 = new DoublyLinkedList<>();
        linkedList2.insertAtTail("1");

        ILinkedList<String> linkedList3 = new DoublyLinkedList<>();
        linkedList3.insertAtTail("1");
        linkedList3.insertAtTail("3");



        ILinkedList<String> merged = MoreListMethods.mergeSortedLists(linkedList, linkedList2);
        assertEquals(linkedList3.toString(),merged.toString());
    }



    // -----------------------------------------------------------
    // reverseList
    // -----------------------------------------------------------

    @Test
    public void reverseTest() {
        ILinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.insertAtTail("1");
        linkedList.insertAtTail("2");
        linkedList.insertAtTail("3");

        ILinkedList<String> linkedList2 = new DoublyLinkedList<>();
        linkedList2.insertAtTail("3");
        linkedList2.insertAtTail("2");
        linkedList2.insertAtTail("1");

        ILinkedList<String> reved = MoreListMethods.reverseList(linkedList);
        assertEquals(linkedList2.toString(), reved.toString());
    }

}