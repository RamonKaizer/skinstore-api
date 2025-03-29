package com.ramonkaizer.skinstore.rest;

import com.ramonkaizer.skinstore.domain.dto.request.UserCreateRequest;
import com.ramonkaizer.skinstore.domain.dto.request.UserLoginRequest;
import com.ramonkaizer.skinstore.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest request) {
        service.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        String token = service.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
