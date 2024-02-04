package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.model.Book;
import com.barclays.LibrarySystemAPI.model.Genre;
import com.barclays.LibrarySystemAPI.model.Movie;
import com.barclays.LibrarySystemAPI.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceImplUnitTest {



    @InjectMocks
    MovieServiceImpl movieService;

    @MockBean
    MovieRepository movieRepository;

    List<Movie> movieList;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "Inception", "Leonardo DiCaprio, Ellen Page", "Christopher Nolan", "Christopher Nolan","2010-07-16", true ,4, Genre.ROMANCE,8.8));
        movieList.add(new Movie(2L, "The Shawshank Redemption", "Tim Robbins, Morgan Freeman", "Christopher Nolan", "Frank Darabont","1994-09-23", true ,5,Genre.ROMANCE,5));
        movieList.add(new Movie(3L, "Pulp Fiction", "John Travolta, Uma Thurman", "CQuentin Tarantino", "Quentin Tarantino","2008-07-18", true , 6, Genre.SCIENTIFIC,9.1));
        movieList.add(new Movie(4L, "ISWIS", "Jola Ayeye, FK Abudu", "Tomi Agbaje", "Micheal Jackson","2012-07-15", true , 6, Genre.FICTION,9.8));
    }

    @Test
   public void searchMovieByTitle() {
        String title = "Inception";

        List<Movie> expectedMovie = movieList.stream()
                .filter(movie -> "Inception".equals(movie.getTitle()))
                .collect(Collectors.toList());

        //Movie  expectedMovie = movieList.get(0);
        when(movieRepository.searchByTitle(title)).thenReturn(expectedMovie);
         List<Movie> actualMovie = movieService.searchMovieByTitle(title);
         assertEquals(expectedMovie.get(0).getId(),actualMovie.get(0).getId());
   }

    @Test
    void searchMovieByAuthorContaining() {
        String directorName ="Christopher";
        List<Movie> actualMovie = movieList.stream()
                .filter(movie -> "Christopher".equals(movie.getDirector()))
                .collect(Collectors.toList());


        when(movieRepository.searchMovieByDirectorContaining(directorName)).thenReturn(actualMovie);
        List<Movie> expectedMovie= movieService.searchMovieByDirectorContaining(directorName);
        assertEquals(expectedMovie, actualMovie);
    }

    @Test
    void searchMovieByGenre() {
        String genreString = "ROMANCE";
        Genre genre = Genre.valueOf(genreString);
          List<Movie> actualGenre = movieList.stream()
                  .filter(movie -> genre.equals(movie.getGenre()))
                  .collect(Collectors.toList());


          when(movieRepository.searchMovieByGenre(genre)).thenReturn(actualGenre );
       List<Movie> expectedGenre= movieService.searchMovieByGenre(genre);
          assertEquals(actualGenre, expectedGenre);
    }
    @Test
    void findAllMovies() {

       List<Movie> actualMovieSize = movieList;

       when(movieRepository.findAll()).thenReturn(actualMovieSize);

       List<Movie>  expectedMovieSize = movieService.findAllMovies();

       assertEquals(actualMovieSize, expectedMovieSize);

    }

    @Test
    void deleteMovie(){
        int expectedMovieLengthAfterDeletion = 3;


        Movie movieToDelete = movieList.get(3);// Assuming you have a Movie class

        when(movieRepository.findById(movieToDelete.getId())).thenReturn(Optional.of(movieToDelete));
        doNothing().when(movieRepository).deleteById(movieToDelete.getId());
        movieList.remove(movieToDelete);
        movieService.deleteMovie(movieToDelete.getId());
        int actualMovieList = movieList.size();
        assertEquals(expectedMovieLengthAfterDeletion, actualMovieList);

    }

    @Test
   void  save(){

        Movie movie1= new Movie(5L, "Moment with Toke", "Toke Makiwa", "Abimbola Oguns", "Abimbola Oguns","2023-07-15", true ,5,  Genre.FICTION,5);
        when(movieRepository.save(movie1)).thenReturn(movie1);
        Movie savedMovie= movieService.save(movie1);
        assertEquals(movie1.getId(), savedMovie.getId());
    }

}