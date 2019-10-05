package br.com.tcc.cee.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tcc.cee.modelo.Perfil;
import br.com.tcc.cee.modelo.Setor;
import br.com.tcc.cee.modelo.Usuario;
import br.com.tcc.cee.repository.UsuarioRepository;

@RequestMapping("usuarios")
@Controller
public class UsuarioController implements IController<Usuario>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	private String descricao = "";

	@Override
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("usuarios/list");
		if (descricao.isEmpty()) {
			mv.addObject("usuarios", usuarioRepository.findAll());
		} else {
			//mv.addObject("usuarios", usuarioRepository.findByNomeContaining(descricao.toUpperCase()));			
		}
		return mv;
	}

	@Override
	@GetMapping("cadastros")
	public ModelAndView saveAll() {
		ModelAndView mv = new ModelAndView("redirect:/usuarios");
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = Arrays.asList(new Usuario("batman", "batman", "12345", Perfil.ADMINISTRADOR), 
				new Usuario("superman", "superman", "1234", Perfil.USUARIO), 
				new Usuario("robin", "robin", "1010", Perfil.USUARIO), 
				new Usuario("flash", "flash", "12345", Perfil.USUARIO), 
				new Usuario("aquaman", "aquaman", "12345", Perfil.ADMINISTRADOR));
		usuarioRepository.saveAll(usuarios);
		return mv;
	}

	@Override
	public ModelAndView form() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView edit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView delete(Long id, RedirectAttributes atts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView salvar(@Valid Usuario entity, BindingResult result, RedirectAttributes attr) {
		// TODO Auto-generated method stub
		return null;
	}

}
