package com.jc.darkestoque.controllers;


import com.jc.darkestoque.dto.MovimentacaoDTO;
import com.jc.darkestoque.dto.MovimentacaoPageDTO;
import com.jc.darkestoque.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Operation(description = "Buscar todas movimentações no estoque com paginação")
    @GetMapping
    public MovimentacaoPageDTO listarMovimentacoes(
            @RequestParam(defaultValue = "0") @Valid @PositiveOrZero int page,
            @RequestParam @Valid @Positive int size) {

        return movimentacaoService.listarMovimentacoes(page, size);
    }

    @Operation(description = "Cadastrar uma nova movimentação no estoque (Não precisa informar a data)")
    @PostMapping
    public MovimentacaoDTO salvarMovimentacao(@RequestBody MovimentacaoDTO movimentacaoDTO) {
        return movimentacaoService.salvarMovimentacao(movimentacaoDTO);
    }

    @Operation(description = "Excluir uma movimentação no estoque")
    @DeleteMapping("/{id}")
    public void deletarMovimentacao(@PathVariable Long id) {
        movimentacaoService.deletarMovimentacao(id);
    }
}
