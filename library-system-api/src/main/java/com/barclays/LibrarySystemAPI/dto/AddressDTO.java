package com.barclays.LibrarySystemAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String lineOne;
    private String city;
    private String postCode;
    private String country;
}
