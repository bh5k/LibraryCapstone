package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.dto.ReserveDTO;
import com.barclays.LibrarySystemAPI.exception.ItemNotFoundException;
import com.barclays.LibrarySystemAPI.exception.OutOfQuantityException;
import com.barclays.LibrarySystemAPI.model.Book;
import com.barclays.LibrarySystemAPI.model.ItemType;
import com.barclays.LibrarySystemAPI.repository.BookRepository;
import com.barclays.LibrarySystemAPI.repository.ReservationStrategy;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@NoArgsConstructor
@Primary
public class BookReservationStrategy implements ReservationStrategy {
    BookRepository bookRepository;

    @Autowired
    public BookReservationStrategy(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void reserve(ReserveDTO reserveDTO) {


        List<Book> books = bookRepository.findByTitle(reserveDTO.getTitle());

        if (books.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }


        if (!(books.get(0).getQuantity()>0)){

            throw new OutOfQuantityException("This Movie is out of Quantity in the Library");
        }
        else {
            //books.stream().findFirst()//better way to find first item in the arraylist
            books.get(0).setQuantity((books.get(0).getQuantity())-1);

            bookRepository.save(books.get(0));
        }
    }

}
