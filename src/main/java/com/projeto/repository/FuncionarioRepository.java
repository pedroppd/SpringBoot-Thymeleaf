package com.projeto.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projeto.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

	List<Funcionario> findByNomeContainingIgnoreCase(String nome);
	
	List<Funcionario> findByCargoId(Integer id);

	@Query("select f from Funcionario f where f.dataEntrada >= ?1 and f.dataSaida <= ?2 order by f.dataEntrada asc")
	List<Funcionario> findByDataEntradaAndDataSaida(LocalDate DataEntrada, LocalDate DataSaida);
	
	@Query("select f from Funcionario f where f.dataEntrada = ?1 order by f.dataEntrada asc")
	List<Funcionario> findByDataEntrada(LocalDate DataEntrada);

	@Query("select f from Funcionario f where f.dataSaida = ?1 order by f.dataEntrada asc")
	List<Funcionario> findByDataSaida(LocalDate DataSaida);
}
