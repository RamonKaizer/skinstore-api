package com.ramonkaizer.skinstore.rest;

import com.ramonkaizer.skinstore.domain.dto.response.CarrinhoResponse;
import com.ramonkaizer.skinstore.service.CarrinhoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
@AllArgsConstructor
public class CarrinhoController {

    private final CarrinhoService service;

    @PostMapping("/inserir-skin/{id}")
    public ResponseEntity<Void> inserirSKin(@PathVariable Long id) {
        service.inserirSkin(id);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/remover-skin/{id}")
    public ResponseEntity<Void> removerSkin(@PathVariable Long id) {
        service.removerSkin(id);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<CarrinhoResponse> skinsCarrinho() {
        CarrinhoResponse response = service.getCarrinho();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
