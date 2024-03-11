package com.taller_4.taller_4.Services.Implementations;

import com.taller_4.taller_4.Dtos.BookDTO;
import com.taller_4.taller_4.Models.BookModel;
import com.taller_4.taller_4.Models.UserModel;
import com.taller_4.taller_4.Services.IBookService;
import org.springframework.stereotype.Service;
import com.taller_4.taller_4.Repositories.IBookRepository;
import com.taller_4.taller_4.Mappers.BookMapper;
import com.taller_4.taller_4.Repositories.IUserRepository;

@Service
public class BookService implements IBookService {

    private final IBookRepository bookRepository;
    private final BookMapper bookMapper;
    private final IUserRepository userRepository;

    public BookService(IBookRepository bookRepository, BookMapper bookMapper, IUserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Object create(Long id, BookDTO bookDTO) throws Exception {
        try {
            UserModel userModel = userRepository.findById(id).orElse(null);
            if (userModel == null) {
                throw new Exception("No existe un usuario con ese ID");
            }
            BookModel bookModel = bookMapper.toBookModel(bookDTO);
            if (bookRepository.findByTitle(bookModel.getTitle()) != null) {
                throw new Exception("El libro ya se encuentra registrado");
            }
            bookModel.setUser(userModel);
            bookModel = bookRepository.save(bookModel);
            return bookMapper.toBookDTO(bookModel);
        } catch (Exception e) {
            throw new Exception("Error al registrar el libro");
        }
    }

    @Override
    public Object findByUserId(Long id) throws Exception {
        try {
            UserModel userModel = userRepository.findById(id).orElse(null);
            if (userModel == null) {
                throw new Exception("No existe un usuario con ese ID");
            }
            return bookRepository.findByUserId(id);
        } catch (Exception e) {
            throw new Exception("Error al buscar los libros");
        }
    }

}
