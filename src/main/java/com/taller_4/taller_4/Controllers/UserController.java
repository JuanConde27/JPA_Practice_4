package com.taller_4.taller_4.Controllers;

import com.taller_4.taller_4.Dtos.ResponseDTO;
import com.taller_4.taller_4.Dtos.UserDTO;
import com.taller_4.taller_4.Repositories.IUserRepository;
import com.taller_4.taller_4.Services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    private final IUserService userService;

    private final IUserRepository userRepository;

    public UserController(IUserService userService, IUserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            Object registeredUser = userService.register(userDTO);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            Object loggedUser = userService.login(userDTO);
            return new ResponseEntity<>(loggedUser, HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(userRepository.findByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        try {
            return new ResponseEntity<>(userService.update(id, updatedUserDTO), HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            ResponseDTO errorResponse = ResponseDTO.builder().message(e.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
