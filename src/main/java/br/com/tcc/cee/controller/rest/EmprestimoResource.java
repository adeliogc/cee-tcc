package br.com.tcc.cee.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tcc.cee.dto.EmprestimoBeanDTO;
import br.com.tcc.cee.dto.EmprestimoItemBeanDTO;
import br.com.tcc.cee.modelo.Emprestimo;
import br.com.tcc.cee.modelo.EmprestimoItem;
import br.com.tcc.cee.repository.EmprestimoRepository;

@RestController
@RequestMapping("api/emprestimo")
public class EmprestimoResource {
	
	@Autowired
	private EmprestimoRepository repository;
	
	@GetMapping("porItem/{id}")
	public ResponseEntity<List<EmprestimoBeanDTO>> listar(@PathVariable("id") String id){
		List<Emprestimo> emprestimos = repository.findByItensEquipamentoNumeroSerie(id);
		List<EmprestimoBeanDTO> emprestimoBean = new ArrayList<>();
		if(!emprestimos.isEmpty()) {
			for(Emprestimo emprestimo : emprestimos) {
				EmprestimoBeanDTO empDTO = new EmprestimoBeanDTO();
				empDTO.setFuncionario(emprestimo.getFuncionario().getMatricula() + " - " + emprestimo.getFuncionario().getNome());
				empDTO.setNumero(emprestimo.getNumeroEmprestimo());
				empDTO.setObservacoes(emprestimo.getObservacao());
				List<EmprestimoItemBeanDTO> itemsDTO = new ArrayList<>();
				for(EmprestimoItem item : emprestimo.getItens()) {
					EmprestimoItemBeanDTO itemDTO = new EmprestimoItemBeanDTO();
					if (id.equals(item.getEquipamento().getNumeroSerie())) {
						itemDTO.setDataDevolucao(item.getDataDevolucao());
						itemDTO.setDataEmprestimo(item.getDataEmprestimo());
						itemDTO.setIdEquipamento(item.getEquipamento().getNumeroSerie());
						itemDTO.setEquipamento(item.getEquipamento().getNome());
						itemDTO.setStatus(item.getStatus().getNome());
						itemsDTO.add(itemDTO);						
					}
				}
				empDTO.setItems(itemsDTO);
				emprestimoBean.add(empDTO);
			}
		}
		return emprestimos.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(emprestimoBean, HttpStatus.OK);
	}
	
	@GetMapping("porId/{id}")
	public ResponseEntity<EmprestimoBeanDTO> listarPorId(@PathVariable("id") String id){
		Emprestimo emprestimo = repository.findByNumeroEmprestimo(id);
		EmprestimoBeanDTO beanDTO = new EmprestimoBeanDTO();
		if (emprestimo != null) {
			beanDTO.setNumero(emprestimo.getNumeroEmprestimo());
			beanDTO.setFuncionario(emprestimo.getFuncionario().getMatricula() + " - " + emprestimo.getFuncionario().getNome());
			beanDTO.setObservacoes(emprestimo.getObservacao());
			List<EmprestimoItemBeanDTO> lista = new ArrayList<EmprestimoItemBeanDTO>();
			for(EmprestimoItem item : emprestimo.getItens()) {
				EmprestimoItemBeanDTO beanItem = new EmprestimoItemBeanDTO();
				beanItem.setIdEquipamento(item.getEquipamento().getNumeroSerie());
				beanItem.setEquipamento(item.getEquipamento().getNome());
				beanItem.setStatus(item.getStatus().getNome());
				beanItem.setDataDevolucao(item.getDataDevolucao());
				beanItem.setDataEmprestimo(item.getDataEmprestimo());
				lista.add(beanItem);
			}
			beanDTO.setItems(lista);
		}
		return emprestimo == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(beanDTO, HttpStatus.OK);
	}

}
