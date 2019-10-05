package br.com.tcc.cee.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.tcc.cee.modelo.Cargo;
import br.com.tcc.cee.repository.CargoRepository;

@Component
public class CargoConverter implements Converter<String, Cargo>{

	@Autowired
	private CargoRepository dao;
	
	@Override
	public Cargo convert(String valor) {
		if (StringUtils.isEmpty(valor)) {
			return null;			
		} else {
			Cargo cargo = dao.findById(Long.parseLong(valor)).get();
			return cargo;
		}
	}

}
