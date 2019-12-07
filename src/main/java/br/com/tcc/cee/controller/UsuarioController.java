package br.com.tcc.cee.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tcc.cee.modelo.Perfil;
import br.com.tcc.cee.modelo.Usuario;
import br.com.tcc.cee.repository.UsuarioRepository;
import br.com.tcc.cee.util.Constantes;

@RequestMapping("usuarios")
@Controller
public class UsuarioController implements IController<Usuario>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("usuarios/list");
		mv.addObject("usuarios", usuarioRepository.findAll());
		return mv;
	}

	@Override
	@GetMapping("cadastros")
	public ModelAndView saveAll() {
		ModelAndView mv = new ModelAndView("redirect:/usuarios");
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = Arrays.asList(new Usuario("batman", "batman", "1010", Perfil.ADMINISTRADOR), 
				new Usuario("superman", "superman", "1234", Perfil.USUARIO), 
				new Usuario("robin", "robin", "1010", Perfil.USUARIO), 
				new Usuario("flash", "flash", "12345", Perfil.USUARIO), 
				new Usuario("aquaman", "aquaman", "1010", Perfil.ADMINISTRADOR));
		usuarioRepository.saveAll(usuarios);
		return mv;
	}

	@Override
	public ModelAndView form() {
		return null;
	}

	@Override
	public ModelAndView edit(Long id) {
		return null;
	}

	@Override
	@GetMapping("excluir/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes atts) {
		ModelAndView mv = new ModelAndView("redirect:/usuarios");
		usuarioRepository.deleteById(id);
		atts.addFlashAttribute("erro", false);			
		atts.addFlashAttribute("mensagem", Constantes.MENSAGEM_EXCLUSAO);
		return mv;
	}

	@Override
	public ModelAndView salvar(@Valid Usuario entity, BindingResult result, RedirectAttributes attr) {
		return null;
	}

	@Override
	@PostMapping("filtro")
	public ModelAndView listarPorDescricao(@Nullable @RequestParam("descricao") String descricao) {
		ModelAndView mv = new ModelAndView("usuarios/list");
		mv.addObject("usuarios", usuarioRepository.findByNomeContaining(descricao.toUpperCase()));
		return mv;
	}
	
	@PostMapping("salvarUsuario")
	public ModelAndView cadastroUsuario(@Valid @ModelAttribute("usuario") Usuario entity, BindingResult result, RedirectAttributes attr) {
		entity.setPerfil(Perfil.USUARIO);
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("cria-usuario");
			return mv;
		}
		usuarioRepository.save(entity);
		ModelAndView mv = new ModelAndView("redirect:/cria-usuario");
		attr.addFlashAttribute("mensagem", Constantes.MENSAGEM_SALVO);
		
		return mv;
	}


}
