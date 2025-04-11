/*
 * MovieToMovieDegreeOfSeparation.java
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
 * Creates a Movie-Movie graph where:
 * <ul>
 * <li>Vertices: Movie names
 * <li>Edges: two Movies are connected iff they share an Actor
 * </ul>
 */
public class MovieToMovieDegreesOfSeparation extends AbstractDegreesOfSeparation {

    /**
     * This allows us to compute the degrees of separation for movies.
     */
    public MovieToMovieDegreesOfSeparation() {
        this("X-Men: First Class");
    }

    /**
     * This allows us to compute the degrees of separation for movies in Hollywood using the specified source movie.
     *
     * @param sourceMovie
     *            Movie that will be serving as the source of our search.
     */
    public MovieToMovieDegreesOfSeparation(String sourceMovie) {
        super(sourceMovie);
    }

    /**
     * Using the actors and movies maps, create Movie graph where:
     * <ul>
     * <li>Vertices: Movie names
     * <li>Edges: two Movies are connected iff they share an Actor
     * </ul>
     */
    @Override
    public void createGraph() {
        for(String actor : myActors.keys()){
            Iterable<Movie> movies = myActors.get(actor).getMovies();
            for(Movie movie1 : movies){
                myG.addVertex(movie1.name);
                for(Movie movie2 : movies){
                    if(!movie1.equals(movie2)){
                        myG.addVertex(movie2.name);
                        myG.addEdge(movie1.name, movie2.name);
                    }
                }
            }
        }
    }

    /**
     * Creates a frequency chart containing statistics about the source's distance number.
     */
    public void createFrequencyChart() {
        for(Vertex vert : myG.getVertices()){
            myHistogram.record(vert.distance);
        }
    }

    /**
     * Computes the Hollywood number for the source.
     *
     * @return Average degrees of separation of all the movies.
     */
    @Override
    public double computeHollywoodNumber() {
        double sum = 0;
        double numOf = 0;
        for(Vertex vert : myG.getVertices()){
            if(vert.distance != Integer.MAX_VALUE) {
                sum += (double) vert.distance;
                if(myMovies.contains(vert.name)){
                    numOf++;
                }
            }
        }
        return sum / numOf;
    }

    /**
     * Create a string with the chain from source to specified movie. If no such movie, it will generate the appropriate
     * error message as a string.
     *
     * @param name
     *            for movie.
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
            sb.append("Source movie: ").append(mySource).append(" does not exist in our graph.\n");
        } else {
            if (dest == null) {
                sb.append("Destination movie: ").append(name).append(" does not exist in our graph.\n");
            } else {
                sb.append(start.name).append(" and ").append(dest.name).append(" have a distance of ");
                if (dest.distance == Vertex.INFINITY) {
                    sb.append("infinity.\n");
                } else {
                    sb.append(dest.distance / 2).append(".\n\n");

                    int counter = 1;
                    while (dest != start) {
                        sb.append(counter).append(". ").append(dest.name).append(" was in \"").append(dest.predecessor)
                                .append("\" with ").append(dest.predecessor).append(".\n");
                        dest = dest.predecessor;
                        counter++;
                    }
                }
            }
        }

        return sb.toString();
    }
}