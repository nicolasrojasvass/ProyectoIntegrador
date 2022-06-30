package com.vass.springboot.ProyectoIntegrador.repository;

import com.vass.springboot.ProyectoIntegrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);

    List<User> findByUsernameAndPassword(String username, String password);
}
