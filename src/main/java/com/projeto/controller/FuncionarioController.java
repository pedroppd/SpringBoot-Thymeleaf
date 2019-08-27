package com.projeto.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.model.Cargos;
import com.projeto.model.Funcionario;
import com.projeto.model.enums.UF.UF;
import com.projeto.services.CargoService;
import com.projeto.services.FuncionarioService;

@Controller
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@Autowired
	private CargoService cargoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}

	@GetMapping("/listar")
	public String ListAll(ModelMap map) {
		map.addAttribute("funcionarios", service.findAll());
		return "/funcionario/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		service.save(funcionario, attr);
		attr.addFlashAttribute("success", "Funcionário salvo com sucesso!");
		return "redirect:/funcionarios/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Integer id, ModelMap model) {
		model.addAttribute("funcionario", service.findByid(id));
		return "/funcionario/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		service.save(funcionario, attr);
		attr.addFlashAttribute("success", "Funcionario editado com sucesso!!");
		return "redirect:/funcionarios/listar";
	}

	@GetMapping("/excluir/{id}")
	public String deletar(@PathVariable Integer id, RedirectAttributes attr) {
		service.delete(id);
		attr.addFlashAttribute("success", "Funcionario excluído com sucesso!!");
		return "redirect:/funcionarios/listar";
	}

	@GetMapping("/buscar/nome")
	public String findByNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", service.findByNome(nome));
		return "/funcionario/lista";
	}
	
	
	@GetMapping("/buscar/cargo")
	public String findByCargo(@RequestParam("id") Integer id, ModelMap model) {
		model.addAttribute("funcionarios", service.findByCargo(id));
		return "/funcionario/lista";
	}
	
	@GetMapping("/buscar/data")
	public String findByDate(@RequestParam("entrada") @DateTimeFormat(iso = ISO.DATE) LocalDate entrada, 
			@RequestParam("saida") @DateTimeFormat(iso = ISO.DATE) LocalDate saida, 
			ModelMap model) {
		
		model.addAttribute("funcionarios", service.findByDate(entrada, saida));
		return "/funcionario/lista";
	}

	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}

	@ModelAttribute("cargos")
	public List<Cargos> getCargos() {
		return cargoService.findAll();
	}

}
