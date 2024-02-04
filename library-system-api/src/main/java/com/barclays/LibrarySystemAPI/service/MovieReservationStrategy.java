package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.dto.ReserveDTO;
import com.barclays.LibrarySystemAPI.exception.IdNotFoundException;
import com.barclays.LibrarySystemAPI.exception.ItemNotFoundException;
import com.barclays.LibrarySystemAPI.exception.OutOfQuantityException;
import com.barclays.LibrarySystemAPI.model.*;
import com.barclays.LibrarySystemAPI.repository.MovieRepository;
import com.barclays.LibrarySystemAPI.repository.ReservationStrategy;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Component
@NoArgsConstructor
public class MovieReservationStrategy implements ReservationStrategy {

    MovieRepository movieRepository;

    @Autowired
    public MovieReservationStrategy(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void reserve(ReserveDTO reserveDTO) {

            List<Movie> movies = movieRepository.searchByTitle(reserveDTO.getTitle());
            if (movies.isEmpty()) throw new ItemNotFoundException("Item not found");

            if (!(movies.get(0).getQuantity()>0)){
                //throw new ResponseStatusException(HttpStatusCode.valueOf(422),"Item not available");
                throw new OutOfQuantityException("This Movie is out of Quantity in the Library");
            }
            else {
                movies.get(0).setQuantity((movies.get(0).getQuantity())-1);
                movieRepository.save(movies.get(0));
            }

    }
}

