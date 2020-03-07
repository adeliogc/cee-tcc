package br.com.tcc.cee.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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


import br.com.tcc.cee.modelo.Cargo;
import br.com.tcc.cee.modelo.Funcionario;
import br.com.tcc.cee.repository.CargoRepository;
import br.com.tcc.cee.util.Constantes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("cargos")
public class CargoController implements IController<Cargo>{
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@GetMapping
	@Override
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cargos/list");
		modelAndView.addObject("cargos", cargoRepository.findAll());
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

	
	@PostMapping("filtro")
	@Override
	public ModelAndView listarPorDescricao(@Nullable @RequestParam("descricao") String descricao) {
		ModelAndView modelAndView = new ModelAndView("cargos/list");
		modelAndView.addObject("cargos", cargoRepository.findByNomeContaining(descricao.toUpperCase()));
		return modelAndView;
	}

	@PostMapping("imprimir")
	public void imprimir(@RequestParam Map<String, Object> parametros, HttpServletResponse response) throws JRException, SQLException, IOException {	
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;		
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_cargos.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cargoRepository.findAll());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=lista.pdf");
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}

	
}
