package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.exception.IdNotFoundException;
import com.barclays.LibrarySystemAPI.model.Book;
import com.barclays.LibrarySystemAPI.model.Genre;
import com.barclays.LibrarySystemAPI.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;
    @Override
    public List<Book> searchBookByTitle(String title){
        return bookRepository.findByTitle(title);
   }


    @Override
    public List<Book> findAllBooks(){
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().forEach(bookList :: add);
        return bookList;

    }


    @Override
    public List<Book> searchByAuthor(String authorName){
        log.debug("author name :" + authorName);
        List<Book> books = bookRepository.searchByAuthorNameContaining(authorName);
        log.debug("books size "+books.size());
        return books;
    }

    @Override
    public List<Book> searchBookByGenre(Genre genre){
        log.debug("genre type :" + genre);
        List<Book> books = bookRepository.searchBookByGenre(genre);
        log.debug("books size "+ books.size());
        return books;
    }



    @Override
    public Book save(Book book){
        return bookRepository.save(book);
    }



    @Override
    public void  deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Book Id not found "));
        bookRepository.deleteById(book.getId());
    }


    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
