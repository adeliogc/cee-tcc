package br.com.tcc.cee.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tcc.cee.dto.EmprestimoSetorDTO;
import br.com.tcc.cee.dto.OrdemServicoDTO;
import br.com.tcc.cee.modelo.Categoria;
import br.com.tcc.cee.modelo.Setor;
import br.com.tcc.cee.repository.EquipamentoRepository;
import br.com.tcc.cee.repository.FuncionarioRepository;

@Controller
public class HomeController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@ModelAttribute("ordemServicoPorCategoria")
	public List<OrdemServicoDTO> listarOs(){
		List<OrdemServicoDTO> ordemServicos = new ArrayList<>();
		OrdemServicoDTO dto1 = new OrdemServicoDTO(Categoria.COMPUTADORES, 200l);
		OrdemServicoDTO dto2 = new OrdemServicoDTO(Categoria.HARDWARES, 180l);
		OrdemServicoDTO dto3 = new OrdemServicoDTO(Categoria.OUTROS, 111l);
		OrdemServicoDTO dto4 = new OrdemServicoDTO(Categoria.RADIOS, 80l);
		OrdemServicoDTO dto5 = new OrdemServicoDTO(Categoria.TELEFONES, 42l);
		ordemServicos = Arrays.asList(dto1, dto2, dto3, dto4, dto5);
		return ordemServicos;
	}
	
	@ModelAttribute("emprestimosPorSetor")
	public List<EmprestimoSetorDTO> emprestimos(){
		List<EmprestimoSetorDTO> emprestimos = new ArrayList<>();
		EmprestimoSetorDTO dto1 = new EmprestimoSetorDTO(new Setor("ALMOXARIFADO AGRICOLA"), 123l);
		EmprestimoSetorDTO dto2 = new EmprestimoSetorDTO(new Setor("OFICINA AUTOMOTIVA"), 99l);
		EmprestimoSetorDTO dto3 = new EmprestimoSetorDTO(new Setor("ADMINISTRACAO"), 77l);
		EmprestimoSetorDTO dto4 = new EmprestimoSetorDTO(new Setor("OFICINA INDUSTRIAL"), 90l);
		EmprestimoSetorDTO dto5 = new EmprestimoSetorDTO(new Setor("ALMOXARIFADO INDUSTRIAL"), 10l);
		emprestimos = Arrays.asList(dto1, dto2, dto3, dto4, dto5);
		return emprestimos;
	}
	
	@ModelAttribute("totalEquipamentos")
	public Long totalEquipamentos() {
		return equipamentoRepository.count();
	}
	
	@ModelAttribute("totalColaboradores")
	public Long totalColaboradores() {
		return funcionarioRepository.count();
	}
	
	@ModelAttribute("equipamentosEmManutencao")
	@NumberFormat(style = Style.PERCENT)
	public Double equipamentosEmManutencao() {
		return 88.6;
	}
	
	@ModelAttribute("equipamentosEmprestados")
	@NumberFormat(style = Style.PERCENT)
	public Double equipamentosEmprestados() {
		return 59.34;
	}

}
