package com.jc.darkestoque.dto.mapper;

import com.jc.darkestoque.dto.ProdutoDTO;
import com.jc.darkestoque.entity.Produto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProdutoDTO toDTO (@NotNull Produto product) {
        return  new ProdutoDTO(product.getId(), product.getCodigo(),product.getNome(), product.getQuantidade());
    }
}
