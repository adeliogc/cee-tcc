package br.com.tcc.cee.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.tcc.cee.modelo.Equipamento;

@Component
public class EquipamentoConverter implements Converter<String, Equipamento>{

	
	@Override
	public Equipamento convert(String valor) {
		if (StringUtils.isEmpty(valor)) {
			return null;
		} else {
			Equipamento equipamento = new Equipamento();
			equipamento.setId(Long.parseLong(valor));
			return equipamento;
		}
	}

}
