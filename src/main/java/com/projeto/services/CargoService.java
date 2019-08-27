package com.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.model.Cargos;
import com.projeto.repository.CargoRepository;


@Service
public class CargoService {

	@Autowired
	private CargoRepository repo;

	public Cargos save(Cargos obj) {
		if (obj == null) {
			throw new RuntimeException("Erro ao inserir.");
		}
		return repo.save(obj);
	}

	public void delete(Integer id, RedirectAttributes attr) {
		if(findByid(id).getFuncionarios().isEmpty()) {
		   repo.deleteById(id);
		   attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
		}else {
			attr.addFlashAttribute("fail", "Erro ao deletar.");
		}
	}
	
	public Cargos findByid(Integer id) {
		Optional<Cargos> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public List<Cargos> findAll() {
		List<Cargos> obj = repo.findAll();
		return obj;
	}
	
	public Cargos update(Cargos obj) {
		Cargos newObj = new Cargos();
		newObj.setId(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void updateData(Cargos newObj, Cargos obj) {
		newObj.setDepartamento(obj.getDepartamento());
		newObj.setNome(obj.getNome());
	}
	
	
}
