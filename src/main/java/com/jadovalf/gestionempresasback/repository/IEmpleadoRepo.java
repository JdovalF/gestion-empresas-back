package com.jadovalf.gestionempresasback.repository;

import com.jadovalf.gestionempresasback.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoRepo extends JpaRepository<Empleado, Integer> {
}
