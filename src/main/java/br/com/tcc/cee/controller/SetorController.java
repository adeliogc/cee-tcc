package br.com.tcc.cee.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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

import br.com.tcc.cee.modelo.Funcionario;
import br.com.tcc.cee.modelo.Setor;
import br.com.tcc.cee.repository.SetorRepository;
import br.com.tcc.cee.util.Constantes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@RequestMapping("setores")
@Controller
public class SetorController implements IController<Setor>{

	@Autowired
	private SetorRepository setorRepository;
	private String descricao = "";
	
	@Override
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("setores/list");
		if (descricao.isEmpty()) {
			mv.addObject("setores", setorRepository.findAll());
		} else {
			mv.addObject("setores", setorRepository.findByNomeContaining(descricao.toUpperCase()));			
		}
		return mv;
	}

	@Override
	@GetMapping("cadastros")
	public ModelAndView saveAll() {
		ModelAndView mv = new ModelAndView("redirect:/setores");
		List<Setor> setores = new ArrayList<>();
		setores = Arrays.asList(new Setor("almoxarifado agricola"), new Setor("almoxarifado industrial"),
				new Setor("administracao"), new Setor("industria"), new Setor("buriti i"), 
				new Setor("oficina mecanica"), new Setor("posto"), new Setor("laboratorio"));
		setorRepository.saveAll(setores);
		return mv;
	}

	@Override
	@GetMapping("novo")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("setores/form");
		modelAndView.addObject("setor", new Setor());
		modelAndView.addObject("tituloForm", "Cadastro de Setores");
		return modelAndView;	
	}

	@Override
	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("setores/form");
		modelAndView.addObject("setor", setorRepository.findById(id).get());
		modelAndView.addObject("tituloForm", "Editando Setor");
		return modelAndView;	
	}

	@Override
	@GetMapping("excluir/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes atts) {
		List<Funcionario> funcionarios = setorRepository.findById(id).get().getFuncionarios();
		ModelAndView mv = new ModelAndView("redirect:/setores");
		if (funcionarios.isEmpty()) {
			setorRepository.deleteById(id);
			atts.addFlashAttribute("erro", false);			
			atts.addFlashAttribute("mensagem", Constantes.MENSAGEM_EXCLUSAO);
		} else {
			mv.addObject("setores", setorRepository.findAll());
			atts.addFlashAttribute("erro", true);			
			atts.addFlashAttribute("mensagem", Constantes.MENSAGEM_EXCLUSAO_SETOR_ERRO);
		}
		return mv;
		
	}

	@Override
	@PostMapping
	public ModelAndView salvar(@Valid @ModelAttribute("setor") Setor entity, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("/setores/form");
			mv.addObject("tituloForm", "Cadastro de Setores");
			return mv;
		}
		ModelAndView mv = new ModelAndView("redirect:/setores");
		setorRepository.save(entity);
		attr.addFlashAttribute("erro", false);
		attr.addFlashAttribute("mensagem", Constantes.MENSAGEM_SALVO);
		return mv;
	}

	@Override
	@PostMapping("filtro")
	public ModelAndView listarPorDescricao(@Nullable @RequestParam("descricao") String descricao) {
		ModelAndView mv = new ModelAndView("setores/list");
		mv.addObject("setores", setorRepository.findByNomeContaining(descricao.toUpperCase()));
		return mv;
	}
	
	
	@PostMapping("imprimir")
	public void imprimir(@RequestParam Map<String, Object> parametros, HttpServletResponse response) throws JRException, SQLException, IOException {	
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;		
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_setores.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(setorRepository.findAll());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=lista.pdf");
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	


}
