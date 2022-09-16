package com.procesos.negocio.fabian.controllers;

import com.procesos.negocio.fabian.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UsuarioController {
    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario= new Usuario();
        usuario.setId(id);
        usuario.setNombre("fabian");
        usuario.setApellidos("Rincón Chinchilla");
        usuario.setDocumento("1064836389");
        usuario.setFechaNacimiento(new Date(2004,7,14));
        usuario.setDireccion("kdx-o10-310");
        usuario.setTelefono("3144454761");
        return usuario;
    }
}
