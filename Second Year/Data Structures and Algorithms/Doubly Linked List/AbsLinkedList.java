/*
 * AbsLinkedList.java
 *
 * Author: Your Name
 * Submitted on: Insert Date
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

import edu.princeton.cs.algs4.StdOut;

/**
 * <p>
 * An abstract class that contains an overridden implementation of {@link Object#toString()}.
 * </p>
 *
 * @author Yu-Shan Sun
 *
 * @version 1.0
 */
public abstract class AbsLinkedList<E> implements ILinkedList<E> {

    /**
     * <p>
     * Checks to see if the list is full.
     * </p>
     *
     * @return {@code true} if the list is full, {@code false} otherwise.
     */
    public final boolean isFull() {
        return false; // since there's really no way to know this
    }

    /**
     * <p>
     * Prints the contents of the list.
     * </p>
     */
    public final void printList() {
        StdOut.println(this.toString());
    }

    /**
     * <p>
     * This helper method retrieves the contents of the doubly-linked list as a string.
     * </p>
     *
     * @return A string representation of the doubly-linked list. The doubly-linked list remains the same.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Traverse doubly-linked list and build a string
        sb.append("[");
        TwoWayNode<E> travNode = this.getHeadOfList();
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
}