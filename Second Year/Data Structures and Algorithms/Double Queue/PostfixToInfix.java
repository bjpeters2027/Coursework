/*
 * PostfixToInfix.java
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

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import wpialgs.day03.FixedCapacityStack;
import wpialgs.day03.IStack;
import java.util.ArrayList;

/**
 * This class uses the {@link IStack} and {@link FixedCapacityStack} to first convert a postfix expression to an infix
 * expression and then performs the necessary computation to produce its value.
 * <p>
 * Using the postfix expression as input
 * <p>
 * 3 6 + 5 * 8 2 - /
 * <p>
 * should produce the following as output:
 * <p>
 * (((3 + 6) * 5) / (8 - 2)) = 7.5
 *
 * @version 1.0
 */
public class PostfixToInfix {

    /**
     * The main entry point to the program.
     *
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args) {
        IStack<String> exprs = new FixedCapacityStack<>(100);
        IStack<Double> vals = new FixedCapacityStack<>(100);
        double v;
        String b;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();

            switch(s){
                //Solve first and only need the values stack
                case "+":

                    b = exprs.pop();
                    v = vals.pop();
                        exprs.push("( " + exprs.pop() + " + " + b + " )" );

                        v = vals.pop() + v;
                    vals.push(v);
                    break;

                case "-":
                    v = vals.pop();
                    b = exprs.pop();
                    exprs.push("( " + exprs.pop() + " - " + b + " )" );
                        v = vals.pop() - v;
                    vals.push(v);
                    break;

                case "*":
                    v = vals.pop();
                    b = exprs.pop();
                    exprs.push("( " + exprs.pop() + " * " + b + " )" );
                        v = vals.pop() * v;
                    vals.push(v);
                    break;

                case "/":
                    v = vals.pop();
                    b = exprs.pop();
                    exprs.push("( " + exprs.pop() + " / " + b + " )" );
                        v = vals.pop() / v;
                    vals.push(v);
                    break;

                default:
                    vals.push(Double.parseDouble(s));
                    exprs.push(s);
                    break;
            }
        }

        StdOut.println(exprs.pop() + " = " + vals.pop());


    }
}