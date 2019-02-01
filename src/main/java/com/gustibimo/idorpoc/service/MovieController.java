package com.gustibimo.idorpoc.service;

import com.gustibimo.idorpoc.entity.Movie;
import com.gustibimo.idorpoc.util.IDORUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@EnableAutoConfiguration
public class MovieController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    /**
     * Demo backend data
     */
    private final List<Movie> movies = new ArrayList<>();
    public MovieController() {
        movies.add(new Movie("18298","Venom", 2018, "Marvel"));
        movies.add(new Movie("14325","Avengers", 2014, "Marvel"));
        movies.add(new Movie("15287","Toy Story 3", 2015, "Pixar"));
    }

    /**
     * Service to list all available movies
     *
     * @return The collection of movies ID and name as JSON response
     */
    @RequestMapping(value = "/movies", method = GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Map<String, String> listAllMovies () {
        Map<String, String> result = new HashMap<>();

        try {
            movies.forEach(m -> {
                try {
                    String frontEndId = IDORUtil.computeFrontEndIdentifier(m.getBackendId());
                    result.put(frontEndId, m.getName());
                } catch (Exception e) {
                    LOGGER.error("Error during generate ID  for real id {} : {}", m.getBackendId());
                }
            });
        } catch (Exception e){
            result.clear();
            LOGGER.error("Error during processing", e);
        }

        return result;
    }

    /**
     * Service to obtain the information on a specific movie
     *
     * @param id Movie identifier from a front end point of view
     * @return The movie object as JSON response
     */
    @RequestMapping(value = "/movies/{id}", method = GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Movie obtainMovieName(@PathVariable("id") String id) {

        //Search for the wanted movie information using Front End Identifier
        Optional<Movie> movie = this.movies.stream().filter(m -> {
            boolean match;
            try {
                //Compute the front end ID for the current element
                String frontEndId = IDORUtil.computeFrontEndIdentifier(m.getBackendId());
                //Check if the computed ID match the one provided
                match = frontEndId.equals(id);
            } catch (Exception e) {
                //Ensure that in case of error no item is returned
                match = false;
                LOGGER.error("Error during processing", e);
            }
            return match;
        }).findFirst();

        //We have marked the Backend Identifier class field as excluded from the serialization
        //So we can sent the object to front end through the serializer
        return movie.get();
    }

}
