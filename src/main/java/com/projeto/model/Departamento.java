package com.projeto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Departamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "nome", unique = true, length = 60)
	@NotNull(message="O campo nome é requerido!")
	private String nome;
	
	@OneToMany(mappedBy = "departamento")
	List<Cargos> cargos = new ArrayList<>();
	
	
	
	public Departamento() {
		
	}

	public Departamento(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public List<Cargos> getCargos() {
		return cargos;
	}

	public void addCargos(Cargos cargos) {
		this.cargos.add(cargos);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nome=" + nome + "]";
	}
		
}
