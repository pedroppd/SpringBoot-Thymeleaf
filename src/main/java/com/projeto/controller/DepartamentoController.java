package com.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.model.Departamento;
import com.projeto.services.DepartamentoService;

@Controller
@RequestMapping(value="/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento){
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model){
		model.addAttribute("departamentos", service.findAll());
		return "/departamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Departamento obj, RedirectAttributes attr) {
		service.save(obj);
		attr.addFlashAttribute("success", "Departamento editado com sucesso!");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Integer id,RedirectAttributes attr) {
		service.delete(id, attr);
		return "redirect:/departamentos/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("departamento", service.findByid(id));
		return "/departamento/cadastro";
	}
	
	
	@PostMapping("/editar")
	public String editar(Departamento departamento, RedirectAttributes attr) {
		service.update(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso!");
		return "redirect:/departamentos/cadastrar";
	}
	
	
	
	
	
	
}
