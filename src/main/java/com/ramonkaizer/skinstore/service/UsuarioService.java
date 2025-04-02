package com.ramonkaizer.skinstore.service;

import com.ramonkaizer.skinstore.domain.entity.Carrinho;
import com.ramonkaizer.skinstore.domain.entity.Usuario;
import com.ramonkaizer.skinstore.domain.dto.request.UserCreateRequest;
import com.ramonkaizer.skinstore.domain.dto.request.UserLoginRequest;
import com.ramonkaizer.skinstore.repository.UsuarioRepository;
import com.ramonkaizer.skinstore.security.TokenService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final ModelMapper modelMapper;

    @Transactional
    public void saveUser(UserCreateRequest request) {
        Usuario user = modelMapper.map(request, Usuario.class);

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        Carrinho carrinho = Carrinho.builder()
                .usuario(user)
                .build();
        user.setCarrinho(carrinho);

        repository.save(user);
    }

    public String login(UserLoginRequest request) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken((request.getEmail()), request.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((Usuario) auth.getPrincipal());
    }

    public Usuario getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return repository.findByEmail(email);
    }
}
