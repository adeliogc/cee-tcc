package br.com.tcc.cee.controllers;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IController<T> {
	
	ModelAndView listar();
	ModelAndView saveAll();
	ModelAndView form();
	ModelAndView edit(@PathVariable("id") Long id);
	ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes atts);
	ModelAndView salvar(@Valid T entity, BindingResult result, RedirectAttributes attr);
	ModelAndView listarPorDescricao(@RequestParam("descricao") String descricao);

}
