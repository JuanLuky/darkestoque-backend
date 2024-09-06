package com.jc.darkestoque.controllers;

import com.jc.darkestoque.dto.ProdutoDTO;
import com.jc.darkestoque.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(description = "Retorna todos os produtos cadastrado no sistema")
    @GetMapping
    public List<ProdutoDTO> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @Operation(description = "Retorna o produto pelo ID")
    @GetMapping("/{id}")
    public ProdutoDTO buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @Operation(description = "Cria um novo produto no sistema")
    @PostMapping
    public ProdutoDTO salvarProduto(@RequestBody ProdutoDTO produtoDTO) {
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



