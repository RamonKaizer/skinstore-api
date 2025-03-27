package com.ramonkaizer.skinstore.dto.request;

import com.ramonkaizer.skinstore.enums.Categoria;
import com.ramonkaizer.skinstore.enums.Raridade;
import lombok.Getter;

@Getter
public class SkinRequest {
    private String nome;
    private Double preco;
    private String imagem;
    private Raridade raridade;
    private String floatValue;
    private Categoria categoria;
    private boolean ofertaSemana;
}
