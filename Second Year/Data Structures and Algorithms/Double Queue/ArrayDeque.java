/*
 * ArrayDeque.java
 *
 * Author: Brendon Peters
 * Submitted on: 9/3/2024
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
package wpialgs.hw01;

/**
 * <p>
 * An array implementation of {@link IDeque}.
 * </p>
 *
 * @version 1.0
 */
public class ArrayDeque<E> extends AbsDeque<E> implements IDeque<E> {

    // ===========================================================
    // Member Fields
    // ===========================================================

    /**
     * <p>
     * Where the data is stored. myQ[0] is the front of the deque.
     * </p>
     */
    private final E[] myQ;

    /**
     * <p>
     * Tracks how many items in the deque, also used to find the end of the deque.
     * </p>
     */
    private int myLength;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * <p>
     * This creates a new array-based deque that is initially empty.
     * </p>
     *
     * @param capacity
     *            Maximum number of elements that can be stored in this deque.
     */
    @SuppressWarnings("unchecked")
    public ArrayDeque(int capacity) {
        // TODO: Replace the following line with your implementation
        myQ = (E[]) new Object[capacity];
        myLength = 0;
    }

    // ===========================================================
    // Public Methods
    // ===========================================================

    /**
     *
     * @param item
     *            Element to be added to the back of the queue
     */

    @Override
    public void enqueue(E item) {
        // TODO: Replace the following line with your implementation
        myQ[myLength] = item;
        myLength++;
    }

    /**
     *
     * @return item at the front of the queue and removes it. Also move everything down
     */
    @Override
    public E dequeue() {
        // TODO: Replace the following line with your implementation
        E item = myQ[0];
        for (int i = 1; i < myLength; i++) {
        myQ[i - 1] = myQ[i];
        }
        myQ[myLength - 1] = null;
        myLength--;
        return item;
    }


    /**
     *
     * @param item
     *            Element to be added to the front of the queue and shifts all other items over
     */
    @Override
    public void inject(E item) {
        for (int i = myLength; i > 0; i--) {
            myQ[i] = myQ[i - 1]; // Shift elements to the right
        }
        myQ[0] = item;
        myLength++;
    }

    /**
     *
     * @return the item at the end of the queue and removes it
     */
    @Override
    public E removeLast() {
        E item = myQ[myLength -1];
        myQ[myLength] = null;
        myLength--;
        return item;
    }


    /**
     *
     * @return item at the front of the queue and does not remove it
     */
    @Override
    public E peek() {
        // TODO: Replace the following line with your implementation
        return myQ[0];
    }

    /**
     *
     * @return item at the end of the queue and does not remove it
     */
    @Override
    public E endOfDeque() {
        // TODO: Replace the following line with your implementation
        return myQ[myLength - 1];
    }

    /**
     *
     * @return the number of items in the queue
     */
    @Override
    public int length() {
        // TODO: Replace the following line with your implementation
        return myLength;
    }

    /**
     * resets the queue to all null values
     */
    @Override
    public void clear() {
        // TODO: Replace the following line with your implementation
        for(int i = 0; i < myLength; i++){
            myQ[i] = null;
        }
        myLength = 0;
    }
}