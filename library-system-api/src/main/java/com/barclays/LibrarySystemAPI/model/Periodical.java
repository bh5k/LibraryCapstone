package com.barclays.LibrarySystemAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table (name = "periodical")
@AllArgsConstructor
@NoArgsConstructor
public class Periodical {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "periodical_seq")
    @SequenceGenerator(
            name = "periodical_seq",
            sequenceName = "periodical_seq",
            initialValue = 1,
            allocationSize = 1

    )
    private Long id;

    private String type;
    private String publicationDate;
    private String periodicalName;
}
