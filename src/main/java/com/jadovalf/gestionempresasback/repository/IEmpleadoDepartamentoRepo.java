package com.jadovalf.gestionempresasback.repository;

import com.jadovalf.gestionempresasback.model.EmpleadoDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoDepartamentoRepo extends JpaRepository<EmpleadoDepartamento, Integer> {
}
