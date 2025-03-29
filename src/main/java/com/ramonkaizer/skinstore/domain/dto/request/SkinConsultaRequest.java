package com.ramonkaizer.skinstore.domain.dto.request;

import com.ramonkaizer.skinstore.domain.enums.Categoria;
import com.ramonkaizer.skinstore.domain.enums.Raridade;
import com.ramonkaizer.skinstore.domain.enums.SortBy;
import com.ramonkaizer.skinstore.domain.enums.SortDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkinConsultaRequest {
    private String nome;
    private Double precoMin;
    private Double precoMax;
    private Raridade raridade;
    private String floatMin;
    private String floatMax;
    private Categoria categoria;
    private SortBy sortBy;
    private SortDirection sortDirection;
}