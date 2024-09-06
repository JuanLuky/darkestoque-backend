package com.jc.darkestoque.service;

import com.jc.darkestoque.dto.MovimentacaoDTO;
import com.jc.darkestoque.entity.Movimentacao;
import com.jc.darkestoque.entity.Produto;
import com.jc.darkestoque.repository.MovimentacaoRepository;
import com.jc.darkestoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<MovimentacaoDTO> listarMovimentacoes() {
        return movimentacaoRepository.findAll().stream()
                .map(mov -> new MovimentacaoDTO(mov.getId(), mov.getProduto().getId(), mov.getQuantidade(), mov.getTipo(), mov.getDataHora()))
                .collect(Collectors.toList());
    }

    public MovimentacaoDTO salvarMovimentacao(MovimentacaoDTO movimentacaoDTO) {
        Produto produto = produtoRepository.findById(movimentacaoDTO.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setProduto(produto);
        movimentacao.setQuantidade(movimentacaoDTO.getQuantidade());
        movimentacao.setTipo(movimentacaoDTO.getTipo());
        movimentacao.setDataHora(LocalDate.now());

        if ("Entrada".equalsIgnoreCase(movimentacaoDTO.getTipo())) {
            produto.setQuantidade(produto.getQuantidade() + movimentacaoDTO.getQuantidade());
        } else if ("Saída".equalsIgnoreCase(movimentacaoDTO.getTipo())) {
            produto.setQuantidade(produto.getQuantidade() - movimentacaoDTO.getQuantidade());
        }

        produtoRepository.save(produto);
        Movimentacao salva = movimentacaoRepository.save(movimentacao);

        return new MovimentacaoDTO(salva.getId(), salva.getProduto().getId(), salva.getQuantidade(), salva.getTipo(), salva.getDataHora());
    }

    public void deletarMovimentacao(Long id) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        Produto produto = movimentacao.getProduto();

        // Reverte a movimentação
        if ("Entrada".equalsIgnoreCase(movimentacao.getTipo())) {
            produto.setQuantidade(produto.getQuantidade() - movimentacao.getQuantidade());
        } else if ("Saída".equalsIgnoreCase(movimentacao.getTipo())) {
            produto.setQuantidade(produto.getQuantidade() + movimentacao.getQuantidade());
        }

        produtoRepository.save(produto);
        movimentacaoRepository.deleteById(id);
    }
}
