package com.ramonkaizer.skinstore.rest;

import com.ramonkaizer.skinstore.dto.request.UserRequest;
import com.ramonkaizer.skinstore.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest) {
        service.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
