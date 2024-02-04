package com.barclays.LibrarySystemAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(
            name = "movie_seq",
            sequenceName = "movie_seq",
            initialValue = 1,
            allocationSize = 1

    )
    private  Long id;
    private String title;
    private String leadActors;
    private String director;
    private String screenWriter;
    private String releaseDate;
    private boolean isAvailable;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private double rating;
}
