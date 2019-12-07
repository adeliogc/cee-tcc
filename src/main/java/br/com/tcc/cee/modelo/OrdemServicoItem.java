package br.com.tcc.cee.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDEM_SERVICO_ITEM")
public class OrdemServicoItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="equipamento_id")
	private Equipamento equipamento;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private StatusOS status;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ordemServico_id")
	private OrdemServico ordemServico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public StatusOS getStatus() {
		return status;
	}

	public void setStatus(StatusOS status) {
		this.status = status;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	public OrdemServicoItem() {
	}

	public OrdemServicoItem(OrdemServico ordemServico) {
		this.status = StatusOS.ABERTA;
		this.ordemServico = ordemServico;
	}
	
	
}
