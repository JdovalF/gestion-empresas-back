package com.jadovalf.gestionempresasback.api.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;

import java.util.List;

@Data
@JGlobalMap
public class GenericListDto<S> {
    public Long total;
    public List<S> lista;
}
