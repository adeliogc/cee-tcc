package br.com.tcc.cee.dto;

import java.util.List;

import br.com.tcc.cee.modelo.EmprestimoItem;
import br.com.tcc.cee.modelo.Equipamento;

public class EquipamentoDTO {
	
	private Equipamento equipamento;
	private List<EmprestimoItem> emprestimos;
	
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public Equipamento getEquipamento() {
		return equipamento;
	}
	
	public void setEmprestimos(List<EmprestimoItem> emprestimos) {
		this.emprestimos = emprestimos;
	}
	
	public List<EmprestimoItem> getEmprestimos() {
		return emprestimos;
	}

}
