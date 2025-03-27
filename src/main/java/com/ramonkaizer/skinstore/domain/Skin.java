package com.ramonkaizer.skinstore.domain;

import com.ramonkaizer.skinstore.enums.Categoria;
import com.ramonkaizer.skinstore.enums.Raridade;
import com.ramonkaizer.skinstore.enums.StatusSkin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250)
    private String nome;

    private Double preco;

    @Column(length = 1000)
    private String imagem;

    @Enumerated(EnumType.STRING)
    private Raridade raridade;

    @Column(length = 15)
    private String floatValue;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private StatusSkin status;

    private boolean ofertaSemana;

    @PrePersist()
    void prePersist() {
        this.status = StatusSkin.DISPONIVEL;
    }
}
