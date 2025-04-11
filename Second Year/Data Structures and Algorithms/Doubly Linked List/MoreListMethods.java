/*
 * MoreListMethods.java
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
 * This class provides more helpful methods for {@link ILinkedList}. Note that you don't have direct access to the
 * private data in {@link DoublyLinkedList}, so you will have to use the methods from the interface to complete this
 * class!
 * </p>
 *
 * @version 2.0
 */
public class MoreListMethods {

    // ===========================================================
    // Public Methods
    // ===========================================================

    // This method returns the smallest element in the list
    // HINT: Use the "compareTo" method!
    public static String findMin(ILinkedList<String> l) {
        String tempS = l.getHeadOfList().getDataVal();
        TwoWayNode<String> tempN = l.getHeadOfList();
        while(tempN != null){
            if(tempN.getDataVal().compareTo(tempS) < 0){
                tempS = tempN.getDataVal();
            }
            tempN = tempN.getNext();
        }
        return tempS;
    }

    // This method returns the largest element in the list
    // HINT: Use the "compareTo" method!
    public static String findMax(ILinkedList<String> l) {
        String tempS = l.getHeadOfList().getDataVal();
        TwoWayNode<String> tempN = l.getHeadOfList();
        while(tempN != null){
            if(tempN.getDataVal().compareTo(tempS) > 0){
                tempS = tempN.getDataVal();
            }
            tempN = tempN.getNext();
        }
        return tempS;
    }

    // This method returns the middle node of the list. If the list size is an even number,
    // you may return either middle nodes.
    public static TwoWayNode<String> findMiddleNode(ILinkedList<String> l) {
        TwoWayNode<String> temp = new TwoWayNode<String>(l.getHeadOfList().getDataVal(), l.getHeadOfList().getPrevious(), l.getHeadOfList().getNext());
        int i = 0;
        while(temp != null && i < l.getListLength() / 2){
            temp = temp.getNext();
            i++;
        }
        return temp;
    }

    // This method returns a duplicate copy of the list. Note that this problem requires you to make a
    // "deep copy" of all the elements in the list!
    public static ILinkedList<String> createDuplicateList(ILinkedList<String> l) {

        ILinkedList<String> ans = new DoublyLinkedList<>();
        TwoWayNode<String> temp = new TwoWayNode<String>(l.getHeadOfList().getDataVal(), l.getHeadOfList().getPrevious(), l.getHeadOfList().getNext());

        while(temp != null){
            ans.insertAtTail(temp.getDataVal());
            temp = temp.getNext();
        }
        return ans;
    }

    // This method returns a new list that is the concatenation of two lists.
    // Note that this problem requires you to make a "deep copy" of all the elements in both of the list!
    public static ILinkedList<String> concatenateLists(ILinkedList<String> l1, ILinkedList<String> l2) {
        DoublyLinkedList<String> ans = new DoublyLinkedList<>();
        TwoWayNode<String> temp = l1.getHeadOfList();
        while(temp != null){
            ans.insertAtTail(temp.getDataVal());
            temp = temp.getNext();
        }
        temp = l2.getHeadOfList();
        while(temp != null){
            ans.insertAtTail(temp.getDataVal());
            temp = temp.getNext();
        }
        return ans;
    }

    // This method returns a new list that merges two sorted lists.
    // Note that this problem requires you to make a "deep copy" of all the elements in both of the list!
    public static ILinkedList<String> mergeSortedLists(ILinkedList<String> l1, ILinkedList<String> l2) {

        ILinkedList<String> ans = new DoublyLinkedList<>();

        if(!l1.isEmpty() && !l2.isEmpty()){
            TwoWayNode<String> temp1 = new TwoWayNode<String>(l1.getHeadOfList().getDataVal(), l1.getHeadOfList().getPrevious(), l1.getHeadOfList().getNext());
            TwoWayNode<String> temp2 = new TwoWayNode<String>(l2.getHeadOfList().getDataVal(), l2.getHeadOfList().getPrevious(), l2.getHeadOfList().getNext());
            while ((temp1 != null) && (temp2 != null)){
                if (temp1.getDataVal().compareTo(temp2.getDataVal()) < 0) {
                    ans.insertAtTail(temp1.getDataVal());
                    temp1 = temp1.getNext();
                } else{
                    ans.insertAtTail(temp2.getDataVal());
                    temp2 = temp2.getNext();
                }
            }
            if (temp1 != null) {
                while (temp1 != null) {
                    ans.insertAtTail(temp1.getDataVal());
                    temp1 = temp1.getNext();
                }
            }else if (temp2 != null) {
                while (temp2 != null) {
                    ans.insertAtTail(temp2.getDataVal());
                    temp2 = temp2.getNext();
                }
            }

            return ans;
        }else if (l2.isEmpty() && !l1.isEmpty()){
            TwoWayNode<String> temp1 = new TwoWayNode<String>(l1.getHeadOfList().getDataVal(), l1.getHeadOfList().getPrevious(), l1.getHeadOfList().getNext());
            while (temp1 != null) {
                ans.insertAtTail(temp1.getDataVal());
                temp1 = temp1.getNext();
            }
            return ans;
        } else if (l1.isEmpty() && !l2.isEmpty()) {
            TwoWayNode<String> temp2 = new TwoWayNode<String>(l2.getHeadOfList().getDataVal(), l2.getHeadOfList().getPrevious(), l2.getHeadOfList().getNext());
            while (temp2 != null) {
                ans.insertAtTail(temp2.getDataVal());
                temp2= temp2.getNext();
            }
            return ans;
        }
        return new DoublyLinkedList<>();
    }

    // This method returns a new list that is the reverse of the original list
    // Note that this problem requires you to make a "deep copy" of all the elements in the list!
    public static ILinkedList<String> reverseList(ILinkedList<String> origList) {
        if(origList.isEmpty()){
            return new DoublyLinkedList<>();
        }
        ILinkedList<String> ans = new DoublyLinkedList<>();
        TwoWayNode<String> temp = new TwoWayNode<String>(origList.getHeadOfList().getDataVal(), origList.getHeadOfList().getPrevious(), origList.getHeadOfList().getNext());

        while(temp != null){
            ans.insertAtHead(temp.getDataVal());
            temp = temp.getNext();
        }
        return ans;
    }
}