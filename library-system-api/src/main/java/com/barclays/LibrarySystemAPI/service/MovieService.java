package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.model.Genre;
import com.barclays.LibrarySystemAPI.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> searchMovieByTitle(String title);

    List<Movie> searchMovieByDirectorContaining(String director);

    List<Movie> searchMovieByGenre(Genre genre);

    List<Movie> findAllMovies();

    Movie save(Movie movie);

    void  deleteMovie(Long id);
}
