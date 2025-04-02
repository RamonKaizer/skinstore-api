package com.ramonkaizer.skinstore.domain.dto.response;

import com.ramonkaizer.skinstore.domain.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoResponse {

    private StatusPagamento statusPagamento;
}
