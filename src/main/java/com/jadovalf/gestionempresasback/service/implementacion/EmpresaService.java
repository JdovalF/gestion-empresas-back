package com.jadovalf.gestionempresasback.service.implementacion;

import com.jadovalf.gestionempresasback.api.dto.GenericListDto;
import com.jadovalf.gestionempresasback.api.dto.response.EmpresaDto;
import com.jadovalf.gestionempresasback.repository.IEmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jadovalf.gestionempresasback.service.IEmpresaService;

@Service
public class EmpresaService implements IEmpresaService {

    @Autowired
    private IEmpresaRepo repo;

    @Override
    public GenericListDto<EmpresaDto> findAll() {
        return null;
    }
}
