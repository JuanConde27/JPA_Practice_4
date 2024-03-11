package com.taller_4.taller_4.Mappers;

import com.taller_4.taller_4.Dtos.BookDTO;
import com.taller_4.taller_4.Models.BookModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toBookDTO(BookModel bookModel);

    List<BookDTO> toBookDTOs(List<BookModel> bookModels);

    @InheritConfiguration
    BookModel toBookModel(BookDTO bookDTO);
}
