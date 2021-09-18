package com.jadovalf.gestionempresasback.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name="Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double potenciaContratada;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="EmpresaDepartamento",
            joinColumns = @JoinColumn(name="codEmpresa"),
            inverseJoinColumns = @JoinColumn(name="codDepartamento")
    )
    private List<Departamento> departamentos = new ArrayList<>();

    @Transient
    private List<Integer> codsDepartamentos;

    public List<Integer> getCodsDepartamentos(){
        return departamentos != null
                ? departamentos.stream().map(Departamento::getId).collect(Collectors.toList())
                : new ArrayList<>();
    }

    public void setDepartamentos(List<Departamento> newDeps){
        if(newDeps == null){
            departamentos = new ArrayList<>();
        } else {
            departamentos.clear();
            departamentos.addAll(newDeps);
        }
    }

}
