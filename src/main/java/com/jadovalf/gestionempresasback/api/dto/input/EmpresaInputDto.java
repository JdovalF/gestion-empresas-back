package com.jadovalf.gestionempresasback.api.dto.input;

import lombok.Value;

import java.util.List;

@Value
public class EmpresaInputDto {

    Integer id;
    String nombre;
    String descripcion;
    Double potenciaContratada;
    List<Integer> codsDepartamentos;
}
