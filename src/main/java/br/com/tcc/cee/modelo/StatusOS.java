package br.com.tcc.cee.modelo;

public enum StatusOS {

	ABERTA("Aberta"), ENCERRADA("Encerrada"), TODAS("Todas");

	private String descricao;

	private StatusOS(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
