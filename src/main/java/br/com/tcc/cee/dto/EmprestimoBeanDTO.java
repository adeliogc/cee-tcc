package br.com.tcc.cee.dto;

import java.util.List;

public class EmprestimoBeanDTO {

	private String numero;
	private String funcionario;
	private String observacoes;
	private List<EmprestimoItemBeanDTO> items;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<EmprestimoItemBeanDTO> getItems() {
		return items;
	}

	public void setItems(List<EmprestimoItemBeanDTO> items) {
		this.items = items;
	}

}
