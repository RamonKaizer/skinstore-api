package com.ramonkaizer.skinstore.dto.response;

import com.ramonkaizer.skinstore.enums.Categoria;
import com.ramonkaizer.skinstore.enums.Raridade;
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
