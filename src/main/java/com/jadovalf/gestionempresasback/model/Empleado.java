package com.jadovalf.gestionempresasback.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name="codEmpresa")
    private Empresa empresa;

    @OneToMany (mappedBy = "empleado")
    private Set<EmpleadoDepartamento> empleadoDepartamentos;
}
