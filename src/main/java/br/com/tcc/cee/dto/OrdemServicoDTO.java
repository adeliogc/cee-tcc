package br.com.tcc.cee.dto;

import br.com.tcc.cee.modelo.Categoria;

public class OrdemServicoDTO {
	
	private Categoria categoria;
	private Long quantidade;
	
	public OrdemServicoDTO(Categoria categoria, Long quantidade) {
		this.categoria = categoria;
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Long getQuantidade() {
		return quantidade;
	}

}
