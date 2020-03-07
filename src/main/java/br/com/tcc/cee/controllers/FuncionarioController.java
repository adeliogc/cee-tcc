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
import br.com.tcc.cee.modelo.Cidade;
import br.com.tcc.cee.modelo.Endereco;
import br.com.tcc.cee.modelo.Funcionario;
import br.com.tcc.cee.modelo.Setor;
import br.com.tcc.cee.modelo.Sexo;
import br.com.tcc.cee.repository.CargoRepository;
import br.com.tcc.cee.repository.CidadeRepository;
import br.com.tcc.cee.repository.FuncionarioRepository;
import br.com.tcc.cee.repository.SetorRepository;
import br.com.tcc.cee.util.Constantes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


@RequestMapping("funcionarios")
@Controller
public class FuncionarioController implements IController<Funcionario> {

	@Autowired
	private CargoRepository cargoRepository;
	@Autowired
	private SetorRepository setorRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	private String descricao = "";
	
	
	@Override
	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("funcionarios/list");
		if (descricao.isEmpty()) {
			modelAndView.addObject("funcionarios", funcionarioRepository.findAll());
		} else {
			modelAndView.addObject("funcionarios", funcionarioRepository.findByNomeContaining(descricao.toUpperCase()));
		}
		return modelAndView;
	}

	@GetMapping("cadastros")
	@Override
	public ModelAndView saveAll() {
		Cidade goiatuba = cidadeRepository.findById(1l).get();
		Endereco end1 = new Endereco("rua amapa", "872", "", "centro", "75600000", goiatuba );
		Funcionario f1 = new Funcionario("batman", "007", Sexo.MASCULINO, "00788744160", "234567", end1 );
		f1.setCargo(cargoRepository.findById(45l).get());
		f1.setSetor(setorRepository.findById(1l).get());
		f1.setCelular("4567890");
		f1.setTelefone("3456789");
		
		Cidade itumbiara = cidadeRepository.findById(3l).get();
		Endereco end2 = new Endereco("rua dos malas", "171", "", "malandragem", "75606000", itumbiara);
		Funcionario f2 = new Funcionario("SUPERMAN", "779", Sexo.MASCULINO, "60577348078", "45678", end2 );
		f2.setCargo(cargoRepository.findById(46l).get());
		f2.setSetor(setorRepository.findById(3l).get());
		f2.setCelular("567890");
		f2.setTelefone("567890");
		funcionarioRepository.saveAll(Arrays.asList(f1, f2));
		ModelAndView mv = new ModelAndView("redirect:/funcionarios");
		return mv;
	}

	@GetMapping("novo")
	@Override
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("funcionarios/form");
		modelAndView.addObject("funcionario", new Funcionario());
		modelAndView.addObject("tituloForm", "Cadastro de Funcionários");
		modelAndView.addObject("setorSelecionado", null);
		modelAndView.addObject("cargoSelecionado", null);
		modelAndView.addObject("cidadeSelecionada", null);
		return modelAndView;		
	}

	@GetMapping("edit/{id}")
	@Override
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("funcionarios/form");
		Funcionario funcionario = funcionarioRepository.findById(id).get();
		modelAndView.addObject("funcionario", funcionario);
		modelAndView.addObject("setorSelecionado", funcionario.getSetor());
		modelAndView.addObject("cargoSelecionado", funcionario.getCargo());
		modelAndView.addObject("cidadeSelecionada", funcionario.getEndereco().getCidade());
		modelAndView.addObject("tituloForm", "Editando Funcionários");
		return modelAndView;		
	}

	@GetMapping("excluir/{id}")
	@Override
	public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes atts) {
		ModelAndView modelAndView = new ModelAndView("redirect:/funcionarios");
		funcionarioRepository.deleteById(id);
		atts.addFlashAttribute("erro", false);
		atts.addFlashAttribute("mensagem", Constantes.MENSAGEM_EXCLUSAO);
		return modelAndView;				
	}

	@PostMapping
	@Override
	public ModelAndView salvar(@Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("/funcionarios/form");
			mv.addObject("tituloForm", "Cadastro de Funcionários");
			return mv;
		}
		ModelAndView mv = new ModelAndView("redirect:/funcionarios");
		funcionarioRepository.save(funcionario);
		attr.addFlashAttribute("erro", false);
		attr.addFlashAttribute("mensagem", Constantes.MENSAGEM_SALVO);
		return mv;
	}
	
	@ModelAttribute("cidades")
	public List<Cidade> getCidades(){
		return cidadeRepository.findAll();
	}
	
	@ModelAttribute("setores")
	public List<Setor> getSetores(){
		return setorRepository.findAll();
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoRepository.findAll();
	}
	
	@ModelAttribute("sexos")
	public List<Sexo> getSexo(){
		return Arrays.asList(Sexo.values());
	}

	@Override
	@PostMapping("filtro")
	public ModelAndView listarPorDescricao(@Nullable @RequestParam("descricao") String descricao) {
		ModelAndView mv = new ModelAndView("funcionarios/list");
		mv.addObject("funcionarios", funcionarioRepository.findByNomeContaining(descricao.toUpperCase()));
		return mv;
	}
	
	@PostMapping("imprimir")
	public void imprimir(@RequestParam Map<String, Object> parametros, HttpServletResponse response) throws JRException, SQLException, IOException {	
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;		
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_funcionarios.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(funcionarioRepository.findAll());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=lista.pdf");
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}

}
