package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.exception.IdNotFoundException;
import com.barclays.LibrarySystemAPI.model.Genre;
import com.barclays.LibrarySystemAPI.model.Movie;
import com.barclays.LibrarySystemAPI.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {


    MovieRepository movieRepository;

    @Override
    public  List<Movie> searchMovieByTitle(String title){
       return movieRepository.searchByTitle(title);
    }

    @Override
    public List<Movie> searchMovieByDirectorContaining(String director){
        log.debug("director name: "+ director);
        List<Movie> movies = movieRepository.searchMovieByDirectorContaining(director);
        log.debug("movies size "+movies.size());
        return movies;
    }

    @Override
    public List<Movie> searchMovieByGenre(Genre genre){
        return  movieRepository.searchMovieByGenre(genre);
    }

    @Override
    public List<Movie> findAllMovies(){
        List<Movie> movies = new ArrayList<>();
        Iterable<Movie> moviesIts = movieRepository.findAll();
        moviesIts.forEach(movies::add);
        return movies;
    }

    @Override
    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    @Override
    public void  deleteMovie(Long id){
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Movie Id not found "));
         movieRepository.deleteById(movie.getId());
    }

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

}
