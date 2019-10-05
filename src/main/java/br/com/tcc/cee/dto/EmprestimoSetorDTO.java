package br.com.tcc.cee.dto;

import br.com.tcc.cee.modelo.Setor;

public class EmprestimoSetorDTO {
	
	private Setor setor;
	private Long quantidade;
	
	public EmprestimoSetorDTO(Setor setor, Long quantidade) {
		this.setor = setor;
		this.quantidade = quantidade;
	}

	public Setor getSetor() {
		return setor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

}
