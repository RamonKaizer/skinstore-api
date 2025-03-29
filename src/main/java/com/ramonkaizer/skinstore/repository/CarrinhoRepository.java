package com.ramonkaizer.skinstore.repository;

import com.ramonkaizer.skinstore.domain.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

}