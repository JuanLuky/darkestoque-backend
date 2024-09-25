package com.jc.darkestoque.service;

import com.jc.darkestoque.dto.MovimentacaoDTO;
import com.jc.darkestoque.dto.MovimentacaoPageDTO;
import com.jc.darkestoque.dto.mapper.MovementMapper;
import com.jc.darkestoque.entity.Movimentacao;
import com.jc.darkestoque.entity.Produto;
import com.jc.darkestoque.repository.MovimentacaoRepository;
import com.jc.darkestoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {

    @Autowired
    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;
    private final MovementMapper movementMapper;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ProdutoRepository produtoRepository, MovementMapper movementMapper) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
        this.movementMapper = movementMapper;
    }

    public MovimentacaoPageDTO listarMovimentacoes(int page, int size) {
        // Buscar a página de movimentações do repositório
        Page<Movimentacao> pageMovimentacao = movimentacaoRepository.findAll(PageRequest.of(page, size));
        List<MovimentacaoDTO> movimentacoes = pageMovimentacao.get().map(movementMapper::toDTO).collect(Collectors.toList());
        return new MovimentacaoPageDTO(movimentacoes, pageMovimentacao.getTotalElements(), pageMovimentacao.getTotalPages());
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
        } else if ("Saida".equalsIgnoreCase(movimentacaoDTO.getTipo())) {
            produto.setQuantidade(produto.getQuantidade() - movimentacaoDTO.getQuantidade());
        }

        produtoRepository.save(produto);
        Movimentacao salva = movimentacaoRepository.save(movimentacao);

        return new MovimentacaoDTO(salva.getId(), salva.getProduto().getId(),salva.getProduto().getNome(), salva.getQuantidade(), salva.getTipo(), salva.getDataHora());
    }

    public void deletarMovimentacao(Long id) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        Produto produto = movimentacao.getProduto();

        // Reverte a movimentação
        if ("Entrada".equalsIgnoreCase(movimentacao.getTipo())) {
            produto.setQuantidade(produto.getQuantidade() - movimentacao.getQuantidade());
        } else if ("Saida".equalsIgnoreCase(movimentacao.getTipo())) {
            produto.setQuantidade(produto.getQuantidade() + movimentacao.getQuantidade());
        }

        produtoRepository.save(produto);
        movimentacaoRepository.deleteById(id);
    }
}
