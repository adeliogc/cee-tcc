package br.com.tcc.cee.modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.tcc.cee.repository.OrdemServicoRepository;

@Entity
@Table(name="ORDEM_SERVICOS")
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20, nullable = false)
	private String numeroOrdemServico;
	
	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataAbertura;
	
	@Column(length = 400)
	private String observacao;
	
//	@Valid
//	@Size(min = 1, message = "Deve possuir pelo menos um item")
//	@NotNull(message = "Não há itens incluídos na ordem de serviço")
	@OneToMany(
		        mappedBy = "ordemServico",
		        cascade = CascadeType.ALL,
		        orphanRemoval = true
		    )
	private List<OrdemServicoItem> itens = new ArrayList<OrdemServicoItem>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroOrdemServico() {
		return numeroOrdemServico;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<OrdemServicoItem> getItens() {
		return itens;
	}

	public void setItens(List<OrdemServicoItem> itens) {
		this.itens = itens;
	}
	
	public void geraNumeroOrdemServico(OrdemServicoRepository osRepository) {
		int anoDaOS = LocalDate.now().getYear();
		int totalOsNoAno = osRepository.totalOsNoAno(anoDaOS);
		this.numeroOrdemServico = anoDaOS+new DecimalFormat("000000").format(totalOsNoAno);
	}
	

	public OrdemServico() {
		this.dataAbertura = LocalDateTime.now();
	}
	
	
	
}
