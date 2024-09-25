package com.jc.darkestoque.dto;

import java.util.List;

public record ProdutoPageDTO(List<ProdutoDTO> product, long totalElements, int totalPages) {
}
