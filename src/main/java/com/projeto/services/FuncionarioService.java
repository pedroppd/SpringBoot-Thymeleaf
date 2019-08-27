package com.projeto.services;

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
	
	
}
