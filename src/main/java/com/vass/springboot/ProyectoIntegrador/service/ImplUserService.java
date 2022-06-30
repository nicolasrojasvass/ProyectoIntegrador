package com.vass.springboot.ProyectoIntegrador.service;

import com.vass.springboot.ProyectoIntegrador.model.User;
import com.vass.springboot.ProyectoIntegrador.repository.IUserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImplUserService implements IUserService {
    private final IUserRepository iUserRepository;
    private final String PASS = "**********";

    public ImplUserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public User createUser(User user) {
        User userSaved = iUserRepository.save(user);
        userSaved.setPassword(PASS);
        return userSaved;
    }

    @Override
    public Optional<User> findByIdUser(String idUser) {
        try {
            Optional<User> user = iUserRepository.findById(Long.parseLong(idUser));
            if (user.isPresent())
                user.get().setPassword(PASS);
            return user;
        } catch (NumberFormatException e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        List<User> listUsers = iUserRepository.findAll();
        listUsers.stream().peek(user -> user.setPassword("*****")).collect(Collectors.toList());
        return (listUsers != null && !listUsers.isEmpty()) ? Optional.of(listUsers) : Optional.empty();
    }

    @Override
    public boolean deleteUser(String idUser) {
        try {
            Long id = Long.parseLong(idUser);
            iUserRepository.deleteById(id);
            Optional<User> user = iUserRepository.findById(id);
            return user.isPresent() ? false : true;
        } catch (NumberFormatException | EmptyResultDataAccessException e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public Optional<User> updateUser(String idUser, User userUpdated) {
        Optional<User> user = iUserRepository.findById(Long.parseLong(idUser));
        if (user.isPresent()) {
            user.get().setEmail(userUpdated.getEmail());
            user.get().setFirst_name(userUpdated.getFirst_name());
            user.get().setLast_name(userUpdated.getLast_name());
            user.get().setUsername(userUpdated.getUsername());
            user.get().setPassword(userUpdated.getPassword());
            Optional.of(iUserRepository.save(user.get()));
            user.get().setPassword(PASS);
            return Optional.of(user.get());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.of(iUserRepository.findByUsername(username).get(0));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        try {
            String pass = Base64.getEncoder().encodeToString(password.getBytes());
            return Optional.of(iUserRepository.findByUsernameAndPassword(username, pass).get(0));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
