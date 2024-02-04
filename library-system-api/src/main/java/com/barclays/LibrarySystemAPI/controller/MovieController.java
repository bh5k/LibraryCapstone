package com.barclays.LibrarySystemAPI.controller;

import com.barclays.LibrarySystemAPI.model.Genre;
import com.barclays.LibrarySystemAPI.model.Movie;
import com.barclays.LibrarySystemAPI.service.MovieService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    MovieService movieService;
    @GetMapping("/movie/title")
    public List<Movie> searchMovieByTitle(@RequestParam("title") String title){
        title=  title.substring(0,1).toUpperCase() + title.substring(1);
        return movieService.searchMovieByTitle(title);
    }

    @GetMapping("/movie/author")
    public List<Movie> searchMovieByDirectorContaining(@RequestParam("name")String director){
        return movieService.searchMovieByDirectorContaining(director);
    }

    @GetMapping("/movie/genre")
    public List<Movie> searchMovieByGenre(@PathParam("genre") Genre genre){
        return movieService.searchMovieByGenre(genre);
    }

    @GetMapping("/movie")
    public List<Movie> findAllMovies(){
        return movieService.findAllMovies();
    }

    @PostMapping("/movie/create")
    public Movie createMovie(@RequestBody Movie movie){
        return movieService.save(movie);
    }

    @DeleteMapping("/movie/delete/{id}")
    public void deleteMovie(@PathVariable Long id){
         movieService.deleteMovie(id);
    }



    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
}
