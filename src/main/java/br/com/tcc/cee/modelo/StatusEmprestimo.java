package br.com.tcc.cee.modelo;

public enum StatusEmprestimo {
	
	EMPRESTADO("Emprestado"), DEVOLVIDO("Devolvido"), TODAS("Todas");
	
	private String nome;

	private StatusEmprestimo(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
