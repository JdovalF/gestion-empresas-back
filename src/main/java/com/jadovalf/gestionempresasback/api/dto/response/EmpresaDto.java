package com.jadovalf.gestionempresasback.api.dto.response;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;

import java.util.Set;

@Data
@JGlobalMap
public class EmpresaDto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Double potenciaContratada;
    private Set<Integer> codsDepartamentos;


}
