package com.jadovalf.gestionempresasback.service;

import com.jadovalf.gestionempresasback.api.dto.GenericListDto;
import com.jadovalf.gestionempresasback.api.dto.response.EmpresaDto;

public interface IEmpresaService {
    public GenericListDto<EmpresaDto> findAll();
}
