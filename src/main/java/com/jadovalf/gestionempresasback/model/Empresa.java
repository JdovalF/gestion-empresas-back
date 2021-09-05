package com.jadovalf.gestionempresasback.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="EmpresaDepartamento",
            joinColumns = @JoinColumn(name="codEmpresa"),
            inverseJoinColumns = @JoinColumn(name="codDepartamento")
    )
    private Set<Departamento> departamentos;

}
