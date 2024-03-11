package com.taller_4.taller_4.Services;

import com.taller_4.taller_4.Dtos.BookDTO;

public interface IBookService {
    Object create(Long id, BookDTO bookDTO) throws Exception;
    Object findByUserId(Long id) throws Exception;
}
