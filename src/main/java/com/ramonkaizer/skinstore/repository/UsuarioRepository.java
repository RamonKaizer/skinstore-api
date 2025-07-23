package com.ramonkaizer.skinstore.repository;

import com.ramonkaizer.skinstore.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    Usuario findByUuid(UUID Uuid);
}
