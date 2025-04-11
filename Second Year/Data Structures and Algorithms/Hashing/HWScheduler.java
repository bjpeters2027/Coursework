/*
 * HWScheduler.java
 *
 * Author: Brendon Peters
 * Submitted on: 9/24/2024
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
package wpialgs.hw03;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * <p>
 * This is a scheduler that uses the "Earliest Deadline First" strategy to schedule activities.
 * </p>
 *
 * @version 1.0
 */
public class HWScheduler {

    /**
     * The main entry point to the program.
     *
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("USAGE: java HWScheduler <filename>");
        } else {
            // Open the file
            String filename = args[0];
            In in = new In(filename);
            String[] lines = in.readAllLines();
            // Welcome message
            StdOut.println("Welcome to CS 2223 - Earliest Deadline First Scheduler!");
            StdOut.printf("\tRunning scheduler on file: %s.\n", filename);
            StdOut.println("-------------------------------------------------------");
            int totalTime = 0;
            int time = 0;
            int realTime = 0;
            int workTime = 0;
            MinPQ<Activity> schedule = new MinPQ<>();
            for(int i = 0; i < lines.length; i++){
                String[] temp = lines[i].split(" ");
                if(temp[0].equals("schedule")){
                    schedule.insert(new Activity(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
                    StdOut.println("[ " + time +" ]: adding " + temp[1] +" with deadline " + temp[2] +" and duration " + temp[3]);
                } else if(temp[0].equals("run")){
                    time += Integer.parseInt(temp[1]);
                    totalTime += Integer.parseInt(temp[1]);
                    String current;
                    while(time >= 0 && !schedule.isEmpty()){
                        current = schedule.min().name;
                        time -= schedule.min().duration;
                        StdOut.println("[ " + realTime + " ]: working on " + current + " with deadline " + schedule.min().deadline + " and duration " + schedule.min().duration);
                        workTime = realTime;
                        realTime += schedule.min().duration;
                        if(realTime > totalTime){
                            realTime = realTime - (realTime - totalTime);
                        }
                        if(time >= 0){
                            if(schedule.min().deadline >= realTime) {
                                StdOut.println("[ " + realTime + " ]: done with " + current + " on time!");
                                schedule.delMin();
                            }else{
                                StdOut.println("[ " + realTime + " ]: done with " + current + ", but completed it late!");
                                schedule.delMin();
                            }
                        }else{
                            String tempName = schedule.min().name;
                            int tempDur = schedule.min().duration - (realTime - workTime);
                            int tempDea = schedule.min().deadline;
                            StdOut.println("[ " + realTime + " ]: time is up! adding back " + tempName + " with deadline " + tempDea + " and duration " + tempDur);
                            schedule.delMin();
                            schedule.insert(new Activity(tempName, tempDea, tempDur));
                        }
                    }
                    time = 0;

                }else{
                    throw new RuntimeException("Improper input configuration");
                }
            }
            if(schedule.isEmpty()){
                StdOut.println("\nFinished all jobs!");
            }else{
                int counter = 1;
                StdOut.println("\nNever finished the following jobs:");
                while(!schedule.isEmpty()){
                    String tempName = schedule.delMin().name;
                    StdOut.println(counter + ". " + tempName);
                }
            }
        }
    }

    /**
     * <p>
     * This helper class stores an activity's information.
     * </p>
     */
    private static class Activity implements Comparable<Activity> {

        // Class attributes
        String name;
        int deadline;
        int duration;

        /**
         * This creates a new activity.
         *
         * @param name
         *            Activity's name
         * @param deadline
         *            Activity's deadline
         * @param duration
         *            Activity's duration
         */
        public Activity(String name, int deadline, int duration) {
            this.name = name;
            this.deadline = deadline;
            this.duration = duration;
        }

        /**
         * This implements the {@link Comparable#compareTo(Object)} method to check the ordering of two objects.
         *
         * @param o
         *            Object to be compared.
         *
         * @return {@code < 0} if this activity is less than {@code o}, {@code > 0} if this activity is greater than
         *         {@code o}, and {@code 0} if they are equal.
         */
        @Override
        public int compareTo(Activity o) {
            return Integer.compare(this.deadline, o.deadline); // compare using deadlines
        }

        /**
         * This overrides the default {@link Object#equals(Object)} method to perform an equality test on two objects.
         *
         * @param o
         *            Object to be compared.
         *
         * @return {@code true} if all the fields are equal, {@code false} otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Activity activity = (Activity) o;
            return deadline == activity.deadline && duration == activity.duration && name.equals(activity.name);
        }

        /**
         * This overrides the default {@link Object#hashCode()} method to produce a hash code for the node.
         *
         * @return The hash code associated with the object.
         */
        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + deadline;
            result = 31 * result + duration;
            return result;
        }

        /**
         * This overrides the default {@link Object#toString()} method to produce a string representation of a node.
         *
         * @return A string representation of the stack.
         */
        @Override
        public String toString() {
            return name + " with deadline " + deadline + " and duration " + duration;
        }
    }
}