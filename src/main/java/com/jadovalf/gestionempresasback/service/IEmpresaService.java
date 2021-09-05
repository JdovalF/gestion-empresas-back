package com.jadovalf.gestionempresasback.service;

import com.jadovalf.gestionempresasback.api.dto.GenericListDto;
import com.jadovalf.gestionempresasback.api.dto.input.EmpresaInputDto;
import com.jadovalf.gestionempresasback.api.dto.response.EmpresaDto;

public interface IEmpresaService {
    public GenericListDto<EmpresaDto> findAll();
    public GenericListDto<EmpresaDto> findAll(Integer nPAge, Integer nSize);
    public EmpresaDto findById(Integer id);
    public Integer createOrUpdate(EmpresaInputDto inputDto);
    public Integer delete(Integer id);
}
