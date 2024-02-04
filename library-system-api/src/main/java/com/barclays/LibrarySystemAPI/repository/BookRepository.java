package com.barclays.LibrarySystemAPI.repository;

import com.barclays.LibrarySystemAPI.model.Book;
import com.barclays.LibrarySystemAPI.model.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

   @Query("SELECT new Book (b.id, b.title, b.author,b.genre,b.quantity, b.isAvailable) FROM Book b WHERE b.title LIKE %:title%")
    List<Book> findByTitle( String title);



    List<Book> findAll();

    List<Book> searchByAuthorNameContaining(String authorName);
    List<Book> searchBookByGenre(Genre genre);

}
