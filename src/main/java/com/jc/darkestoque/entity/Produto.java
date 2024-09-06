package com.jc.darkestoque.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name="tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int codigo;

    private String nome;

    private int quantidade;
}
