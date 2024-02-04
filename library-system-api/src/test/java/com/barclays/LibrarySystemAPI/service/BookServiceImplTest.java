package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.model.Author;
import com.barclays.LibrarySystemAPI.model.Book;
import com.barclays.LibrarySystemAPI.model.Genre;
import com.barclays.LibrarySystemAPI.model.Movie;
import com.barclays.LibrarySystemAPI.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class BookServiceImplTest {

    Book book;
    Author author;

    @InjectMocks
    BookServiceImpl bookService;
    @MockBean
    BookRepository bookRepository;

    List<Book> bookList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Author author= new Author("Wole Soyinka");
       Author author1 = new Author("Tolani Shoneye");


        bookList=  new ArrayList<>();
        bookList.add(new Book(1L, "Mary had a little lamb",author , Genre.FICTION,true ));
        bookList.add(new Book(2L, "Blah Blah sheep" , author1 , Genre.ROMANCE,true));
        bookList.add(new Book(3L, "Upon the old solid rock" ,author, Genre.FICTION,true));

    }


    @Test
    void searchBookByTitle() {

        String title = "Blah Blah sheep";
       List<Book>  expectedBook = Collections.singletonList(bookList.get(1)); //
        when(bookRepository.findByTitle(title)).thenReturn(expectedBook);
        List<Book> actualBook = bookService.searchBookByTitle("Blah Blah sheep");
        assertEquals(expectedBook,actualBook);
    }

    @Test
    void findAllBooks() {
        List<Book> actualMovieSize = bookList;

        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> expectedBookList= bookService.findAllBooks();
        assertEquals(actualMovieSize, expectedBookList);
    }

    @Test
    void searchByAuthor() { //wronggggg
        String authorName ="Wole";
        List<Book> actualAuthorName = bookList.stream()
                .filter(book -> "Wole".equals(book.getAuthor()))
                .collect(Collectors.toList());


        when(bookRepository.searchByAuthorNameContaining(authorName)).thenReturn(actualAuthorName);
        List<Book> expectedAuthorName= bookService.searchByAuthor("Wole");
       assertEquals(actualAuthorName, expectedAuthorName);
    }

    @Test
    void searchBookByGenre() {
        String genreString = "FICTION";
        Genre genre = Genre.valueOf(genreString);
        List<Book> actualGenre = bookList.stream()
                .filter(movie -> genre.equals(movie.getGenre()))
                .collect(Collectors.toList());


        when(bookRepository.searchBookByGenre(genre)).thenReturn(actualGenre );
        List<Book> expectedGenre= bookService.searchBookByGenre(genre);
        assertEquals(actualGenre, expectedGenre);
    }




    @Test
    void save() {
        int expectedBookListSize =4;
        Book  book1= new Book(4L, "Tayo Aina travels" ,author, Genre.FICTION,true);
        when(bookRepository.save(book1)).thenReturn(book1);
        Book savedBook= bookService.save(book1);
        assertEquals(book1.getId(), savedBook.getId());
    }

    @Test
    void deleteBook() {
        int expectedBookListSize =2;

        Book book1 = bookList.get(1);
        log.debug("id of book " + book1.getId());

        when(bookRepository.findById(book1.getId())).thenReturn(Optional.of(book1));
        doNothing().when(bookRepository).deleteById(book1.getId());
        bookList.remove(book1);
        bookService.deleteBook(book1.getId());

        int actualBookList = bookList.size();

        assertEquals(expectedBookListSize, actualBookList);

    }
}