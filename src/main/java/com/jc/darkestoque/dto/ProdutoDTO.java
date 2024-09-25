package com.jc.darkestoque.dto;

import com.jc.darkestoque.entity.Produto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProdutoDTO {

    private Long id;
    private String nome;
    private int codigo;

    private int quantidade;


    public ProdutoDTO(Long id, int codigo, String nome, int quantidade) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
    }

}
