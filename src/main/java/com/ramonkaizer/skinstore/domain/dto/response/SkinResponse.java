package com.ramonkaizer.skinstore.domain.dto.response;

import com.ramonkaizer.skinstore.domain.enums.Categoria;
import com.ramonkaizer.skinstore.domain.enums.Raridade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkinResponse {

    private Long id;
    private String nome;
    private Double preco;
    private String imagem;
    private Raridade raridade;
    private String floatValue;
    private Categoria categoria;
}
