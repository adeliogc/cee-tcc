package br.com.tcc.cee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("ordemServicos")
@Controller
public class OrdemServicoController {
		
//	@GetMapping("abertura-os")
//	public ModelAndView form(@ModelAttribute("os") OrdemServico os) {
//		ModelAndView mv = new ModelAndView("ordem-servicos/abertura-os");
//		System.out.println("Numero OS: " + os.getNumeroOrdemServico());
//		mv.addObject("os", os);
//		return mv;
//	}
//		
//	@ModelAttribute("statusOs")
//	public List<StatusOS> getStatus(){
//		return Arrays.asList(StatusOS.values());
//	}	
	
}