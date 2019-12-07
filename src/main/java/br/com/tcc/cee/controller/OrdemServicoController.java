package br.com.tcc.cee.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.cee.modelo.Equipamento;
import br.com.tcc.cee.modelo.StatusOS;
import br.com.tcc.cee.repository.EquipamentoRepository;
import br.com.tcc.cee.repository.OrdemServicoRepository;

@RequestMapping("ordemServicos")
@Controller
public class OrdemServicoController {
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	@Autowired
	private OrdemServicoRepository osRepository;
//	private OrdemServico os = new OrdemServico();


	@GetMapping("baixar-os")
	public ModelAndView baixar() {
		ModelAndView mv = new ModelAndView("ordem-servicos/baixar-os");
		mv.addObject("ordemServicos", osRepository.findAll());
		return mv;	
	}
	
//	@GetMapping("abertura-os")
//	public ModelAndView form() {
//		ModelAndView mv = new ModelAndView("ordem-servicos/abertura-os");
//		mv.addObject("os", os);
//		mv.addObject("itemOs", new OrdemServicoItem());
//		return mv;
//	}
	
	@ModelAttribute("equipamentos")
	public List<Equipamento> getEquipamentos(){
		return equipamentoRepository.findAll();
	}
	
	@ModelAttribute("statusOs")
	public List<StatusOS> getStatus(){
		return Arrays.asList(StatusOS.values());
	}
	
//	@PostMapping("abertura-os")
//	public ModelAndView salvarOs(@Valid @ModelAttribute("os") OrdemServico os, BindingResult result, RedirectAttributes attr) {
//		if (result.hasErrors()) {
//			ModelAndView mv = new ModelAndView("ordem-servicos/abertura-os");
//			return mv;
//		} else {
//			ModelAndView mv = new ModelAndView("redirect:/ordemServicos/abertura-os");
//			//os.geraNumeroOrdemServico(osRepository);
//			
//			osRepository.save(os);
//			attr.addFlashAttribute("erro", false);
//			attr.addFlashAttribute("mensagem", "Ordem de Serviço gerada com o número " + os.getNumeroOrdemServico());
//			return mv;
//		}
//	}
	
//	public ModelAndView addItem(OrdemServicoItem item) {
//		ModelAndView mv = new ModelAndView("ordemServicos/abertura-os");
//		//attr.addFlashAttribute("mensagem", "Ordem de Serviço gerada com o número " + os.getNumeroOrdemServico());
//		return mv;
//	}
	
	
}