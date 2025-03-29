package com.ramonkaizer.skinstore.service;

import com.ramonkaizer.skinstore.repository.CarrinhoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository repository;

    public void inserirSkin(Long skinId) {
    }
}
