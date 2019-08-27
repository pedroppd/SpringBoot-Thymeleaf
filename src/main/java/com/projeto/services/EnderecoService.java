package com.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.model.Endereco;
import com.projeto.repository.EnderecoRepository;


@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;

	public Endereco save(Endereco obj) {
		if (obj == null) {
			throw new RuntimeException("Erro ao inserir.");
		}
		return repo.save(obj);
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public Endereco findByid(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public List<Endereco> findAll() {
		List<Endereco> obj = repo.findAll();
		return obj;
	}
	
	public Endereco update(Endereco obj) {
		Endereco newObj = new Endereco();
		newObj.setId(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void updateData(Endereco newObj, Endereco obj) {
		newObj.setBairro(obj.getBairro());
		newObj.setCep(obj.getCep());
		newObj.setCidade(obj.getCidade());
		newObj.setComplemento(obj.getComplemento());
		newObj.setLogradouro(obj.getLogradouro());
		newObj.setNumero(obj.getNumero());
		newObj.setUf(obj.getUf());
	}
	
	
}
