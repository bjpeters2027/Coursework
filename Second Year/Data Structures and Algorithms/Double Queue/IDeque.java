/*
 * IDeque.java
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
package wpialgs.hw01;

/**
 * <p>
 * A deque is abstractly a string of elements. It is a double-ended queue that allows you to insert and remove from both
 * ends.
 * </p>
 *
 * @author Yu-Shan Sun
 *
 * @version 1.0
 */
public interface IDeque<E> {

    /**
     * <p>
     * Adds x to the end of the deque.
     * </p>
     *
     * @param item
     *            Element to be added.
     */
    public void enqueue(E item);

    /**
     * <p>
     * Removes and returns the element at the front of the deque.
     * </p>
     *
     * @return Element at the front of the deque
     */
    public E dequeue();

    /**
     * <p>
     * Adds x to the front of the deque.
     * </p>
     *
     * @param item
     *            Element to be added.
     */
    public void inject(E item);

    /**
     * <p>
     * Removes and returns the element at the end of the deque.
     * </p>
     *
     * @return Element at the end of the deque
     */
    public E removeLast();

    /**
     * <p>
     * Returns the element at the front of the deque, but doesn't remove it.
     * </p>
     *
     * @return Element at the front of the deque
     */
    public E peek();

    /**
     * <p>
     * Returns the element at the end of the deque, but doesn't remove it.
     * </p>
     *
     * @return Element at the end of the deque
     */
    public E endOfDeque();

    /**
     * <p>
     * Returns the number of elements in the deque.
     * </p>
     *
     * @return The length of the deque.
     */
    public int length();

    /**
     * <p>
     * Clears the entire deque.
     * </p>
     */
    public void clear();
}