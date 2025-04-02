package com.ramonkaizer.skinstore.rest;

import com.ramonkaizer.skinstore.domain.dto.response.PedidoResponse;
import com.ramonkaizer.skinstore.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping()
    public ResponseEntity<PedidoResponse> criaPedido() {
        PedidoResponse response = service.criaPedido();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
