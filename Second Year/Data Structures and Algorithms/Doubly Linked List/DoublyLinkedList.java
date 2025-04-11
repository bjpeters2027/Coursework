/*
 * DoublyLinkedList.java
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

public class DoublyLinkedList<E> extends AbsLinkedList<E> {

    // ===========================================================
    // Class attributes
    // ===========================================================

    private TwoWayNode<E> myHeadNode;
    private TwoWayNode<E> myTailNode;
    private int myListLength;

    // ===========================================================
    // Constructors
    // ===========================================================

    /**
     * <p>
     * This creates a new doubly-linked-list that is initially empty.
     * </p>
     */
    public DoublyLinkedList() {
        // You may modify the constructor if you need to add more
        // private class attributes, but do not alter the following lines.
        myHeadNode = null;
        myTailNode = null;
        myListLength = 0;
    }

    // ===========================================================
    // Public Methods
    // ===========================================================

    public TwoWayNode<E> getHeadOfList() {
        return myHeadNode;
    }

    public TwoWayNode<E> getTailOfList() {
        return myTailNode;
    }

    public void insertAtHead(E newData) {
        if(isEmpty()) {
            myTailNode = new TwoWayNode<>(newData , null , null);
            myHeadNode = myTailNode;
        }
        else {
            TwoWayNode<E> temp = new TwoWayNode<>(newData , null , myHeadNode);
            if(myListLength > 0){
                myHeadNode.insertBefore(temp);
            }
            myHeadNode = temp;
        }
        myListLength++;
    }

    public void insertAtTail(E newData) {
        if(isEmpty()) {
            myTailNode = new TwoWayNode<>(newData , null , null);
            myHeadNode = myTailNode;
        }
        else {
            TwoWayNode<E> temp = new TwoWayNode<>(newData,myTailNode,null);
            myTailNode.insertAfter(temp);
            myTailNode = temp;
        }
        myListLength++;
    }

    public boolean isEmpty() {
        return myListLength == 0;
    }

    public int getListLength() {
        return myListLength;
    }

    public void clearList() {
        myTailNode = null;
        myHeadNode = null;
        myListLength = 0;
    }

    public TwoWayNode<E> findFirstOccurrenceNode(E searchData) {
        TwoWayNode<E> temp = myHeadNode;
        while(temp != null){
            if(temp.getDataVal().equals(searchData)){
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }

    public TwoWayNode<E> findLastOccurrenceNode(E searchData) {
        TwoWayNode<E> temp = myTailNode;
        while(temp != null){
            if(temp.getDataVal().equals(searchData)){
                return temp;
            }
            temp = temp.getPrevious();
        }
        return null;
    }

    public void replaceFirstOccurrenceNode(E searchData, E newData) {
        TwoWayNode<E> temp = myHeadNode;
        while(temp != null){
            if(temp.getDataVal().equals(searchData)){
                temp.setDataVal(newData);
                break;
            }
            temp = temp.getNext();
        }
    }

    public void replaceLastOccurrenceNode(E searchData, E newData) {
        TwoWayNode<E> temp = myTailNode;
        while(temp != null){
            if(temp.getDataVal().equals(searchData)){
                temp.setDataVal(newData);
                break;
            }
            temp = temp.getPrevious();
        }
    }

    public void insertAfterFirstOccurrence(E searchData, E newData) {
        TwoWayNode<E> temp = myHeadNode;
        while(temp != null){
            if(temp.getDataVal().equals(searchData)){
                temp.insertAfter(new TwoWayNode<E>(newData,temp, temp.getNext()));
                if(temp.equals(myTailNode)){
                    myTailNode = temp.getPrevious();
                }
                break;
            }
            temp = temp.getNext();
        }
    }

    public void insertAfterLastOccurrence(E searchData, E newData) {
        TwoWayNode<E> temp = myTailNode;
        while(temp != null){
            if(temp.getDataVal().equals(searchData)){
                temp.insertAfter(new TwoWayNode<E>(newData,temp, temp.getNext()));
                if(temp.equals(myTailNode)){
                    myTailNode = temp.getNext();
                }
                break;
            }
            temp = temp.getPrevious();
        }
    }

    public void insertBeforeFirstOccurrence(E searchData, E newData) {
        TwoWayNode<E> temp = myHeadNode;
        while(temp != null){
            if(temp.getDataVal().equals(searchData)){
                temp.getPrevious().insertAfter(new TwoWayNode<E>(newData,temp, temp));
                if(temp.equals(myTailNode)){
                    myTailNode = temp.getNext();
                }
                break;
            }
            temp = temp.getNext();
        }
    }

    public void insertBeforeLastOccurrence(E searchData, E newData) {
        TwoWayNode<E> temp = myTailNode;
        while(temp != null){
            if(temp.getDataVal().equals(searchData)){
                if(temp.equals(myHeadNode)) {
                    temp.insertBefore(new TwoWayNode<E>(newData, temp.getPrevious(), temp));
                    myHeadNode = temp.getPrevious();
                }else {
                    temp.getPrevious().insertAfter(new TwoWayNode<E>(newData, temp.getPrevious(), temp));
                    if (temp.equals(myTailNode)) {
                        myTailNode = temp;
                    }
                }
                break;
            }
            temp = temp.getPrevious();
        }
    }

    public TwoWayNode<E> removeFirstOccurrenceNode(E searchData) {
        TwoWayNode<E> temp = findFirstOccurrenceNode(searchData);
        if(temp != null){
            if(myListLength == 1 ){
                clearList();
                return temp;
            }else if (temp.equals(myHeadNode)){
                temp.getNext().insertBefore(null);
                myHeadNode = temp.getNext();
            } else if (temp.equals(myTailNode)){
                temp.getPrevious().insertAfter(null);
                myTailNode = temp.getPrevious();
            }else{
                temp.getPrevious().insertAfter(temp.getNext());
                temp.getNext().insertBefore(temp.getPrevious());
            }
            myListLength--;
            return temp;
        }

        return null;
    }

    public TwoWayNode<E> removeLastOccurrenceNode(E searchData){
        TwoWayNode<E> temp = findLastOccurrenceNode(searchData);
        if (temp != null){
            if(myListLength == 1 ){
                clearList();
                return temp;
            }else if (temp.equals(myHeadNode)){
                temp.getNext().insertBefore(null);
                myHeadNode = temp.getNext();
            }else if (temp.equals(myTailNode)){
                temp.getPrevious().insertAfter(null);
                myTailNode = temp.getPrevious();
            }else{
                temp.getPrevious().insertAfter(temp.getNext());
                temp.getNext().insertBefore(temp.getPrevious());
            }
            myListLength--;
            return temp;
        }

        return null;
    }
}