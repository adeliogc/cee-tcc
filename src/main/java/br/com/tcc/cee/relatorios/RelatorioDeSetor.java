package br.com.tcc.cee.relatorios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.tcc.cee.modelo.Setor;

public class RelatorioDeSetor {
	
	
	public static List<Setor> imprmirSetores(){
		List<Setor> setores = new ArrayList<>();
		setores = Arrays.asList(new Setor("almoxarifado agricola"), new Setor("almoxarifado industrial"),
				new Setor("administracao"), new Setor("industria"), new Setor("buriti i"), 
				new Setor("oficina mecanica"), new Setor("posto"), new Setor("laboratorio"));
		return setores;
	}
	

}
