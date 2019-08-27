package com.projeto.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.model.Funcionario;
import com.projeto.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repo;

	public Funcionario save(Funcionario obj, RedirectAttributes attr) {
		if (obj == null) {
			attr.addFlashAttribute("fail", "Ocorreu um erro ao salvar.");
		}
	        return repo.save(obj);
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public Funcionario findByid(Integer id) {
		Optional<Funcionario> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public List<Funcionario> findAll() {
		List<Funcionario> obj = repo.findAll();
		return obj;
	}
	
	public List<Funcionario> findByNome(String nome){
		List<Funcionario> list = repo.findByNomeContainingIgnoreCase(nome);
		return list;
	}
	
	public List<Funcionario> findByCargo(Integer id){
		List<Funcionario> list = repo.findByCargoId(id);
		return list;
	}
	
	public Funcionario update(Funcionario obj) {
		Funcionario newObj = new Funcionario();
		newObj.setId(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setCargo(obj.getCargo());
		newObj.setDataEntrada(obj.getDataEntrada());
		newObj.setDataSaida(obj.getDataSaida());
		newObj.setEndereco(obj.getEndereco());
		newObj.setNome(obj.getNome());
		newObj.setSalario(obj.getSalario());		
	}

	public List<Funcionario> findByDate(LocalDate entrada, LocalDate saida) {
		if(entrada != null && saida!= null) {
			repo.findByDataEntradaAndDataSaida(entrada, saida);
		}
		else if(entrada != null) {
			repo.findByDataEntrada(entrada);
		}
		else if(saida!= null) {
			repo.findByDataSaida(saida);
		}
		else {
			return new ArrayList<>();
		}
		return null;
		
	}
	
	
}
