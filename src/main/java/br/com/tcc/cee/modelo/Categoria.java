package br.com.tcc.cee.modelo;

public enum Categoria {
	
	COMPUTADORES("Computadores"), 
	HARDWARES("Hardwares"), 
	RADIOS("Rádios"), 
	TELEFONES("Telefones"),
	OUTROS("Outros");
	
	private String nome;
	
	private Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
