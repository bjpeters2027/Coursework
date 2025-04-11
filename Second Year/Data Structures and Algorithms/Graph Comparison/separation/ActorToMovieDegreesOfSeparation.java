/*
 * ActorToMovieDegreeOfSeparation.java
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
import wpialgs.sixdegrees.utils.Movie;

/**
 * Creates an Actor-Movie graph where:
 * <ul>
 * <li>Vertices: Actor and Movie names
 * <li>Edges: an Actor is connected to a Movie, iff he or she appeared in that movie
 * </ul>
 */
public class ActorToMovieDegreesOfSeparation extends AbstractDegreesOfSeparation {

    /**
     * This allows us to compute the degrees of separation for actors/movies in Hollywood using "Kevin Bacon" as the
     * source vertex.
     */
    public ActorToMovieDegreesOfSeparation() {
        super();
    }

    /**
     * This allows us to compute the degrees of separation for actors/movies in Hollywood using the specified source
     * vertex.
     *
     * @param sourceVertex
     *            Actor/Movie that will be serving as the source of our search.
     */
    public ActorToMovieDegreesOfSeparation(String sourceVertex) {
        super(sourceVertex);
    }

    /**
     * Using the actors and movies maps, create an Actor-Movie graph where:
     * <ul>
     * <li>Vertices: Actor and Movie names
     * <li>Edges: an Actor is connected to a Movie, iff he or she appeared in that movie
     * </ul>
     */
    @Override
    public void createGraph() {
        for(String movie : myMovies.keys()) {
            Iterable<Actor> acts = myMovies.get(movie).getActors();
            for(Actor act1 : acts){
                myG.addVertex(act1.getName());
                Iterable<Movie> movies = act1.getMovies();
                for(Movie movie1 : movies){
                    myG.addVertex(movie1.name);
                    myG.addEdge(act1.getName(), movie1.name);
                }
            }
        }
    }

    /**
     * Creates a frequency chart containing statistics about the source's distance number.
     */
    public void createFrequencyChart() {
        for(Vertex vert : myG.getVertices()){
            if(myActors.contains(vert.name)){
                if(vert.distance != Integer.MAX_VALUE) {
                    myHistogram.record(vert.distance / 2);
                }else{
                    myHistogram.record(Integer.MAX_VALUE);
                }
            }
        }
    }

    /**
     * Computes the Hollywood number for the source.
     *
     * @return Average degrees of separation of all the actors / movies.
     */
    @Override
    public double computeHollywoodNumber() {
        double sum = 0;
        double numOf = 0;
        for(Vertex vert : myG.getVertices()){
            if(vert.distance != Integer.MAX_VALUE && myActors.contains(vert.name)) {
                sum += (double) vert.distance / 2;
                numOf++;

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
                    sb.append(dest.distance / 2).append(".\n\n");

                    int counter = 1;
                    while (dest != start) {
                        sb.append(counter).append(". ").append(dest.name).append(" was in \"").append(dest.predecessor)
                                .append("\" with ").append(dest.predecessor.predecessor).append(".\n");
                        dest = dest.predecessor.predecessor;
                        counter++;
                    }
                }
            }
        }

        return sb.toString();
    }
}