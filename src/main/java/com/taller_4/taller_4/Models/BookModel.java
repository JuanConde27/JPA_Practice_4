package com.taller_4.taller_4.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "author", nullable = false, length = 100)
    private String author;

    @Column(name = "genre", nullable = false, length = 100)
    private String genre;

    @Column(name = "editorial", nullable = false, length = 100)
    private String editorial;

    @Column(name = "year", nullable = false, length = 100)
    private String year;

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}
