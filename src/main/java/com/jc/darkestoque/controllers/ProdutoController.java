package com.jc.darkestoque.controllers;

import com.jc.darkestoque.dto.ProdutoDTO;
import com.jc.darkestoque.dto.ProdutoPageDTO;
import com.jc.darkestoque.entity.Produto;
import com.jc.darkestoque.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(description = "Retorna todos os produtos cadastrado no sistema")
    @GetMapping
    public ProdutoPageDTO list(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero  int page , @RequestParam @Valid int size){
        return produtoService.list(page, size);
    }

    @Operation(description = "Retorna o produto pelo ID")
    @GetMapping("/{id}")
    public ProdutoDTO buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @Operation(description = "Cria um novo produto no sistema")
    @PostMapping
    public ProdutoDTO salvarProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        return produtoService.salvarProduto(produtoDTO);
    }

    @Operation(description = "Atualizar um produto já cadastrado, buscar pelo ID")
    @PutMapping("/{id}")
    public ProdutoDTO atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.atualizarProduto(id, produtoDTO);
    }

    @Operation(description = "Deletar um produto cadastrado")
    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}



