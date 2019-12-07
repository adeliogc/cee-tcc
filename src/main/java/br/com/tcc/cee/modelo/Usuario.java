package br.com.tcc.cee.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="USUARIOS")
public class Usuario implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 80, nullable = false)
	@NotBlank(message = "O Nome do Usuário deve ser informado")
	@Size(min=3, max=80, message = "O Nome do Usuário deverá conter {min} até {max} caracteres")
	private String nome;
	
	@Column(length = 40, nullable = false, unique = true)
	@NotBlank(message = "O Login do Usuário deve ser informado")
	@Size(min=3, max=40, message = "O Login do Usuário deverá conter {min} até {max} caracteres")
	private String login;
	
	@Column(nullable = false)
	@NotBlank(message = "A Senha do Usuário deve ser informado")
	private String senha;
	
	@Transient
	private String contrasenha;
	
	private boolean ativo;
	
	@Column(length = 80, nullable = false)
	@Enumerated(EnumType.STRING)
	private Perfil perfil;

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
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public Usuario() {
	}

	public Usuario(String nome,String login,String senha, Perfil perfil) {
		this.nome = nome.toUpperCase();
		this.login = login.toUpperCase();
		this.senha = senha;
		this.perfil = perfil;
	}
	
	
}
