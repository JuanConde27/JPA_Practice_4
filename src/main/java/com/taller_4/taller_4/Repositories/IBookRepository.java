package com.taller_4.taller_4.Repositories;

import com.taller_4.taller_4.Models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookRepository extends JpaRepository<BookModel, Long> {
    BookModel findByStatus(String status);
    List<BookModel> findByUserId(Long userId);
    @Query
    (value = "SELECT * FROM books WHERE title = ?1", nativeQuery = true)
    List<BookModel> findByTitle(String title);

    @Query
    (value = "SELECT * FROM books WHERE author = ?1", nativeQuery = true)
    List<BookModel> findByAuthor(String author);

}
