package com.ramonkaizer.skinstore.service;

import com.ramonkaizer.skinstore.domain.dto.response.PagamentoResponse;
import com.ramonkaizer.skinstore.domain.dto.response.PedidoResponse;
import com.ramonkaizer.skinstore.domain.dto.response.SkinResponse;
import com.ramonkaizer.skinstore.domain.entity.*;
import com.ramonkaizer.skinstore.domain.enums.StatusPagamento;
import com.ramonkaizer.skinstore.domain.enums.StatusPedido;
import com.ramonkaizer.skinstore.exception.BusinessException;
import com.ramonkaizer.skinstore.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final UsuarioService usuarioService;
    private final CarrinhoService carrinhoService;
    private final ModelMapper modelMapper;

    @Transactional
    public PedidoResponse criaPedido() {
        Usuario usuario = usuarioService.getUser();
        Carrinho carrinho = usuario.getCarrinho();

        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .build();

        List<PedidoSkin> pedidoSkins = carrinho.getCarrinhoSkins().stream()
                .map(carrinhoSkin -> {
                    Skin skin = carrinhoSkin.getSkin();
                    return PedidoSkin.builder()
                            .skin(skin)
                            .pedido(pedido)
                            .build();
                })
                .collect(Collectors.toList());


        Double valorTotal = pedidoSkins.stream()
                .mapToDouble(pedidoSkin -> pedidoSkin.getSkin().getPreco())
                .sum();

        pedido.setPedidoSkins(pedidoSkins);
        pedido.setValorTotal(valorTotal);

        repository.save(pedido);

        carrinho.getCarrinhoSkins().clear();
        carrinhoService.save(carrinho);

        return buildPedidoResponse(pedido);
    }

    private PedidoResponse buildPedidoResponse(Pedido pedido) {
        return PedidoResponse.builder()
                .idPedido(pedido.getId())
                .status(pedido.getStatus().toString())
                .valorTotal(pedido.getValorTotal())
                .skins(pedido.getPedidoSkins().stream().map(pedidoSkin -> modelMapper.map(pedidoSkin.getSkin(), SkinResponse.class)).collect(Collectors.toList()))
                .build();
    }

    public Pedido findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException("Pedido não cadastrado"));
    }

    @Transactional
    public PagamentoResponse pagarPedido(Long pedidoId) {
        Usuario usuario = usuarioService.getUser();

        Pedido pedido = findById(pedidoId);

        if (!pedido.getUsuario().getId().equals(usuario.getId())) {
            throw new BusinessException("Você não tem permissão para pagar este pedido.");
        }

        if (!pedido.getStatus().equals(StatusPedido.PENDENTE)) {
             throw new BusinessException("O pedido não está aguardando pagamento.");
        }

        pedido.setStatus(StatusPedido.FINALIZADO);
        repository.save(pedido);

        return PagamentoResponse.builder()
                .statusPagamento(StatusPagamento.APROVADO)
                .build();
    }
}
