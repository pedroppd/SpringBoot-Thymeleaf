package com.projeto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.model.Cargos;
import com.projeto.model.Departamento;
import com.projeto.services.CargoService;
import com.projeto.services.DepartamentoService;

@Controller
@RequestMapping(value="/cargos")
public class CargoController {
	
	@Autowired
	private CargoService service;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Cargos cargo){
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model){
		model.addAttribute("cargos", service.findAll());
		return "/cargo/lista";
	}
	
	@PostMapping("/salvar")
	public String save(@Valid Cargos cargos, BindingResult br, RedirectAttributes attr) {
		if(br.hasErrors()) {
			return "/cargo/cadastro";
		}
		if(cargos == null) {
			attr.addFlashAttribute("fail", "Campo obrigatório");
		}
		service.save(cargos);
		attr.addFlashAttribute("success", "Cadastro efetuado com sucesso!!");
		return "redirect:/cargos/cadastrar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listarTodos(){
		return departamentoService.findAll();
	}
	
	@GetMapping("/excluir/{id}")
	public String deletar(@PathVariable Integer id, RedirectAttributes attr) {
		service.delete(id, attr);
		return "redirect:/cargos/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("cargos", service.findByid(id));
		return "cargo/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Cargos cargos, BindingResult br, RedirectAttributes attr) {
		if(br.hasErrors()) {
			return "/cargo/cadastro";
		}
		service.update(cargos);
		attr.addFlashAttribute("success", "Cargo editado com sucesso!!");
		return "redirect:/cargos/listar";
	}
}
