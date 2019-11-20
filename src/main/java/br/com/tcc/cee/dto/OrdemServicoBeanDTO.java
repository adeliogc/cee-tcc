package br.com.tcc.cee.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoBeanDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroOrdemServico;
	private LocalDate dataAbertura;
	private String observacao;
	private List<OrdemServicoItemBean> items = new ArrayList<>();

	public String getNumeroOrdemServico() {
		return numeroOrdemServico;
	}

	public void setNumeroOrdemServico(String numeroOrdemServico) {
		this.numeroOrdemServico = numeroOrdemServico;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<OrdemServicoItemBean> getItems() {
		return items;
	}

	public void setItems(List<OrdemServicoItemBean> items) {
		this.items = items;
	}

}