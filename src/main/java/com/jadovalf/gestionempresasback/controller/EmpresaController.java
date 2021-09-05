package com.jadovalf.gestionempresasback.controller;

import com.jadovalf.gestionempresasback.api.dto.GenericListDto;
import com.jadovalf.gestionempresasback.api.dto.input.EmpresaInputDto;
import com.jadovalf.gestionempresasback.api.dto.response.EmpresaDto;
import com.jadovalf.gestionempresasback.service.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.xml.ws.Response;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private IEmpresaService service;

    //TODO all/paging
    @GetMapping("/all")
    public ResponseEntity<GenericListDto<EmpresaDto>> findAll(
            @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size
    ){

        try{
            GenericListDto<EmpresaDto> empresas = (page.isPresent() && size.isPresent())
                    ? service.findAll(page.get(), size.get())
                    : service.findAll();
            return ResponseEntity.ok(empresas);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> findById(@PathVariable Integer id){

        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    //TODO addOrUpdate
    @PostMapping
    public ResponseEntity<Integer> createOrUpdate(@RequestBody EmpresaInputDto inputDto){

        try {
            return ResponseEntity.ok(service.createOrUpdate(inputDto));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    //TODO delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id){

        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }


}
