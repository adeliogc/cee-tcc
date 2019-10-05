package br.com.tcc.cee.controller;

import java.util.ArrayList;
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

import br.com.tcc.cee.modelo.Categoria;
import br.com.tcc.cee.modelo.Equipamento;
import br.com.tcc.cee.repository.EquipamentoRepository;
import br.com.tcc.cee.util.Constantes;

@RequestMapping("equipamentos")
@Controller
public class EquipamentoController implements IController<Equipamento>{

	@Autowired
	private EquipamentoRepository equipamentoRepository;
	private String descricao = "";
	
	@Override
	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("equipamentos/list");
		if (descricao.isEmpty()) {
			modelAndView.addObject("equipamentos", equipamentoRepository.findAll());
		} else {
			modelAndView.addObject("equipamentos", equipamentoRepository.findByNomeContaining(descricao.toUpperCase()));
		}
		return modelAndView;
	}

	@Override
	@GetMapping("cadastros")
	public ModelAndView saveAll() {
		ModelAndView modelAndView = new ModelAndView("redirect:/equipamentos");
		List<Equipamento> equipamentos = new ArrayList<>();
		equipamentos = Arrays.asList(new Equipamento("CELULAR LG K4", "2345678", Categoria.TELEFONES, ""), 
				new Equipamento("radio movel motorola analogico", "345678900", Categoria.RADIOS, ""), 
				new Equipamento("radio motorola digital", "4567898", Categoria.RADIOS, ""), 
				new Equipamento("conector rj45", "12345", Categoria.OUTROS, ""), 
				new Equipamento("cabo par trancado azul", "13456789", Categoria.HARDWARES, ""), 
				new Equipamento("CELULAR samsung a20", "3493840", Categoria.TELEFONES, ""), 
				new Equipamento("SERVIDOR hp 3600", "2345678", Categoria.COMPUTADORES, ""), 
				new Equipamento("CELULAR LG K10 black", "43676283", Categoria.TELEFONES, ""), 
				new Equipamento("notebook asus i5", "2345678", Categoria.COMPUTADORES, ""), 
				new Equipamento("mouse sem fio microsoft", "45679-9", Categoria.HARDWARES, ""));
		equipamentoRepository.saveAll(equipamentos);
		return modelAndView;	
	}

	@Override
	@GetMapping("novo")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("equipamentos/form");
		modelAndView.addObject("equipamento", new Equipamento());
		modelAndView.addObject("tituloForm", "Cadastro de Equipamentos");
		return modelAndView;	
	}

	@GetMapping("edit/{id}")
	@Override
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("equipamentos/form");
		modelAndView.addObject("equipamento", equipamentoRepository.findById(id).get());
		modelAndView.addObject("tituloForm", "Editando Equipamentos");
		return modelAndView;		
	}

	@GetMapping("excluir/{id}")
	@Override
	public ModelAndView delete(Long id, RedirectAttributes atts) {
		ModelAndView modelAndView = new ModelAndView("redirect:/equipamentos");
		equipamentoRepository.deleteById(id);
		atts.addFlashAttribute("erro", false);
		atts.addFlashAttribute("mensagem", Constantes.MENSAGEM_EXCLUSAO);
		return modelAndView;	
	}

	@Override
	@PostMapping
	public ModelAndView salvar(@Valid @ModelAttribute("equipamento") Equipamento entity, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("/equipamentos/form");
			mv.addObject("tituloForm", "Cadastro de Equipamentos");
			return mv;
		}
		ModelAndView mv = new ModelAndView("redirect:/equipamentos");
		equipamentoRepository.save(entity);
		attr.addFlashAttribute("erro", false);
		attr.addFlashAttribute("mensagem", Constantes.MENSAGEM_SALVO);
		return mv;
	}
	
	@ModelAttribute("categorias")
	public List<Categoria> getCategorias(){
		return Arrays.asList(Categoria.values());
	}

}
