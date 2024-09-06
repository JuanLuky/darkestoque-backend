package com.jc.darkestoque.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class MovimentacaoDTO {
    private Long id;
    private int quantidade;
    private String tipo;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataHora;
    private Long produtoId;


    public MovimentacaoDTO(Long id, Long produtoId, int quantidade, String tipo, LocalDate dataHora) {
        this.id = id;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.dataHora = dataHora;
        this.produtoId = produtoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}
