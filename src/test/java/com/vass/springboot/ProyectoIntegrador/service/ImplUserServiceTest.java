package com.vass.springboot.ProyectoIntegrador.service;

import com.vass.springboot.ProyectoIntegrador.model.User;
import com.vass.springboot.ProyectoIntegrador.repository.IUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


class ImplUserServiceTest {

    ImplUserService implUserService;
    IUserRepository iUserRepository;

    private User user;

    @BeforeEach
    void setUp() {
        System.out.println("Inicio Test");
        iUserRepository = Mockito.mock(IUserRepository.class);
        implUserService =  new ImplUserService(iUserRepository);
        user = new User();
        user.setIdUser(10L);
        user.setLast_name("Nicolas");
        user.setLast_name("Rojas");
        user.setEmail("nicolas.rojas@vasslatam.com");
        user.setUsername("nicolas.rojas");
        user.setPassword("123456789");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Fin Test");
    }

    @Test
    void createUser() {
        implUserService.createUser(user);
        verify(iUserRepository).save(user);
    }

    @Test
    void findByIdUser() {
        verify(iUserRepository).findById(user.getIdUser());
    }

    @Test
    void getAllUsers() {
        verify(iUserRepository).findAll();
    }

    @Test
    void deleteUser() {
        verify(iUserRepository).deleteById(user.getIdUser());
    }

    @Test
    void updateUser() {
        user.setFirst_name("Pepito");
        user.setLast_name("Lopez");
        implUserService.updateUser(String.valueOf(user.getIdUser()), user);
        verify(iUserRepository).save(user);
    }
}