package com.ramonkaizer.skinstore.domain.dto.request;

import com.ramonkaizer.skinstore.domain.enums.Categoria;
import com.ramonkaizer.skinstore.domain.enums.Raridade;
import lombok.Getter;

@Getter
public class SkinSaveRequest {
    private String nome;
    private Double preco;
    private String imagem;
    private Raridade raridade;
    private String floatValue;
    private Categoria categoria;
    private boolean ofertaSemana;
}
