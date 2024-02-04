package com.barclays.LibrarySystemAPI.controller;

import com.barclays.LibrarySystemAPI.dto.ReserveDTO;
import com.barclays.LibrarySystemAPI.model.ReservedItem;
import com.barclays.LibrarySystemAPI.service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@Slf4j
public class ReserveController {
    private ReserveService reserveService;

    @PostMapping("/reserve")
    public ReservedItem reserveItem(@RequestBody  ReserveDTO  reserveDTO){
        log.debug(String.valueOf(reserveDTO));
        return reserveService.save( reserveDTO);

    }

    @GetMapping("/user")
    public List<ReservedItem> getAllReservations(){
        return reserveService.getAllReservations();

    }

    @Autowired
    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }
}
