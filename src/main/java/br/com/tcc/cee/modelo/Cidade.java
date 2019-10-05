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
@Table(name="CIDADES")
public class Cidade implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "O nome da cidade deve ser informada")
	@Size(min=3, max=80, message = "O nome da cidade deverá conter {min} até {max} caracteres")
	@Column(length = 80, nullable = false)	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 2, nullable = false)
	private Estado estado;
	
	public Cidade() {

	}

	public Cidade(String nome, Estado estado) {
		this.nome = nome.toUpperCase();
		this.estado = estado;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getIdFormatado() {
		return new DecimalFormat("000000").format(id);
	}
	
}
