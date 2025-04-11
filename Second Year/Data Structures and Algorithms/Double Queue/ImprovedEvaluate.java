/*
 * ImprovedEvaluate.java
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

/**
 * This is a modified version of the Dijkstra's Two-Stack Algorithm for expression evaluation on pp. 129. It uses our
 * defined {@link IStack} and {@link FixedCapacityStack}.
 * <p>
 * To run in IntelliJ / Eclipse, you will need to enter your input into the Console window directly. After you press
 * return, nothing appears to happen. This is because you need to "close" the {@link StdIn}.
 * <p>
 * This is done on a PC by pressing Control-z.
 * <p>
 * On a Macintosh (I am not making this up), to terminate the input, click the mouse anywhere else in Eclipse (typically
 * just back in the source code or in the package explorer), then click BACK in the console window and press Control-d
 * (not Command-d). If you are using IntelliJ, you can just press Command-d in the console.
 *
 * @author George Heineman
 * @author Yu-Shan Sun
 *
 * @version 1.0
 */
public class ImprovedEvaluate {

    /**
     * The main entry point to the program.
     *
     * @param args
     *            Command line arguments
     * When run the user will need to input an equation to be solved.
     * The equation must be input using parenthesis and proper spacing
     * Ex: ( 3 + 3 )
     *
     *
     */
    public static void main(String[] args) {
        // YS: Modified to used our fixed capacity stack.
        IStack<String> ops = new FixedCapacityStack<>(100);
        IStack<Double> vals = new FixedCapacityStack<>(100);
        while (!StdIn.isEmpty()) { // Read token, push if operator.
            String s = StdIn.readString();
            if (s.equals("("))
                ;
            else if (s.equals("+"))
                ops.push(s);
            else if (s.equals("-"))
                ops.push(s);
            else if (s.equals("*"))
                ops.push(s);
            else if (s.equals("/"))
                ops.push(s);
            else if (s.equals("sqrt"))
                ops.push(s);
            else if (s.equals("exp")){
                ops.push(s);
            }
            else if(s.equals("log")){
                ops.push(s);
            }
            else if (s.equals(")")) { // Pop, evaluate, and push result if token is ")".

                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+"))
                    v = vals.pop() + v;
                else if (op.equals("-"))
                    v = vals.pop() - v;
                else if (op.equals("*"))
                    v = vals.pop() * v;
                else if (op.equals("/"))
                    v = vals.pop() / v;
                else if (op.equals("sqrt"))
                    v = Math.sqrt(v);
                else if (op.equals("exp"))
                    v = Math.pow(vals.pop(), v);
                else if (op.equals("log"))
                    v = (Math.log(vals.pop()) / Math.log(v));
                vals.push(v);
            } // Token not operator or paren: push double value.
            else
                vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());

        // set a breakpoint on this line, and you can observe the state
        // of the ops stack and vals stack. You will need this for your
        // answer.
        StdOut.println();
    }
}