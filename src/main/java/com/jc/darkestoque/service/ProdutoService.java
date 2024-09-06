package com.jc.darkestoque.service;

import com.jc.darkestoque.dto.ProdutoDTO;
import com.jc.darkestoque.entity.Produto;
import com.jc.darkestoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.findAll().stream()
                .map(produto -> new ProdutoDTO(produto.getId(), produto.getCodigo(), produto.getNome(), produto.getQuantidade()))
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ProdutoDTO(produto.getId(),produto.getCodigo(), produto.getNome(), produto.getQuantidade());
    }
    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setCodigo(produtoDTO.getCodigo());
        produto.setQuantidade(produtoDTO.getQuantidade());
        Produto salvo = produtoRepository.save(produto);
        return new ProdutoDTO(salvo.getId(),salvo.getCodigo(), salvo.getNome(), salvo.getQuantidade());
    }

    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setNome(produtoDTO.getNome());
        produto.setCodigo(produtoDTO.getCodigo());
        produto.setQuantidade(produtoDTO.getQuantidade());
        Produto atualizado = produtoRepository.save(produto);
        return new ProdutoDTO(atualizado.getId(), atualizado.getCodigo(), atualizado.getNome(), atualizado.getQuantidade());
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
