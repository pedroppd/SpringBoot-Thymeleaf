package com.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.model.Departamento;
import com.projeto.repository.DepartamentoRepository;


@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repo;

	public Departamento save(Departamento obj) {
		if (obj == null) {
			throw new RuntimeException("Erro ao inserir.");
		}
		return repo.save(obj);
	}
	
	public Departamento findByid(Integer id) {
		Optional<Departamento> obj = repo.findById(id);
		return obj.orElse(null);
	}

	
	public void delete(Integer id, 	RedirectAttributes attr) {
		if(findByid(id).getCargos().isEmpty()) {
			repo.deleteById(id);
			attr.addFlashAttribute("success", "Departamento excluído com sucesso");
		}else {
			attr.addFlashAttribute("fail", "Não foi possível excluir, pois, possuí cargos vínculados.");
		}
	}
	
	public List<Departamento> findAll() {
		List<Departamento> obj = repo.findAll();
		return obj;
	}
	
	public Departamento update(Departamento obj) {
		Departamento newObj = new Departamento();
		newObj.setId(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void updateData(Departamento newObj, Departamento obj) {
		newObj.setNome(obj.getNome());
	}

	
	
}
