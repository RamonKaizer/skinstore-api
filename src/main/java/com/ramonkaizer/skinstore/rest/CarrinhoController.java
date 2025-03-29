package com.ramonkaizer.skinstore.rest;

import com.ramonkaizer.skinstore.service.CarrinhoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrinho")
@AllArgsConstructor
public class CarrinhoController {

    private final CarrinhoService service;

    @PostMapping("/inserir-skin/{id}")
    public ResponseEntity<Void> inserirItem(@PathVariable Long skinId) {
        service.inserirSkin(skinId);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
