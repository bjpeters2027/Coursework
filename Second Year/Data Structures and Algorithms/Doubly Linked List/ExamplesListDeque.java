/*
 * ExamplesListDeque.java
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

import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import wpialgs.hw01.IDeque;
import static org.junit.Assert.*;

/**
 * This class provides test cases for our homework 2's {@link ListDeque}.
 *
 * @version 1.0
 */
public class ExamplesListDeque {

    // Fields
    public List<String> EMPTY_LIST;
    public List<String> LIST1;

    /**
     * <p>
     * The max capacity to use for testing the deque.
     * </p>
     */
    private static final int MAX_LENGTH = 100;

    // Constructor
    public ExamplesListDeque() {
        EMPTY_LIST = Collections.emptyList();
        LIST1 = new ArrayList<>();
    }

    // ===========================================================
    // JUnit Test Methods
    // ===========================================================

    // -----------------------------------------------------------
    // Constructor (Given)
    // -----------------------------------------------------------

    /**
     * Testing the constructor and checking we have an empty deque.
     */
    @Test
    public void testConstructor() {
        IDeque<String> deque = new ListDeque<>();
        assertEquals(EMPTY_LIST.toString(), deque.toString());
        assertEquals(0, deque.length());
    }

    // -----------------------------------------------------------
    // enqueue (copy and modify the test cases from hw01)
    // -----------------------------------------------------------
    @Test
    public void testEnqueue() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Hi");
        assertEquals("Hi",deque.peek());
    }

    @Test
    public void testEnqueueMulti() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("5");
        deque.enqueue("78");
        deque.enqueue("36");

        assertEquals("5",deque.peek());
    }
    // -----------------------------------------------------------
    // dequeue (copy and modify the test cases from hw01)
    // -----------------------------------------------------------
    @Test
    public void testDequeue1() {
        IDeque<Integer> deque = new ListDeque<>();
        deque.enqueue(5);

        assertEquals((Integer) 5,deque.dequeue());
    }
    @Test
    public void testDequeueMulti() {
        IDeque<Integer> deque = new ListDeque<>();
        deque.enqueue(5);
        deque.enqueue(78);
        deque.enqueue(36);

        assertEquals((Integer) 5,deque.dequeue());
    }

    @Test
    public void testDequeueTwice() {
        IDeque<Integer> deque = new ListDeque<>();
        deque.enqueue(5);
        deque.enqueue(78);
        deque.enqueue(36);
        deque.dequeue();

        assertEquals((Integer) 78,deque.dequeue());
    }

    // -----------------------------------------------------------
    // inject (copy and modify the test cases from hw01)
    // -----------------------------------------------------------
    @Test
    public void testInjectMulti() {
        IDeque<Integer> deque = new ListDeque<>();
        deque.enqueue(5);
        deque.enqueue(78);
        deque.enqueue(36);
        deque.inject(4);

        assertEquals((Integer) 4,deque.peek());
    }

    @Test
    public void testInject() {
        IDeque<Integer> deque = new ListDeque<>();
        deque.inject(4);

        assertEquals((Integer) 4,deque.dequeue());
    }

    @Test
    public void testInjectAndDequeue() {
        IDeque<Integer> deque = new ListDeque<>();
        deque.enqueue(5);
        deque.enqueue(78);
        deque.enqueue(36);
        deque.inject(4);
        deque.enqueue(40);
        deque.inject(8);
        deque.dequeue();

        assertEquals((Integer) 4,deque.dequeue());
    }
    // -----------------------------------------------------------
    // removeLast (copy and modify the test cases from hw01)
    // -----------------------------------------------------------
    @Test
    public void testRemove1() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Howdy");

        assertEquals("Howdy",deque.removeLast());
    }

    @Test
    public void testRemoveTwice() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Hi");
        deque.enqueue("Hello");
        deque.enqueue("Howdy");

        assertEquals("Howdy",deque.removeLast());
        assertEquals("Hello",deque.removeLast());
    }
    // -----------------------------------------------------------
    // peek (copy and modify the test cases from hw01)
    // -----------------------------------------------------------

    @Test
    public void testPeek1() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Hello world!");
        assertEquals("Hello world!", deque.peek());
    }

    @Test
    public void testPeekMulti() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Hello world!");
        deque.enqueue("CS 2223");
        deque.enqueue("Brendon");
        assertEquals("Hello world!", deque.peek());
    }
    // -----------------------------------------------------------
    // endOfDeque (copy and modify the test cases from hw01)
    // -----------------------------------------------------------
    @Test
    public void testEoDMulti() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Hello world!");
        deque.inject("Hi all!");
        deque.enqueue("Greetings people!");
        assertEquals("Greetings people!", deque.endOfDeque());
    }

    @Test
    public void testEoD1() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Greetings people!");
        assertEquals("Greetings people!", deque.endOfDeque());
    }

    // -----------------------------------------------------------
    // length (copy and modify the test cases from hw01)
    // -----------------------------------------------------------
    @Test
    public void testLengthEmpty() {
        IDeque<String> deque = new ListDeque<>();
        assertEquals(0, deque.length());
    }

    @Test
    public void testLength() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Hi");
        deque.inject("Hello");
        deque.enqueue("What's up?");
        assertEquals(3, deque.length());
    }
    // -----------------------------------------------------------
    // clear (copy and modify the test cases from hw01)
    // -----------------------------------------------------------
    @Test
    public void testClear() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Hello world!");
        deque.inject("Hi all!");
        deque.enqueue("Greetings people!");
        deque.clear();
        assertEquals(0, deque.length());
    }


    @Test
    public void testClearEmpty() {
        IDeque<String> deque = new ListDeque<>();
        deque.clear();
        assertEquals(0, deque.length());
    }

    @Test
    public void testClear2() {
        IDeque<String> deque = new ListDeque<>();
        deque.enqueue("Hello world!");
        deque.inject("Hi all!");
        deque.enqueue("Greetings people!");
        deque.clear();
        assertEquals(0, deque.length());
    }

}