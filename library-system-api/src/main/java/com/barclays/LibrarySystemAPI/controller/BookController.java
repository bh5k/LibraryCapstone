package com.barclays.LibrarySystemAPI.controller;

import com.barclays.LibrarySystemAPI.model.Book;
import com.barclays.LibrarySystemAPI.model.Genre;
import com.barclays.LibrarySystemAPI.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class BookController {

    BookService bookService;

    @GetMapping("/book")
    public  List<Book> searchBookByTitle(@RequestParam("title") String title){
        title=  title.substring(0,1).toUpperCase() + title.substring(1);
        return bookService.searchBookByTitle(title);
    }


    @GetMapping("/books")
    public List<Book> findAllBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping("/book/author")
    public List<Book> searchByAuthor(@RequestParam("name") String authorName){

        return bookService.searchByAuthor(authorName);
    }

    @GetMapping("book/genre")
    public List<Book> searchByGenre(@RequestParam("genre") Genre genre){
        return bookService.searchBookByGenre(genre);
    }

    @PostMapping("/book/create")
    public Book createBook(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/book/delete/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }





    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }






}
