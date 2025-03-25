package com.ramonkaizer.skinstore.domain;

import com.ramonkaizer.skinstore.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250)
    private String nome;

    @Column(length = 100)
    private String email;

    @Column(length = 250)
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @OneToOne(mappedBy = "usuario")
    private Carrinho carrinho;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.tipo == TipoUsuario.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @PrePersist
    void prePersist() {
        this.setTipo(TipoUsuario.CLIENTE);
    }
}
