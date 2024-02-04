package com.barclays.LibrarySystemAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(
            name = "book_seq",
            sequenceName = "book_seq",
            initialValue = 1,
            allocationSize = 1

    )
    private  Long id;
    private String title;

    @ManyToOne (fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name ="author_id", referencedColumnName = "id", nullable = false)
    private Author author;


    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(name = "quantity")
    private int quantity;
    private boolean isAvailable;

    public Book(Long id, String title, Author author, Genre genre, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
    }
}

