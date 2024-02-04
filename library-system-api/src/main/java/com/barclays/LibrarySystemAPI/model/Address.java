package com.barclays.LibrarySystemAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "address_t")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_t_seq")
    @SequenceGenerator(
            name = "address_t_seq",
            sequenceName = "address_t_seq",
            initialValue = 1,
            allocationSize = 1)
    private  Long id;
    private String lineOne;
    private String city;
    private String postCode;
    private String country;
}
