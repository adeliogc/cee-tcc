package br.com.tcc.cee.modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
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
@Table(name = "CARGOS")
public class Cargo implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 80, nullable = false)
	@NotBlank(message = "A descrição do cargo deve ser informada")
	@Size(min=3, max=80, message = "A descrição do cargo deverá conter {min} até {max} caracteres")
	private String nome;
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;
	
	public Cargo() {
		// TODO Auto-generated constructor stub
	}

	public Cargo(String nome) {
		this.nome = nome.toUpperCase();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getIdFormatado() {
		return new DecimalFormat("000000").format(this.id);
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
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
