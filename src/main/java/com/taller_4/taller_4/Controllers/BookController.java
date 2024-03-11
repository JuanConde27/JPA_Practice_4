package com.taller_4.taller_4.Controllers;


import com.taller_4.taller_4.Dtos.BookDTO;
import com.taller_4.taller_4.Dtos.ResponseDTO;
import com.taller_4.taller_4.Repositories.IBookRepository;
import com.taller_4.taller_4.Services.IBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BookController {

    private final IBookService bookService;

    private final IBookRepository bookRepository;

    public BookController(IBookService bookService, IBookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> create(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        try {
            Object registeredBook = bookService.create(id, bookDTO);
            return new ResponseEntity<>(registeredBook, HttpStatus.CREATED);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(bookRepository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable String title) {
        try {
            return new ResponseEntity<>(bookRepository.findByTitle(title), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByAuthor/{author}")
    public ResponseEntity<?> findByAuthor(@PathVariable String author) {
        try {
            return new ResponseEntity<>(bookRepository.findByAuthor(author), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByStatus/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable String status) {
        try {
            return new ResponseEntity<>(bookRepository.findByStatus(status), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable Long userId) {
        try {
            return new ResponseEntity<>(bookRepository.findByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }



}
