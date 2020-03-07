package br.com.tcc.cee.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tcc.cee.modelo.Usuario;
import br.com.tcc.cee.repository.UsuarioRepository;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UsuarioRepository usuarioDao;
	
	@GetMapping
	public String login() {
		return "login";
	}
	
	@PostMapping
	public String efetuarLogin(Usuario usuario, HttpSession session) {
		if (existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "home";
		}
		return "redirect:/login";
	}

	private boolean existeUsuario(Usuario usuario) {
		Usuario usuarioLogado = usuarioDao.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
		return usuarioLogado == null ? false : true;
	}
	
	@RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
