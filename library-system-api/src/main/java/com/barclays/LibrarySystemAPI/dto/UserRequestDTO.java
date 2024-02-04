package com.barclays.LibrarySystemAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String name;
    private AddressDTO address;
    private String phoneNumber;
    private String email;
}

