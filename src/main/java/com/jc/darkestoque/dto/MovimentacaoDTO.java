package com.jc.darkestoque.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class MovimentacaoDTO {
    private Long id;
    private int quantidade;
    private String tipo;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataHora;
    private Long produtoId;
    private String produtoNome;


    public MovimentacaoDTO(Long id, Long produtoId,String produtoNome, int quantidade, String tipo, LocalDate dataHora) {
        this.id = id;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.dataHora = dataHora;
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
    }

}
