/*
 * DoublyLinkedListTest.java
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

import java.util.Scanner;

/**
 * This is a doubly-linked-list test class that can be used to test your implementation.
 *
 * @author Roger Van Scoy
 * @author Yu-Shan Sun
 *
 * @version 2.0
 */
public class DoublyLinkedListTest {

    /**
     * The main entry point to the program.
     *
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Scanner inSS;

        // Temporary variables
        String commandLine;
        TwoWayNode<Integer> tempNode = null;
        char cmd;
        int newData;
        int oldData;

        // Our linked-list
        ILinkedList<Integer> myList = new DoublyLinkedList<>();

        do {
            print_menu();

            // Use another Scanner to parse the input string
            commandLine = scnr.nextLine();
            inSS = new Scanner(commandLine.substring(1));
            cmd = commandLine.charAt(0);

            switch (cmd) {
            case '+': // Insert x at the end
                newData = inSS.nextInt();
                myList.insertAtTail(newData);
                break;
            case '*': // Insert x at head
                newData = inSS.nextInt();
                myList.insertAtHead(newData);
                break;
            case 'P':
            case 'p': // Print current list contents
                System.out.println("------------------");
                myList.printList();
                System.out.println("------------------");
                break;
            case '^': // Replace first occurrence of item x with y
                oldData = inSS.nextInt();
                newData = inSS.nextInt();
                myList.replaceFirstOccurrenceNode(oldData, newData);
                break;
            case 'v': // Replace last occurrence of item x with y
                oldData = inSS.nextInt();
                newData = inSS.nextInt();
                myList.replaceLastOccurrenceNode(oldData, newData);
                break;
            case '>': // Insert y after first occurrence of x
                oldData = inSS.nextInt();
                newData = inSS.nextInt();
                myList.insertAfterFirstOccurrence(oldData, newData);
                break;
            case ')': // Insert y after last occurrence of x
                oldData = inSS.nextInt();
                newData = inSS.nextInt();
                myList.insertAfterLastOccurrence(oldData, newData);
                break;
            case '<': // Insert y before first occurrence of x
                oldData = inSS.nextInt();
                newData = inSS.nextInt();
                myList.insertBeforeFirstOccurrence(oldData, newData);
                break;
            case '(': // Insert y before last occurrence of x
                oldData = inSS.nextInt();
                newData = inSS.nextInt();
                myList.insertBeforeLastOccurrence(oldData, newData);
                break;
            case '-': // Remove the first occurrence of the data item
                oldData = inSS.nextInt();
                myList.removeFirstOccurrenceNode(oldData);
                break;
            case '_': // Remove the last occurrence of the data item
                oldData = inSS.nextInt();
                myList.removeLastOccurrenceNode(oldData);
                break;
            case 'C':
            case 'c': // Clear the list
                myList.clearList();
                break;
            case 'E':
            case 'e': // Empty list?
                if (myList.isEmpty()) {
                    System.out.println("------------------");
                    System.out.println("List is EMPTY.");
                    System.out.println("------------------");
                } else {
                    System.out.println("------------------");
                    System.out.println("List is NOT empty.");
                    System.out.println("------------------");
                }
                break;
            case 'F':
            case 'f': // Full list?
                myList.isFull();
                System.out.println("------------------");
                System.out.println("Well...there is really no good way to know...");
                System.out.println("------------------");
                break;
            case 'L':
            case 'l': // Current List Size
                System.out.println("------------------");
                System.out.printf("There are %d items in the list.\n", myList.getListLength());
                System.out.println("------------------");
                break;
            case '?': // Search list for first occurrence of x
                oldData = inSS.nextInt();
                System.out.println("------------------");
                tempNode = myList.findFirstOccurrenceNode(oldData);
                if (tempNode != null) {
                    System.out.printf("FOUND %d in list.\n", oldData);
                } else {
                    System.out.printf("%d NOT found in list.\n", oldData);
                }
                System.out.println("------------------");
                break;
            case '!': // Search list for last occurrence of x
                oldData = inSS.nextInt();
                System.out.println("------------------");
                tempNode = myList.findLastOccurrenceNode(oldData);
                if (tempNode != null) {
                    System.out.printf("FOUND %d in list.\n", oldData);
                } else {
                    System.out.printf("%d NOT found in list.\n", oldData);
                }
                System.out.println("------------------");
                break;
            case 'M':
            case 'm': // Menu
                print_menu();
                break;
            case 'Q':
            case 'q': // Quit test program
                break;
            default: // Invalid command
                System.out.println("Invalid command");
                break;
            }
        } while (cmd != 'Q' && cmd != 'q');
    }

    /**
     * A helper method that prints our menu of options.
     */
    public static void print_menu() {
        System.out.println("Menu...");
        System.out.println("  M      : Menu (displays this message)");
        System.out.println("  +x     : Insert x at the end");
        System.out.println("  *x     : Insert x at the head");
        System.out.println("  >x y   : Insert y after the first occurrence of item x");
        System.out.println("  )x y   : Insert y after the last occurrence of item x");
        System.out.println("  <x y   : Insert y before the first occurrence of item x");
        System.out.println("  (x y   : Insert y before the last occurrence of item x");
        System.out.println("  ^x y   : Replace the first occurrence of item x with y");
        System.out.println("  vx y   : Replace the last occurrence of item x with y");
        System.out.println("  -x     : Find & remove the first occurrence of item x");
        System.out.println("  _x     : Find & remove the last occurrence of item x");
        System.out.println("  ?x     : Search list for the first occurrence of x");
        System.out.println("  !x     : Search list for the last occurrence of x");
        System.out.println("  C      : Clear the list");
        System.out.println("  E      : List empty?");
        System.out.println("  F      : List full?");
        System.out.println("  L      : Current list size");
        System.out.println("  P      : Print current list contents");
        System.out.println("  Q      : Quit the test program");
        System.out.print("Enter Command: ");
    }
}