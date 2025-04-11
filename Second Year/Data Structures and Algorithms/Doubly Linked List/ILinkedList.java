/*
 * ILinkedList.java
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
 * <p>
 * An interface that defines a linked-list's operations.
 * </p>
 *
 * @author Yu-Shan Sun
 *
 * @version 1.0
 */
public interface ILinkedList<E> {

    /**
     * <p>
     * Returns the head of the list.
     * </p>
     *
     * @return Node representing the beginning of the list.
     */
    public TwoWayNode<E> getHeadOfList();

    /**
     * <p>
     * Returns the tail of the list.
     * </p>
     *
     * @return Node representing the end of the list.
     */
    public TwoWayNode<E> getTailOfList();

    /**
     * <p>
     * Adds newData to the beginning of the list.
     * </p>
     *
     * @param newData
     *            Element to be added.
     */
    public void insertAtHead(E newData);

    /**
     * <p>
     * Adds newData to the end of the list.
     * </p>
     *
     * @param newData
     *            Element to be added.
     */
    public void insertAtTail(E newData);

    /**
     * <p>
     * Prints the contents of the list.
     * </p>
     */
    public void printList();

    /**
     * <p>
     * Checks to see if the list is empty.
     * </p>
     *
     * @return {@code true} if the list is empty, {@code false} otherwise.
     */
    public boolean isEmpty();

    /**
     * <p>
     * Checks to see if the list is full.
     * </p>
     *
     * @return {@code true} if the list is full, {@code false} otherwise.
     */
    public boolean isFull();

    /**
     * <p>
     * Returns the number of elements in the linked list.
     * </p>
     *
     * @return The length of the linked list.
     */
    public int getListLength();

    /**
     * <p>
     * Clears the entire linked list.
     * </p>
     */
    public void clearList();

    /**
     * <p>
     * Searches the entire linked list looking for the first occurrence of a particular value. You may assume that
     * {@link E} has a properly implemented {@link Object#equals(Object)} and {@link Object#hashCode()} methods.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     *
     * @return The node that contains this element.
     */
    public TwoWayNode<E> findFirstOccurrenceNode(E searchData);

    /**
     * <p>
     * Searches the entire linked list looking for the last occurrence of a particular value. You may assume that
     * {@link E} has a properly implemented {@link Object#equals(Object)} and {@link Object#hashCode()} methods.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     *
     * @return The node that contains this element.
     */
    public TwoWayNode<E> findLastOccurrenceNode(E searchData);

    /**
     * <p>
     * Attempts to replaces the first occurrence of an element in our linked list. If the element does not exist in our
     * list, then no change is made to the list.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     * @param newData
     *            New element that will replace {@code searchData}.
     */
    public void replaceFirstOccurrenceNode(E searchData, E newData);

    /**
     * <p>
     * Attempts to replaces the last occurrence of an element in our linked list. If the element does not exist in our
     * list, then no change is made to the list.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     * @param newData
     *            New element that will replace {@code searchData}.
     */
    public void replaceLastOccurrenceNode(E searchData, E newData);

    /**
     * <p>
     * Attempts to insert after the first occurrence of an element in our linked list. If the element does not exist in
     * our list, then no change is made to the list.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     * @param newData
     *            New element that will be inserted after {@code searchData}.
     */
    public void insertAfterFirstOccurrence(E searchData, E newData);

    /**
     * <p>
     * Attempts to insert after the last occurrence of an element in our linked list. If the element does not exist in
     * our list, then no change is made to the list.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     * @param newData
     *            New element that will be inserted after {@code searchData}.
     */
    public void insertAfterLastOccurrence(E searchData, E newData);

    /**
     * <p>
     * Attempts to insert before the first occurrence of an element in our linked list. If the element does not exist in
     * our list, then no change is made to the list.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     * @param newData
     *            New element that will be inserted before {@code searchData}.
     */
    public void insertBeforeFirstOccurrence(E searchData, E newData);

    /**
     * <p>
     * Attempts to insert before the last occurrence of an element in our linked list. If the element does not exist in
     * our list, then no change is made to the list.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     * @param newData
     *            New element that will be inserted before {@code searchData}.
     */
    public void insertBeforeLastOccurrence(E searchData, E newData);

    /**
     * <p>
     * Attempts to remove the first occurrence of an element in our linked list. If the element does not exist in our
     * list, then no change is made to the list.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     *
     * @return A node containing the element that we removed. If we didn't remove anything from our list, this method
     *         returns {@code null}.
     */
    public TwoWayNode<E> removeFirstOccurrenceNode(E searchData);

    /**
     * <p>
     * Attempts to remove the first occurrence of an element in our linked list. If the element does not exist in our
     * list, then no change is made to the list.
     * </p>
     *
     * @param searchData
     *            Element that we are going to look for in our list.
     *
     * @return A node containing the element that we removed. If we didn't remove anything from our list, this method
     *         returns {@code null}.
     */
    public TwoWayNode<E> removeLastOccurrenceNode(E searchData);

}