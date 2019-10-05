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

import br.com.tcc.cee.modelo.Cidade;
import br.com.tcc.cee.modelo.Estado;
import br.com.tcc.cee.repository.CidadeRepository;
import br.com.tcc.cee.util.Constantes;

@RequestMapping("cidades")
@Controller
public class CidadeController implements IController<Cidade>{

	@Autowired
	private CidadeRepository cidadeRepository;
	private String descricao = "";
	
	
	@Override
	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cidades/list");
		if (descricao.isEmpty()) {
			modelAndView.addObject("cidades", cidadeRepository.findAll());
		} else {
			modelAndView.addObject("cidades", cidadeRepository.findByNomeContaining(descricao.toUpperCase()));
		}
		return modelAndView;
	}

	@Override
	@GetMapping("cadastros")
	public ModelAndView saveAll() {
		ModelAndView modelAndView = new ModelAndView("redirect:/cidades");
		List<Cidade> cidades = Arrays.asList(new Cidade("goiatuba", Estado.GO),new Cidade("itumbiara", Estado.GO),
				new Cidade("goiania", Estado.GO), new Cidade("bom jesus de goias", Estado.GO), 
				new Cidade("uberlandia", Estado.MG), new Cidade("rio de janeiro", Estado.RJ), 
				new Cidade("sao paulo", Estado.SP), new Cidade("bahia", Estado.BA), 
				new Cidade("brasilia", Estado.DF), new Cidade("curitiba", Estado.RS));
		cidadeRepository.saveAll(cidades);
		return modelAndView;		
	}

	@Override
	@GetMapping("novo")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("cidades/form");
		modelAndView.addObject("cidade", new Cidade());
		modelAndView.addObject("tituloForm", "Cadastro de Cidades");
		return modelAndView;		
	}

	@Override
	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("cidades/form");
		modelAndView.addObject("cidade", cidadeRepository.findById(id).get());
		modelAndView.addObject("tituloForm", "Editando Cidades");
		return modelAndView;	
	}

	@Override
	@GetMapping("excluir/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes atts) {
		ModelAndView modelAndView = new ModelAndView("redirect:/cidades");
		cidadeRepository.deleteById(id);
		atts.addFlashAttribute("erro", false);
		atts.addFlashAttribute("mensagem", Constantes.MENSAGEM_EXCLUSAO);
		return modelAndView;			
	}

	@Override
	@PostMapping
	public ModelAndView salvar(@Valid @ModelAttribute("cidade") Cidade entity, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("/cidades/form");
			mv.addObject("tituloForm", "Cadastro de Cidades");
			return mv;
		}
		ModelAndView mv = new ModelAndView("redirect:/cidades");
		cidadeRepository.save(entity);
		attr.addFlashAttribute("erro", false);
		attr.addFlashAttribute("mensagem", Constantes.MENSAGEM_SALVO);
		return mv;
	}
	
	@ModelAttribute("estados")
	public List<Estado> getEstados(){
		return Arrays.asList(Estado.values());
	}

}
