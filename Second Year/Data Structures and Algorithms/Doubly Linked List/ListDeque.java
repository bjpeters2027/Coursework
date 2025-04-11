/*
 * ListDeque.java
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

import wpialgs.hw01.AbsDeque;
import wpialgs.hw01.IDeque;

/**
 * <p>
 * An linked-list implementation of {@link IDeque}. It uses our own version of doubly-linked-list provided by
 * {@link ILinkedList}.
 * </p>
 *
 * @version 1.0
 */
public class ListDeque<E> extends AbsDeque<E> implements IDeque<E> {

    // ===========================================================
    // Class attributes
    // ===========================================================

    /**
     * <p>
     * Our data is stored in our linked list implementation.
     * </p>
     */
    private ILinkedList<E> myList;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * <p>
     * This creates a new linked-list-based deque that is initially empty.
     * </p>
     */
    public ListDeque() {
        myList = new DoublyLinkedList<>();
    }

    // ===========================================================
    // Public Methods
    // ===========================================================

    @Override
    public void enqueue(E item) {
        myList.insertAtTail(item);
    }

    @Override
    public E dequeue() {
        if(myList.getListLength() != 0) {
            E temp = myList.getHeadOfList().getDataVal();
            myList.removeFirstOccurrenceNode(temp);
            return temp;
        }
        return null;
    }

    @Override
    public void inject(E item) {
        myList.insertAtHead(item);
    }

    @Override
    public E removeLast() {
        E temp = myList.getTailOfList().getDataVal();
        myList.removeLastOccurrenceNode(temp);
        return temp;
    }

    @Override
    public E peek() {
        if (myList.getListLength() != 0) {
            return myList.getHeadOfList().getDataVal();
        }
        return null;
    }

    @Override
    public E endOfDeque() {
        if(myList.getListLength() != 0){
            return myList.getTailOfList().getDataVal();
        }
        return null;
    }

    @Override
    public int length() {
        return myList.getListLength();
    }

    @Override
    public void clear() {
        myList.clearList();
    }
}