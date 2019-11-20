package br.com.tcc.cee.controller.rest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.cee.dto.OrdemServicoBeanDTO;

@RestController
@RequestMapping("api/emprestimo")
public class EmprestimoResource {
	
	@GetMapping("porItem/{id}")
	public List<OrdemServicoBeanDTO> listar(@PathVariable("id") String id){
		OrdemServicoBeanDTO beanDTO = new OrdemServicoBeanDTO();
		beanDTO.setDataAbertura(LocalDate.now());
		beanDTO.setNumeroOrdemServico("2019091111");
		beanDTO.setObservacao("Sem observações");
		return Arrays.asList(beanDTO);
	}
	
	@GetMapping("porId/{id}")
	public List<OrdemServicoBeanDTO> listarPorId(@PathVariable("id") String id){
		OrdemServicoBeanDTO beanDTO = new OrdemServicoBeanDTO();
		beanDTO.setDataAbertura(LocalDate.now());
		beanDTO.setNumeroOrdemServico("2019091111");
		beanDTO.setObservacao("Sem observações");
		return Arrays.asList(beanDTO);
	}

}
