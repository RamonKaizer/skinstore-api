package com.ramonkaizer.skinstore.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PedidoResponse {

    private List<SkinResponse> skins;
    private Double valorTotal;
    private Long idPedido;
    private String status;
}
