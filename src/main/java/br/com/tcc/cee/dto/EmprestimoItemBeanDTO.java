package br.com.tcc.cee.dto;

import java.time.LocalDate;

public class EmprestimoItemBeanDTO {

	private String idEquipamento;
	private String equipamento;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private String status;

	public String getIdEquipamento() {
		return idEquipamento;
	}

	public void setIdEquipamento(String idEquipamento) {
		this.idEquipamento = idEquipamento;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
