package br.com.tcc.cee.modelo;

public enum Perfil {
	
	ADMINISTRADOR("Administrador"), USUARIO("Usuário");
	
	private String nome;
	
	private Perfil(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
