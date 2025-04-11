/*
 * ActorToActorDegreeOfSeparation.java
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

import wpialgs.sixdegrees.graphs.Vertex;
import wpialgs.sixdegrees.utils.Actor;

/**
 * Creates an Actor-Actor graph where:
 * <ul>
 * <li>Vertices: Actor names
 * <li>Edges: two Actors are connected iff they appeared in the same movie
 * </ul>
 */
public class ActorToActorDegreesOfSeparation extends AbstractDegreesOfSeparation {

    /**
     * This allows us to compute the degrees of separation for actors in Hollywood using "Kevin Bacon" as the source
     * vertex.
     */
    public ActorToActorDegreesOfSeparation() {
        super();
    }

    /**
     * This allows us to compute the degrees of separation for actors in Hollywood using the specified source vertex.
     *
     * @param sourceVertex
     *            Actor that will be serving as the source of our search.
     */
    public ActorToActorDegreesOfSeparation(String sourceVertex) {
        super(sourceVertex);
    }

    /**
     * Using the actors and movies maps, create an Actor graph where:
     * <ul>
     * <li>Vertices: Actor names
     * <li>Edges: two Actors are connected iff they appeared in the same movie
     * </ul>
     */
    @Override
    public void createGraph() {
        // NOTE: See DegreesOfSeparation class in wpialgs.separation for where the data should be stored.
        // then iterate through movies and add edges between all the actors in that movie
        for(String movie : myMovies.keys()){
            Iterable<Actor> actors = myMovies.get(movie).getActors();
            for(Actor a1 : actors){
                myG.addVertex(a1.name);
                for(Actor a2 : actors){
                    if(!a1.equals(a2)){
                        myG.addVertex(a2.name);
                        myG.addEdge(a1.name, a2.name);
                    }
                }
            }
        }

    }

    /**
     * Creates a frequency chart containing statistics about the source's distance number.
     */
    public void createFrequencyChart() {
        // x: different distances, y: num of actors with that distance
        for(Vertex vert : myG.getVertices()){
            myHistogram.record(vert.distance);
        }
    }

    /**
     * Computes the Hollywood number for the source.
     *
     * @return Average degrees of separation of all the actors.
     */
    @Override
    public double computeHollywoodNumber() {
        double sum = 0;
        double numOf = 0;
        for(Vertex vert : myG.getVertices()){
            if(vert.distance != Integer.MAX_VALUE) {
                sum += (double) vert.distance;
                if(myActors.contains(vert.name)){
                    numOf++;
                }
            }
        }
        return sum / numOf;
    }

    /**
     * Create a string with the chain from source to specified actor or actress. If no such actor or actress, it will
     * generate the appropriate error message as a string.
     *
     * @param name
     *            for actor or actress.
     *
     * @return A string representation of the chain
     */
    @Override
    public String chainAsString(String name) {
        Vertex start = myG.getVertex(mySource);
        Vertex dest = myG.getVertex(name);

        // Check if source exists
        StringBuilder sb = new StringBuilder();
        if (start == null) {
            sb.append("Source actor/actress: ").append(mySource).append(" does not exist in our graph.\n");
        } else {
            if (dest == null) {
                sb.append("Destination actor/actress: ").append(name).append(" does not exist in our graph.\n");
            } else {
                sb.append(start.name).append(" and ").append(dest.name).append(" have a distance of ");
                if (dest.distance == Vertex.INFINITY) {
                    sb.append("infinity.\n");
                } else {
                    sb.append(dest.distance).append(".\n\n");

                    int counter = 1;
                    while (dest != start) {
                        sb.append(counter).append(". ").append(dest.name).append(" was in a movie with ")
                                .append(dest.predecessor).append(".\n");
                        dest = dest.predecessor;
                        counter++;
                    }
                }
            }
        }

        return sb.toString();
    }
}