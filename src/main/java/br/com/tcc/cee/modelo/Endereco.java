package br.com.tcc.cee.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length = 40, nullable = false)
	@NotBlank(message = "O logradouro deve ser informado")
	@Size(min = 3, max = 40, message = "O logradouro deverá possuir {min} e {max} caracteres")
	private String logradouro;
	
	@Column(length = 6)
	private String numero;
	
	@Column(length = 40)
	private String complemento;
	
	@Column(length = 40, nullable = false)
	@NotBlank(message = "O bairro deve ser informado")
	@Size(min = 3, max = 40, message = "O bairro deverá possuir {min} e {max} caracteres")
	private String bairro;
	
	@Column(length = 10, nullable = false)
	@NotBlank(message = "O CEP deve ser informado")
	private String cep;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	public Endereco() {
	}
	
	public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade) {
		this.logradouro = logradouro.toUpperCase();
		this.numero = numero.toUpperCase();
		this.complemento = complemento.toUpperCase();
		this.bairro = bairro.toUpperCase();
		this.cep = cep;
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro.toUpperCase();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero.toUpperCase();
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento.toUpperCase();
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.toUpperCase();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
}
