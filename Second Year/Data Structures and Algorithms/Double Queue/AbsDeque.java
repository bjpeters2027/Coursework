/*
 * AbsDeque.java
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
 * An abstract class that contains an overridden implementation of {@link Object#toString()}.
 * </p>
 *
 * @version 1.0
 */
public abstract class AbsDeque<E> implements IDeque<E> {

    /**
     * <p>
     * This helper method retrieves the contents of the deque as a string.
     * </p>
     *
     * @return A string representation of the deque. The deque remains the same.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.length(); i++) {
            // Make sure we are able to "restore" the IDeque when we are done generating a string
            E x = this.dequeue();
            sb.append(x);
            this.enqueue(x);

            // Put a comma if it is not the last element.
            if (i < this.length() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}