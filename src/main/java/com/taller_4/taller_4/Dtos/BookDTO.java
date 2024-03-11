package com.taller_4.taller_4.Dtos;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String editorial;
    private String year;
    private String status;
}
