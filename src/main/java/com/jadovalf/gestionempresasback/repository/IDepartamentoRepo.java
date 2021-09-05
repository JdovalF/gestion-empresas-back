package com.jadovalf.gestionempresasback.repository;

import com.jadovalf.gestionempresasback.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentoRepo extends JpaRepository<Departamento, Integer> {
}
