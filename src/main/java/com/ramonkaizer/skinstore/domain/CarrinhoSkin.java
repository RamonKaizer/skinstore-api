package com.ramonkaizer.skinstore.domain;

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
public class CarrinhoSkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "skin_id")
    private Skin skin;
}
