package com.barclays.LibrarySystemAPI.repository;

import com.barclays.LibrarySystemAPI.model.Genre;
import com.barclays.LibrarySystemAPI.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Query("SELECT new Movie (m.id, m.title, m.leadActors,m.director,m.screenWriter,m.releaseDate,m.isAvailable,m.quantity,m.genre,m.rating) FROM Movie m WHERE m.title LIKE %:title%")
    List<Movie> searchByTitle(String title);

    List<Movie> searchMovieByDirectorContaining(String director);
     List<Movie> searchMovieByGenre(Genre genre);



}
