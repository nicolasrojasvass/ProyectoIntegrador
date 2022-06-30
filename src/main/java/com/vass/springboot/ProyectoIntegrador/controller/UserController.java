package com.vass.springboot.ProyectoIntegrador.controller;

import com.vass.springboot.ProyectoIntegrador.controller.dto.UserDTO;
import com.vass.springboot.ProyectoIntegrador.model.User;
import com.vass.springboot.ProyectoIntegrador.security.JWTToken;
import com.vass.springboot.ProyectoIntegrador.security.dto.UserJwtDTO;
import com.vass.springboot.ProyectoIntegrador.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v4/users")
public class UserController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        Optional<User> user = iUserService.findByUsernameAndPassword(username, pwd);
        if (user.isPresent()){
            String token = JWTToken.getJWTToken(username);
            return new ResponseEntity<>(new UserJwtDTO(username, token), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.OK);
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(iUserService.createUser(new User(userDTO)), HttpStatus.OK);
    }

    @GetMapping("/findById/{idUser}")
    public ResponseEntity<Object> findById(@PathVariable String idUser) {
        Optional<User> user = iUserService.findByIdUser(idUser);
        return new ResponseEntity<>(user.isPresent() ? user.get() : "No se ha encontrado el usuario con el id: " + idUser, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Optional<List<User>>> getAllUsers() {
        return new ResponseEntity<>(iUserService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{idUser}")
    public ResponseEntity<String> deleteUser(@PathVariable String idUser) {
        boolean isDeleted = iUserService.deleteUser(idUser);
        return new ResponseEntity<>(isDeleted ? "Se ha eliminado el usuario correctamente" : "No se ha podido eliminar el usuario con el id: " + idUser, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{idUser}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable String idUser, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(iUserService.updateUser(idUser, new User(userDTO)), HttpStatus.OK);
    }
}
