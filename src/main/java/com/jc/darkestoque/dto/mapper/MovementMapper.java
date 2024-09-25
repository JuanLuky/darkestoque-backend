package com.jc.darkestoque.dto.mapper;

import com.jc.darkestoque.dto.MovimentacaoDTO;
import com.jc.darkestoque.entity.Movimentacao;
import com.jc.darkestoque.entity.Produto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {
    public MovimentacaoDTO toDTO (@NotNull Movimentacao movimentacao) {
        return  new MovimentacaoDTO(
                movimentacao.getId(),
                movimentacao.getProduto().getId(),
                movimentacao.getProduto().getNome(),
                movimentacao.getQuantidade(),
                movimentacao.getTipo(),
                movimentacao.getDataHora()
        );
    }
}
