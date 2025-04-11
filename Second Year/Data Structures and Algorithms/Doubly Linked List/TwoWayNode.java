/*
 * TwoWayNode.java
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

/**
 * This is a generic node class that can be used to build a doubly-linked structure.
 *
 * @author Roger Van Scoy
 * @author Yu-Shan Sun
 *
 * @version 2.0
 */
public class TwoWayNode<E> {

    private E dataVal; // Node data
    private TwoWayNode<E> prevNode; // Reference to the previous node
    private TwoWayNode<E> nextNode; // Reference to the next node

    /**
     * This creates a new node with data and no previous or next locations.
     *
     * @param dataInit
     *            Data to be stored at this node.
     */
    public TwoWayNode(E dataInit) {
        this(dataInit, null, null);
    }

    /**
     * This creates a new node with data, previous and next locations.
     *
     * @param dataInit
     *            Data to be stored at this node.
     * @param prevLoc
     *            Previous location
     * @param nextLoc
     *            Next location
     */
    public TwoWayNode(E dataInit, TwoWayNode<E> prevLoc, TwoWayNode<E> nextLoc) {
        this.dataVal = dataInit;
        this.prevNode = prevLoc;
        this.nextNode = nextLoc;
    }

    /**
     * This returns the value stored at this node.
     *
     * @return Node value.
     */
    public E getDataVal() {
        return dataVal;
    }

    /**
     * This updates the value at this node.
     *
     * @param newValue
     *            The new value for this node.
     */
    public void setDataVal(E newValue) {
        dataVal = newValue;
    }

    /**
     * This returns the location pointed by the previous node.
     *
     * @return The previous node.
     */
    public TwoWayNode<E> getPrevious() {
        return this.prevNode;
    }

    /**
     * This inserts a new node before this one.
     *
     * @param newPrevNode
     *            The new previous node for this node.
     */
    public void insertBefore(TwoWayNode<E> newPrevNode) {
        prevNode = newPrevNode;
    }

    /**
     * This returns the location pointed by the next node.
     *
     * @return The next node.
     */
    public TwoWayNode<E> getNext() {
        return this.nextNode;
    }

    /**
     * This inserts a new node after this one.
     *
     * @param newNextNode
     *            The new next node for this node.
     */
    public void insertAfter(TwoWayNode<E> newNextNode) {
        nextNode = newNextNode;
    }

    /**
     * This overrides the default {@link Object#equals(Object)} method to perform an equality test on two objects.
     *
     * @param o
     *            Object to be compared.
     *
     * @return {@code true} if all the fields are equal, {@code false} otherwise.
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TwoWayNode<?> that = (TwoWayNode<?>) o;

        if (!dataVal.equals(that.dataVal))
            return false;

        // Check previous node without running into infinite recursion
        if (this.prevNode != null) {
            if (that.prevNode != null) {
                return prevNode.dataVal.equals(that.prevNode.dataVal);
            } else {
                return false;
            }
        } else {
            // Only continue true if prev node is null as well, otherwise it has data that we don't have
            if (that.prevNode == null) {
                // Check next node without running into infinite recursion
                if (this.nextNode != null) {
                    if (that.nextNode != null) {
                        return nextNode.dataVal.equals(that.nextNode.dataVal);
                    } else {
                        return false;
                    }
                } else {
                    // Only return true if next node is null as well, otherwise it has data that we don't have
                    return that.nextNode == null;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * This overrides the default {@link Object#hashCode()} method to produce a hash code for the node.
     *
     * @return The hash code associated with the object.
     */
    @Override
    public final int hashCode() {
        int result = dataVal.hashCode();
        result = 31 * result + System.identityHashCode(this); // FIX for single node with no prev and next
        result = 31 * result + (prevNode != null ? System.identityHashCode(prevNode) : 0); // FIX for recursive calls
        result = 31 * result + (nextNode != null ? System.identityHashCode(nextNode) : 0); // FIX for recursive calls
        return result;
    }

    /**
     * This overrides the default {@link Object#toString()} method to produce a string representation of a node.
     *
     * @return A string representation of the stack.
     */
    @Override
    public final String toString() {
        return "TwoWayNode{" + "dataVal=" + dataVal + '}';
    }
}