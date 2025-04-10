package com.ramonkaizer.skinstore.service;

import com.ramonkaizer.skinstore.domain.entity.Pagamento;
import com.ramonkaizer.skinstore.domain.entity.Pedido;
import com.ramonkaizer.skinstore.domain.enums.StatusPagamento;
import com.ramonkaizer.skinstore.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PagamentoService {

    private final PagamentoRepository repository;

    public void criaPagamento(StatusPagamento statusPagamento, Pedido pedido) {
        Pagamento pagamento = Pagamento.builder()
                .dataPagamento(LocalDateTime.now())
                .status(statusPagamento)
                .pedido(pedido)
                .build();

        repository.save(pagamento);
    }
}
