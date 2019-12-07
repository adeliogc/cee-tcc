package br.com.tcc.cee.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="FUNCIONARIOS")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 80, nullable = false)
	@NotBlank(message = "O nome do funcionário deve ser informada")
	@Size(min = 4, max = 80, message = "O nome do funcionário deverá possuir {min} e {max} caracteres")
	private String nome;
	
	@Column(length = 20, nullable = false)
	@NotBlank(message = "A matrícula do funcionário deve ser informada")
	@Size(min = 1, max = 20, message = "A matrícula do funcionário deverá possuir {min} e {max} caracteres")
	private String matricula;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private Sexo sexo;
	
	@CPF(message = "CPF inválido")
	@Column(length = 18, nullable = false)
	@NotBlank(message = "O CPF do funcionário deve ser informado")
	private String cpf;
	
	@Column(length = 30, nullable = false)
	@NotBlank(message = "O R.G. do funcionário deve ser informado")
	@Size(min = 4, max = 30, message = "O R.G. do funcionário deverá possuir {min} e {max} caracteres")
	private String identidade;
	
	@Column(length = 20)
	private String telefone;
	@Column(length = 20)
	private String celular;
	@Column(length = 150)
	@Email(message = "E-mail informado está incorreto")
	private String email;
	
	@Column(length = 400)
	private String observacao;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "setor_id")
	private Setor setor;

	@Embedded
	@Valid
	private Endereco endereco = new Endereco();

	public Funcionario(String nome, String matricula, Sexo sexo, String cpf, String identidade, Endereco endereco) {
		this.nome = nome.toUpperCase();
		this.matricula = matricula.toUpperCase();
		this.sexo = sexo;
		this.cpf = cpf;
		this.identidade = identidade.toUpperCase();
		this.endereco = endereco;
	}
	
	public Funcionario() {
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula.toUpperCase();
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade.toUpperCase();
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao.toUpperCase();
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}
	
}
