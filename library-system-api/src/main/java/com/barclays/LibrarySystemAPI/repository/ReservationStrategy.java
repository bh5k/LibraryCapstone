package com.barclays.LibrarySystemAPI.repository;

import com.barclays.LibrarySystemAPI.dto.ReserveDTO;
import org.springframework.stereotype.Component;

@Component
public interface ReservationStrategy {

        void reserve(ReserveDTO reserveDTO);
    }

