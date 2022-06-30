package com.vass.springboot.ProyectoIntegrador.model;

import com.vass.springboot.ProyectoIntegrador.controller.dto.UserDTO;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    private String first_name;
    private String last_name;
    private String email;
    private String username;

    private String password;


    public User(String first_name, String last_name, String email, String username, String password) {
        String pass = Base64.getEncoder().encodeToString(password.getBytes());
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.password = pass;
    }

    public User() {
    }

    public User(UserDTO userDTO) {
        this(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword());
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name.trim();
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
