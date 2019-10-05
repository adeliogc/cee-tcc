package br.com.tcc.cee.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.tcc.cee.modelo.Setor;
import br.com.tcc.cee.repository.SetorRepository;

@Component
public class SetorConverter implements Converter<String, Setor>{

	@Autowired
	private SetorRepository dao;
	
	@Override
	public Setor convert(String valor) {
		if (StringUtils.isEmpty(valor)) {
			return null;
		} else {
			Setor setor = dao.findById(Long.parseLong(valor)).get();
			return setor;
		}
	}

}
