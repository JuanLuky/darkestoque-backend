package com.jc.darkestoque.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "tb_movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    private String tipo; // Entrada ou Saída

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataHora;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}