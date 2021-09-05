package com.jadovalf.gestionempresasback.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="EmpleadoDepartamento")
public class EmpleadoDepartamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cargo;

    @ManyToOne
    @JoinColumn(name="codEmpleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name="codDepartamento")
    private Departamento departamento;
}
