package com.ramonkaizer.skinstore.service.usuario;

import com.ramonkaizer.skinstore.domain.Usuario;
import com.ramonkaizer.skinstore.dto.request.UserRequest;
import com.ramonkaizer.skinstore.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private final ModelMapper modelMapper;

    @Transactional
    public void saveUser(UserRequest request) {
        Usuario user = modelMapper.map(request, Usuario.class);

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setSenha(encryptedPassword);

        //TODO ADICIONAR CARRINHO

        repository.save(user);
    }
}
