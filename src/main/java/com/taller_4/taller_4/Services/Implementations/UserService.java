package com.taller_4.taller_4.Services.Implementations;

import com.taller_4.taller_4.Dtos.ResponseDTO;
import com.taller_4.taller_4.Dtos.UserDTO;
import com.taller_4.taller_4.Mappers.UserMapper;
import com.taller_4.taller_4.Models.UserModel;
import com.taller_4.taller_4.Repositories.IUserRepository;
import com.taller_4.taller_4.Services.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(IUserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Object register(UserDTO userDTO) throws Exception {
        try {
            if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
                throw new Exception("El correo es obligatorio");
            }

            if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
                throw new Exception("La contraseña es obligatoria");
            }

            if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty()) {
                throw new Exception("El nombre de usuario es obligatorio");
            }

            UserModel userModel = userMapper.toUserModel(userDTO);
            if (userRepository.findByEmail(userModel.getEmail()) != null) {
                throw new Exception("El correo ya se encuentra registrado");
            }

            userRepository.save(userModel);
            return ResponseDTO.builder().message("Usuario registrado exitosamente").build();
        } catch (Exception e) {
            throw new Exception("Error al registrar el usuario");
        }
    }


    @Override
    public Object login(UserDTO userDTO) throws Exception {
        try {
            UserModel userModel = userRepository.findByEmail(userDTO.getEmail());
            if (userModel == null) {
                throw new Exception("El correo no se encuentra registrado");
            }

            if (!userModel.getPassword().equals(userDTO.getPassword())) {
                throw new Exception("La contraseña es incorrecta");
            }

            String successMessage = "Inicio de sesión exitoso";
            return ResponseDTO.builder().message(successMessage).build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Object update(Long id, UserDTO updatedUserDTO) throws Exception {
        try {
            UserModel userModel = userRepository.findById(id).orElse(null);
            if (userModel == null) {
                throw new Exception("No existe un usuario con ese ID");
            }

            if (updatedUserDTO.getEmail() != null && !updatedUserDTO.getEmail().isEmpty()) {
                userModel.setEmail(updatedUserDTO.getEmail());
            }

            if (updatedUserDTO.getPassword() != null && !updatedUserDTO.getPassword().isEmpty()) {
                userModel.setPassword(updatedUserDTO.getPassword());
            }

            if (updatedUserDTO.getUsername() != null && !updatedUserDTO.getUsername().isEmpty()) {
                userModel.setUsername(updatedUserDTO.getUsername());
            }

            userModel = userRepository.save(userModel);
            return userMapper.toUserDTO(userModel);
        } catch (Exception e) {
            throw new Exception("Error al actualizar el usuario");
        }
    }
}