package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.dto.AddressDTO;
import com.barclays.LibrarySystemAPI.dto.ReserveDTO;
import com.barclays.LibrarySystemAPI.dto.UserRequestDTO;
import com.barclays.LibrarySystemAPI.model.*;
import com.barclays.LibrarySystemAPI.repository.*;
import org.hibernate.Length;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReserveServiceUnitImplTest {

    @InjectMocks
    private ReserveServiceImpl reserveService;


    @InjectMocks
    private ReserveDTO reserveDTO;



    @MockBean
    UserRepository userRepository;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    MovieRepository movieRepository;
    @MockBean
    PeriodicalRepository periodicalRepository;
    @MockBean
    private ReserveRepository reserveRepository;
    List<ReservedItem>  reservedItemList;
    List<Movie>  movieList;
    List<User>  userList;
    List<ReservedItem> reservedItems;

    List<Book>  bookList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Address address = new Address(1L,"43 Jenson street", "Nottingham", "NG6 5TY","UK");
        Address address2 = new Address(2L,"65 Broadson street", "Nottingham", "NG8 9TY","UK");
        Address address3 = new Address(3L,"21 Wilkinson street", "Nottingham", "NG3 9YY","UK");

        userList=  new ArrayList<>();
        userList.add(new User(1L, "Tolu Adetomiwa",address,"07347284950", "toluadetomiwa@gmail.com" ));
        userList.add(new User(2L, "Tise Oludayomi",address2,"07347583840", "tiseoludayomi@gmail.com" ));

        movieList =new ArrayList<>();
        movieList.add(new Movie(1L, "Inception", "Leonardo DiCaprio, Ellen Page", "Christopher Nolan", "Christopher Nolan","2010-07-16", true ,4, Genre.ROMANCE,8.8));
        movieList.add(new Movie(2L, "The Shawshank Redemption", "Tim Robbins, Morgan Freeman", "Christopher Nolan", "Frank Darabont","1994-09-23", true ,4,Genre.ROMANCE,5));
        movieList.add(new Movie(3L, "Pulp Fiction", "John Travolta, Uma Thurman", "CQuentin Tarantino", "Quentin Tarantino","2008-07-18", true ,4,  Genre.SCIENTIFIC,9.1));

//        bookList = new ArrayList<>();
//       bookList.add(new Book()) INSERT INTO BOOK (ID, title, author_id, genre,quantity,is_available) VALUES ( 500, 'Mary had a little lamb',1 , 'FICTION',3,true);
//        INSERT INTO BOOK (ID, title, author_id, genre,quantity,is_available) VALUES ( 600, 'Blah Blah sheep',2 , 'THRILLER',4,true);
//        INSERT INTO BOOK (ID, title, author_id, genre, quantity,is_available) VALUES ( 700, 'Upon the old solid rock',3, 'ACTION',3,true);
//        INSERT INTO BOOK (ID, title, author_id, genre,quantity,is_available) VALUES ( 800, 'Mirror Mirror',1 , 'FICTION',4,true);


        List<Movie> actualMovie = movieList.stream()
                .filter(movie -> "Inception".equals(movie.getTitle()))
                .collect(Collectors.toList());

        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setTitle("Inception");
        reserveDTO.setItemType( ItemType.MOVIE);
        reserveDTO.setUserId(1);
        reserveDTO.setDate("20/11/2023");






    }

//    @Test
//    void save() {
//
//        String expectedDirectorName ="Christopher Nolan";
//
//        List<Movie> actual= movieList.stream()
//                .filter(movie -> "Inception".equals(movie.getTitle()))
//                .collect(Collectors.toList());
//
//         reserveDTO = new ReserveDTO();
//        reserveDTO.setTitle("Inception");
//        reserveDTO.setItemType( ItemType.MOVIE);
//        reserveDTO.setUserId(1);
//        reserveDTO.setDate("20/11/2023");
//
//        when(userRepository.findById(reserveDTO.getUserId())).thenReturn(Optional.of(userList.get(0)));
//        when(movieRepository.searchByTitle(reserveDTO.getTitle())).thenReturn(actualMovie);
//
//        ReservedItem savedReservedItem = reserveService.save(reserveDTO);
//
//        assertEquals(savedReservedItem.getUser().getName(), userList.get(0).getName());
//
//    }
    @Test
    void getAllReservations() {

        int expectedListLength =1;
        when(reserveRepository.findAll()).thenReturn(Collections.singletonList(new ReservedItem()));
        List<ReservedItem> reservedItemList2 = reserveService.getAllReservations();
        assertEquals(expectedListLength, reservedItemList2.size());

    }

}