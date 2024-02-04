package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.model.Book;
import com.barclays.LibrarySystemAPI.model.Genre;

import java.util.List;

public interface BookService {

    List<Book> searchBookByTitle(String title);

    List<Book> findAllBooks();

    List<Book> searchByAuthor(String authorName);

    List<Book> searchBookByGenre(Genre genre);

    Book save(Book book);

    void  deleteBook(Long id);
}
