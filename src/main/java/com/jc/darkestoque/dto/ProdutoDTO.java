package com.jc.darkestoque.dto;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
