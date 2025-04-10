package com.ramonkaizer.skinstore.rest;

import com.ramonkaizer.skinstore.domain.dto.request.PagamentoRequest;
import com.ramonkaizer.skinstore.domain.dto.response.PagamentoResponse;
import com.ramonkaizer.skinstore.domain.dto.response.PedidoResponse;
import com.ramonkaizer.skinstore.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{pedidoId}/pagar")
    public ResponseEntity<PagamentoResponse> pagarPedido(@PathVariable Long pedidoId, @RequestBody PagamentoRequest request) {
        PagamentoResponse response = service.pagarPedido(pedidoId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoResponse> getPedidoId(@PathVariable Long pedidoId) {
        PedidoResponse response = service.getPedidoId(pedidoId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> getPedidos() {
        List<PedidoResponse> response = service.getPedidos();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
