package br.com.tcc.cee.modelo;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="EQUIPAMENTOS")
public class Equipamento implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 80, nullable = false)
	@NotBlank(message = "A descrição do equipamento deve ser informada")
	@Size(min=3, max=80, message = "A descrição do equipamento deverá conter {min} até {max} caracteres")
	private String nome;
	
	@Column(length = 20, nullable = false)
	@NotBlank(message = "O número de série do equipamento deve ser informada")
	@Size(min=3, max=20, message = "O número de série do equipamento deverá conter {min} até {max} caracteres")
	private String numeroSerie;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Categoria categoria = Categoria.COMPUTADORES;
	
	@Column(length = 400)	
	private String observacao;
	
	public Equipamento() {
	}

	public Equipamento(String nome,String numeroSerie, Categoria categoria, String observacao) {
		this.nome = nome.toUpperCase();
		this.numeroSerie = numeroSerie.toUpperCase();
		this.categoria = categoria;
		this.observacao = observacao.toUpperCase();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie.toUpperCase();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao.toUpperCase();
	}
	
	public String getIdFormatado() {
		return new DecimalFormat("000000").format(id);
	}
	
	


}
