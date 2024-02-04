package com.barclays.LibrarySystemAPI.dto;

import com.barclays.LibrarySystemAPI.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDTO {
    private ItemType itemType;
    private String  title;
    private long userId;
    private String date;



}
