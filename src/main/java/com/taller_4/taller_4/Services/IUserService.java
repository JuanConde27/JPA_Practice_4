package com.taller_4.taller_4.Services;

import com.taller_4.taller_4.Dtos.UserDTO;


public interface IUserService {
    Object register(UserDTO userDTO) throws Exception;
    Object login(UserDTO userDTO) throws Exception;
    Object update(Long id, UserDTO updatedUserDTO) throws Exception;
}
