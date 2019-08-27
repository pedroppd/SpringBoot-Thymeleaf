package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.model.Cargos;

@Repository
public interface CargoRepository extends JpaRepository<Cargos, Integer>{

}
