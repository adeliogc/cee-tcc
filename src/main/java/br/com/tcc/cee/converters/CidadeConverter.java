package br.com.tcc.cee.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.tcc.cee.modelo.Cidade;
import br.com.tcc.cee.repository.CidadeRepository;

@Component
public class CidadeConverter implements Converter<String, Cidade>{

	@Autowired
	private CidadeRepository dao;
	
	@Override
	public Cidade convert(String valor) {
		if (StringUtils.isEmpty(valor)) {
			return null;			
		} else {
			Cidade cidade = dao.findById(Long.parseLong(valor)).get();
			return cidade;
		}
	}

}
