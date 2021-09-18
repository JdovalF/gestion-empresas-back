package com.jadovalf.gestionempresasback.controller;

import com.jadovalf.gestionempresasback.api.dto.GenericListDto;
import com.jadovalf.gestionempresasback.api.dto.input.EmpresaInputDto;
import com.jadovalf.gestionempresasback.api.dto.response.EmpresaDto;
import com.jadovalf.gestionempresasback.service.IEmpresaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private IEmpresaService service;

    @GetMapping("/all")
    public ResponseEntity<GenericListDto<EmpresaDto>> findAll(
            @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size
    ){
        log.info("EmpresaController - findAll() - Solicitando Lista de empresas");
        try{
            GenericListDto<EmpresaDto> empresas = (page.isPresent() && size.isPresent())
                    ? service.findAll(page.get(), size.get())
                    : service.findAll();
            log.info("EmpresaController - findAll() - Devolviendo Lista de empresas");
            return ResponseEntity.ok(empresas);
        } catch (EntityNotFoundException e){
            log.warn("EmpresaController - findAll() - No se han encontrado empresas - EntityNotFoudException");
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            log.warn("EmpresaController - findAll() - Argumentos invalidos - badRequest" );
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> findById(@PathVariable Integer id){
        log.info("EmpresaController - findById() - Solicitando empresa con id {}", id);
        try {
            EmpresaDto empresaDto = service.findById(id);
            log.info("EmpresaController - findById() - Devolviendo empresa con id {}", id);
            return ResponseEntity.ok(empresaDto);
        } catch (EntityNotFoundException e){
            log.warn("EmpresaController - findById() - No se ha encontrado empresa con id {}", id);
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            log.warn("EmpresaController - findById() - Argumentos invalidos - badRequest" );
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<Integer> createOrUpdate(@RequestBody EmpresaInputDto inputDto){
        log.info("EmpresaController - createOrUpdate() - Solicitando creacion o edicion de empresa");
        try {
            Integer empresaId = service.createOrUpdate(inputDto);
            log.info("EmpresaController - createOrUpdate() - Devolviendo empresa creada o actualizada");
            return ResponseEntity.ok(empresaId);
        } catch (EntityNotFoundException e){
            log.warn("EmpresaController - createOrUpdate() - No se ha encontrado la empresa a actualizar");
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            log.warn("EmpresaController - createOrUpdate() - Argumentos invalidos - badRequest" );
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id){
        log.info("EmpresaController - delete() - Solicitando el Borrado en base de datos de la empresa con id {}", id);
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (EntityNotFoundException e){
            log.warn("EmpresaController - delete() - No se ha encontrado la empresa a eliminar");
            return ResponseEntity.notFound().build();
        }
    }


}
