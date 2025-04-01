package com.ramonkaizer.skinstore.service;

import com.ramonkaizer.skinstore.domain.dto.response.CarrinhoResponse;
import com.ramonkaizer.skinstore.domain.dto.response.SkinResponse;
import com.ramonkaizer.skinstore.domain.entity.Carrinho;
import com.ramonkaizer.skinstore.domain.entity.CarrinhoSkin;
import com.ramonkaizer.skinstore.domain.entity.Skin;
import com.ramonkaizer.skinstore.domain.entity.Usuario;
import com.ramonkaizer.skinstore.exception.BusinessException;
import com.ramonkaizer.skinstore.repository.CarrinhoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository repository;
    private final UsuarioService usuarioService;
    private final SkinService skinService;
    private final ModelMapper modelMapper;

    @Transactional
    public void inserirSkin(Long skinId) {
        Usuario usuario = usuarioService.getUser();
        Carrinho carrinho = usuario.getCarrinho();
        Skin skin = skinService.findById(skinId);

        boolean jaExiste = carrinho.getCarrinhoSkins().stream()
                .anyMatch(cs -> cs.getSkin().getId().equals(skinId));

        if (jaExiste) {
            throw new BusinessException("Essa skin já está no carrinho.");
        }

        CarrinhoSkin carrinhoSkin = CarrinhoSkin.builder()
                .carrinho(carrinho)
                .skin(skin)
                .build();

        carrinho.getCarrinhoSkins().add(carrinhoSkin);

        repository.save(carrinho);
    }

    @Transactional
    public void removerSkin(Long skinId) {
        Usuario usuario = usuarioService.getUser();
        Carrinho carrinho = usuario.getCarrinho();

        Optional<CarrinhoSkin> carrinhoSkinOptional = carrinho.getCarrinhoSkins().stream()
                .filter(cs -> cs.getSkin().getId().equals(skinId))
                .findFirst();

        if (carrinhoSkinOptional.isEmpty()) {
            throw new BusinessException("Essa skin não está no carrinho.");
        }

        CarrinhoSkin carrinhoSkin = carrinhoSkinOptional.get();
        carrinho.getCarrinhoSkins().remove(carrinhoSkin);
        repository.save(carrinho);
    }

    public CarrinhoResponse getCarrinho() {
        Usuario usuario = usuarioService.getUser();
        Carrinho carrinho = usuario.getCarrinho();
        List<CarrinhoSkin> carrinhoSkins = carrinho.getCarrinhoSkins();

        List<SkinResponse> skins =  carrinhoSkins.stream()
                .map(CarrinhoSkin::getSkin)
                .map(skin -> modelMapper.map(skin, SkinResponse.class))
                .toList();

        Double valorTotal = carrinhoSkins.stream()
                .mapToDouble(cs -> cs.getSkin().getPreco())
                .sum();

        return CarrinhoResponse.builder()
                .skins(skins)
                .valorTotal(valorTotal)
                .build();
    }
}
