package com.vass.springboot.ProyectoIntegrador.service;

import com.vass.springboot.ProyectoIntegrador.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User createUser(User user);

    Optional<User> findByIdUser(String idUser);

    Optional<List<User>> getAllUsers();

    boolean deleteUser(String idUser);

    Optional<User> updateUser(String idUser, User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
