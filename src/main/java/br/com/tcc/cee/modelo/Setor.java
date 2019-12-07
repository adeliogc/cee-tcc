package br.com.tcc.cee.modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "SETORES")
public class Setor implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 80, nullable = false)
	@NotBlank(message = "A descrição do setor deve ser informada")
	@Size(min=3, max=80, message = "A descrição do setor deverá conter {min} até {max} caracteres")
	private String nome;
	
	@OneToMany(mappedBy = "setor")
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	public Setor() {
	}

	public Setor(String nome) {
		this.nome = nome.toUpperCase();
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
	
	public String getIdFormatado() {
		return new DecimalFormat("000000").format(id);
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
}
