package com.projeto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Cargos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	@NotNull(message = "Campo requerido")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_departamento")
    Departamento departamento;
	
	@OneToMany(mappedBy="cargo")
	List<Funcionario> funcionarios = new ArrayList<>();
	
	public Cargos() {
		
	}

	public Cargos(Integer id, String nome, Departamento departamento) {
		this.id = id;
		this.nome = nome;
		this.departamento = departamento;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void addFuncionarios(Funcionario funcionarios) {
		this.funcionarios.add(funcionarios);
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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
		Cargos other = (Cargos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cargos [id=" + id + ", nome=" + nome + "]";
	}
	
	
	
	

	
}
