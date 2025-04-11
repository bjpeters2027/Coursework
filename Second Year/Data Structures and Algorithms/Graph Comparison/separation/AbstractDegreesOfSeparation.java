/*
 * AbstractDegreesOfSeparation.java
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
package wpialgs.hw04.separation;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import wpialgs.sixdegrees.graphs.UndirectedGraph;
import wpialgs.sixdegrees.graphs.Vertex;
import wpialgs.sixdegrees.separation.DegreesOfSeparation;

/**
 * This abstract class implements the {@link #traverseBFS(UndirectedGraph, Vertex)} method.
 *
 * @version 1.0
 */
public abstract class AbstractDegreesOfSeparation extends DegreesOfSeparation {

    /**
     * This allows us to compute the degrees of separation for actors/movies in Hollywood using "Kevin Bacon" as the
     * source vertex.
     */
    public AbstractDegreesOfSeparation() {
        super();
    }

    /**
     * This allows us to compute the degrees of separation for actors/movies in Hollywood using the specified source
     * vertex.
     *
     * @param sourceVertex
     *            Actor/Movie that will be serving as the source of our search.
     */
    public AbstractDegreesOfSeparation(String sourceVertex) {
        super(sourceVertex);
    }

    /**
     * Traverse the graph using breadth-first search on {@code g} from {@code source}
     * <p>
     * If source is not in the {@code UndirectedGraph}, then the traversal will do nothing.
     *
     * @param g
     *            {@code UndirectedGraph} that should be initialized and all vertices must have distance set to
     *            {@link Vertex#INFINITY}. After traversal, {@code distance} and {@code predecessor} fields will be set.
     * @param source
     *            the {@link Vertex} from which to begin the traversal
     */
    public final void traverseBFS(UndirectedGraph g, Vertex source) {
        int dist = 0;
        Queue<Vertex> q = new Queue<>();
        ST<Vertex, Boolean> visited = new ST<>();
        source.distance = 0;
        visited.put(source, true);
        q.enqueue(source);

        while(!q.isEmpty()){
            Vertex temp = q.dequeue();
            for(Vertex v : g.adjacentTo(temp.name)){
                if(!visited.contains(v) || !visited.get(v)){
                    v.distance = temp.distance +1;
                    v.predecessor = temp;
                    visited.put(v, true);
                    q.enqueue(v);
                }
            }
            dist++;
        }
    }
}