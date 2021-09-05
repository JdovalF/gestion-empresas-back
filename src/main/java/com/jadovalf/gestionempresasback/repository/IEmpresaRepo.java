package com.jadovalf.gestionempresasback.repository;

import com.jadovalf.gestionempresasback.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaRepo extends JpaRepository<Empresa, Integer> {
}
