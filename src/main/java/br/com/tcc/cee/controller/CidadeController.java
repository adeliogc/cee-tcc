package br.com.tcc.cee.controller;

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

import br.com.tcc.cee.modelo.Cidade;
import br.com.tcc.cee.modelo.Estado;
import br.com.tcc.cee.repository.CidadeRepository;
import br.com.tcc.cee.util.Constantes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@RequestMapping("cidades")
@Controller
public class CidadeController implements IController<Cidade>{

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cidades/list");
		modelAndView.addObject("cidades", cidadeRepository.findAll());
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

	@Override
	@PostMapping("filtro")
	public ModelAndView listarPorDescricao(String descricao) {
		ModelAndView modelAndView = new ModelAndView("cidades/list");
		modelAndView.addObject("cidades", cidadeRepository.findByNomeContaining(descricao.toUpperCase()));
		return modelAndView;
	}
	
	@PostMapping("imprimir")
	public void imprimir(@RequestParam Map<String, Object> parametros, HttpServletResponse response) throws JRException, SQLException, IOException {	
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;		
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_cidades.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cidadeRepository.findAll());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=lista.pdf");
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}

}
