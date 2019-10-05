package br.com.tcc.cee.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tcc.cee.modelo.Cargo;
import br.com.tcc.cee.modelo.Funcionario;
import br.com.tcc.cee.repository.CargoRepository;
import br.com.tcc.cee.util.Constantes;

@Controller
@RequestMapping("cargos")
public class CargoController implements IController<Cargo>{
	
	@Autowired
	private CargoRepository cargoRepository;
	private String descricao = "";
	
	@GetMapping
	@Override
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cargos/list");
		if (descricao.isEmpty()) {
			modelAndView.addObject("cargos", cargoRepository.findAll());
		} else {
			modelAndView.addObject("cargos", cargoRepository.findByNomeContaining(descricao.toUpperCase()));
		}
		return modelAndView;
	}
	
	@GetMapping("cadastros")
	@Override
	public ModelAndView saveAll() {
		ModelAndView modelAndView = new ModelAndView("redirect:/cargos");
		List<Cargo> cargos = Arrays.asList(new Cargo("ANALISta de suporte"), new Cargo("ANALISta de sistemas"), 
				new Cargo("ANALISta de mecanica"), new Cargo("servente de pedreiro"), new Cargo("pedreiro"), 
				new Cargo("auxiliar de servicos gerais"), new Cargo("mecanico industrial"), new Cargo("gerente comercial"));
		cargoRepository.saveAll(cargos);
		return modelAndView;				
	}
	
	@GetMapping("novo")
	@Override
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("cargos/form");
		modelAndView.addObject("cargo", new Cargo());
		modelAndView.addObject("tituloForm", "Cadastro de Cargos");
		return modelAndView;		
	}
	
	@GetMapping("edit/{id}")
	@Override
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("cargos/form");
		modelAndView.addObject("cargo", cargoRepository.findById(id).get());
		modelAndView.addObject("tituloForm", "Editando Cargos");
		return modelAndView;		
	}

	
	@GetMapping("excluir/{id}")
	@Override
	public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes atts) {
		ModelAndView modelAndView = new ModelAndView("redirect:/cargos");
		List<Funcionario> funcionarios = cargoRepository.findById(id).get().getFuncionarios();
		if (funcionarios.isEmpty()) {
			cargoRepository.deleteById(id);
			atts.addFlashAttribute("erro", false);
			atts.addFlashAttribute("mensagem", Constantes.MENSAGEM_EXCLUSAO);
		} else {
			modelAndView.addObject("cargos", cargoRepository.findAll());
			atts.addFlashAttribute("erro", true);
			atts.addFlashAttribute("mensagem", Constantes.MENSAGEM_EXCLUSAO_CARGO_ERRO);			
		}
		return modelAndView;				
	}
	
	@PostMapping
	@Override
	public ModelAndView salvar(@Valid @ModelAttribute("cargo") Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("/cargos/form");
			mv.addObject("tituloForm", "Cadastro de Cargos");
			return mv;
		}
		ModelAndView mv = new ModelAndView("redirect:/cargos");
		cargoRepository.save(cargo);
		attr.addFlashAttribute("erro", false);
		attr.addFlashAttribute("mensagem", Constantes.MENSAGEM_SALVO);
		return mv;
	}

}
