package com.jc.darkestoque.dto;

import java.util.List;

public record MovimentacaoPageDTO(List<MovimentacaoDTO> movement, long totalElements, int totalPages) {
}


