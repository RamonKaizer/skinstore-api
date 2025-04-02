package com.ramonkaizer.skinstore.domain.entity;

import com.ramonkaizer.skinstore.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoSkin> pedidoSkins;

    private Double valorTotal;

    @PrePersist
    void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusPedido.PENDENTE;
    }

}
