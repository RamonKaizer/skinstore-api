package com.ramonkaizer.skinstore.repository;

import com.ramonkaizer.skinstore.domain.entity.Pedido;
import com.ramonkaizer.skinstore.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuario(Usuario usuario);
}
