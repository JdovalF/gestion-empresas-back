package com.jadovalf.gestionempresasback.api.dto.response;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;

@Data
@JGlobalMap
public class EmpresaDto {

    private Integer id;
    private String nombre;
    private String descripcion;

}
