package com.procesos.negocio.fabian.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false, length = 100)
    private String nombre;
    @Column( nullable = false, length = 300)
    private String apellidos;
    @Column( nullable = false, length = 20, unique = true)
    private String documento;
    @Column( length = 100)
    private String direccion;
    private Date fechaNacimiento;
    @Column( length = 20)
    private String telefono;
}
