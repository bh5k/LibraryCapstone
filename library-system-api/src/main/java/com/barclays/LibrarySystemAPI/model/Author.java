package com.barclays.LibrarySystemAPI.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
    @SequenceGenerator(
            name = "author_seq",
            sequenceName = "author_seq",
            initialValue = 1,
            allocationSize = 1

    )
    private  Long id;
    private String name;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Book> books;


    public Author(String name) {
        this.name = name;
    }
}

