package br.com.tcc.cee.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.tcc.cee.modelo.Equipamento;
import br.com.tcc.cee.repository.EquipamentoRepository;

@Component
public class EquipamentoConverter implements Converter<String, Equipamento>{

	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Override
	public Equipamento convert(String valor) {
		if (StringUtils.isEmpty(valor)) {
			return null;
		} else {
			Equipamento equipamento = equipamentoRepository.findById(Long.parseLong(valor)).get();
			return equipamento;
		}
	}

}
