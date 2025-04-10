package com.ramonkaizer.skinstore.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoRequest {
    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    private String cvv;
}