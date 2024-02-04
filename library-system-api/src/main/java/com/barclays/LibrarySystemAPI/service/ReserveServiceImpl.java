package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.dto.ReserveDTO;
import com.barclays.LibrarySystemAPI.exception.IdNotFoundException;
import com.barclays.LibrarySystemAPI.model.*;
import com.barclays.LibrarySystemAPI.repository.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class ReserveServiceImpl implements ReserveService {

     UserRepository userRepository;
     ReserveRepository reserveRepository;
     ReservationStrategy reservationStrategy;
     MovieReservationStrategy movieReservationStrategy;
     BookReservationStrategy bookReservationStrategy;
     List<Book> books= new ArrayList<>();
     @Autowired(required = false)
     Map<ItemType,ReservationStrategy> reservationStrategyMap;

     @PostConstruct
     public  void  init(){
         reservationStrategyMap = new HashMap<>();
         reservationStrategyMap.put(ItemType.MOVIE,movieReservationStrategy);
         reservationStrategyMap.put(ItemType.BOOK,bookReservationStrategy);
     }

    @Transactional
    @Override
    public ReservedItem save (ReserveDTO reserveDTO){

         reservationStrategy=reservationStrategyMap.get(reserveDTO.getItemType());

//        if(reserveDTO.getItemType().equals(ItemType.MOVIE)) {
//          //  reservationStrategy =new MovieReservationStrategy();
//
//            reservationStrategy=movieReservationStrategy;
//        }
//
//        if (reserveDTO.getItemType().equals(ItemType.BOOK) ) {
//          //  reservationStrategy = new BookReservationStrategy();
//
//            reservationStrategy=bookReservationStrategy;
//        }

        reservationStrategy.reserve(reserveDTO);

        User user = userRepository.findById(reserveDTO.getUserId()).

                orElseThrow(()-> new IdNotFoundException( "User ID not found exception"));

        ReservedItem reservedItem = new ReservedItem();
        reservedItem.setItemType(reserveDTO.getItemType());
        reservedItem.setDate(reserveDTO.getDate());
        reservedItem.setPeriod(7);
        reservedItem.setUser(user);

        return reserveRepository.save(reservedItem);
    }

    @Override
    public List<ReservedItem> getAllReservations() {
            List<ReservedItem> reservedItems = new ArrayList<>();
            Iterable<ReservedItem> reservedItemIts = reserveRepository.findAll();
            reservedItemIts.forEach(reservedItems::add);
            return reservedItems;
    }



}

