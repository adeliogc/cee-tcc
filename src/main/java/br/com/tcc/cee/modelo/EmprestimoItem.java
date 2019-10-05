package br.com.tcc.cee.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="EMPRESTIMO_ITEM")
public class EmprestimoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "equipamento_id")
	private Equipamento equipamento;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataEmprestimo;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataDevolucao;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(length = 20)
	private StatusEmprestimo status;
	
	@Column(length = 400)
	private String observacao;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="emprestimo_id", nullable = false)
	private Emprestimo emprestimo;
	
}
