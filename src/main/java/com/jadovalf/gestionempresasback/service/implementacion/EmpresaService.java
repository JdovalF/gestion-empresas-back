package com.jadovalf.gestionempresasback.service.implementacion;

import com.googlecode.jmapper.JMapper;
import com.jadovalf.gestionempresasback.api.dto.GenericListDto;
import com.jadovalf.gestionempresasback.api.dto.input.EmpresaInputDto;
import com.jadovalf.gestionempresasback.api.dto.response.EmpresaDto;
import com.jadovalf.gestionempresasback.model.Departamento;
import com.jadovalf.gestionempresasback.model.Empresa;
import com.jadovalf.gestionempresasback.repository.IEmpresaRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.jadovalf.gestionempresasback.service.IEmpresaService;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmpresaService implements IEmpresaService {

    @Autowired
    private IEmpresaRepo repo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public GenericListDto<EmpresaDto> findAll() {

        return findAll(0, Integer.MAX_VALUE);
    }

    @Override
    public GenericListDto<EmpresaDto> findAll(Integer nPAge, Integer nSize) {

        log.info("EmpresaService - findAll() - Solicitando lista paginada de empresas");
        Pageable paging = PageRequest.of(nPAge, nSize);
        Page<Empresa> empresasPaging = repo.findAll(paging);

        GenericListDto<EmpresaDto> empresaDtoGenericListDto = new GenericListDto<>();
        empresaDtoGenericListDto.setTotal(empresasPaging.getTotalElements());
        if(!empresasPaging.hasContent()){
            log.info("EmpresaService - findAll() - Devolviendo lista de empresas vacia");
            return empresaDtoGenericListDto;
        }

        empresaDtoGenericListDto.setLista(empresasPaging.getContent().stream().map(this::converToEmpresaDto).collect(Collectors.toList()));
        log.info("EmpresaService - findAll() - Devolviendo lista de empresas");
        return empresaDtoGenericListDto;

    }

    @Override
    public EmpresaDto findById(Integer id) {
        log.info("EmpresaService - findById() - Buscando empresa con id {}", id.toString());
        return converToEmpresaDto(repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Empresa - findById()")));
    }

    @Override
    public Integer createOrUpdate(EmpresaInputDto inputDto) {
        log.info("EmpresaService - createOrUpdate() - Creando o actualizando empresa");
        Empresa empresa = new Empresa();
        if(inputDto.getId() != null){
            empresa = repo.findById(inputDto.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Empresa - findById()"));
        }
        BeanUtils.copyProperties(inputDto, empresa, "codsDepartamentos");

        log.info("EmpresaService - createOrUpdate() - Guardando/Actualizando departamentos");
        empresa.setDepartamentos(inputDto.getCodsDepartamentos().stream().map(
                dep -> entityManager.getReference(Departamento.class, dep)
        ).collect(Collectors.toList()));

        log.info("EmpresaService - createOrUpdate() - Salvando empresa en Base de datos");
        return repo.save(empresa).getId();
    }

    @Override
    public Integer delete(Integer id) {
        log.info("EmpresaService - delete() - Buscando empresa con id {}", id.toString());
        Empresa empresa = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Empresa - findById()"));

        Integer empresaId = empresa.getId();
        log.info("EmpresaService - delete() -  Borrando empresa con id {}", empresaId);
        repo.delete(empresa);
        return empresaId;
    }

    private EmpresaDto converToEmpresaDto(Empresa entity){
        JMapper<EmpresaDto, Empresa> mapper = new JMapper<>
                (EmpresaDto.class, Empresa.class);

        return mapper.getDestination(entity);
    }
}
