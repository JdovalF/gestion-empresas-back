package com.jadovalf.gestionempresasback.service.implementacion;

import com.googlecode.jmapper.JMapper;
import com.jadovalf.gestionempresasback.api.dto.GenericListDto;
import com.jadovalf.gestionempresasback.api.dto.input.EmpresaInputDto;
import com.jadovalf.gestionempresasback.api.dto.response.EmpresaDto;
import com.jadovalf.gestionempresasback.model.Empresa;
import com.jadovalf.gestionempresasback.repository.IEmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jadovalf.gestionempresasback.service.IEmpresaService;

import javax.persistence.EntityNotFoundException;

@Service
public class EmpresaService implements IEmpresaService {

    @Autowired
    private IEmpresaRepo repo;

    @Override
    public GenericListDto<EmpresaDto> findAll() {

        return findAll(0, Integer.MAX_VALUE);
    }

    @Override
    public GenericListDto<EmpresaDto> findAll(Integer nPAge, Integer nSize) {
        return null;
    }

    @Override
    public EmpresaDto findById(Integer id) {
        return converToEmpresaDto(repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Empresa - findById()")));
    }

    @Override
    public Integer createOrUpdate(EmpresaInputDto inputDto) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    private EmpresaDto converToEmpresaDto(Empresa entity){
        JMapper<EmpresaDto, Empresa> mapper = new JMapper<>
                (EmpresaDto.class, Empresa.class);

        return mapper.getDestination(entity);
    }
}
