package com.procesos.negocio.fabian.services;

import com.procesos.negocio.fabian.models.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {
    ResponseEntity<Usuario> getUserById(Long id);
    ResponseEntity<Usuario> createUser(Usuario usuario);
    ResponseEntity<List<Usuario>> allUsers();
    ResponseEntity<List<Usuario>> allUsersByNameAndLastName(String nombre,String apellidos);
    ResponseEntity<List<Usuario>> allUsersByName(String nombre);
    ResponseEntity<List<Usuario>> allUsersByLastName(String apellidos);
    ResponseEntity<Usuario> editUser(Long id, Usuario usuario);
    ResponseEntity<Usuario> deleteUser(Long id);

    ResponseEntity login(String correo,String password);
}
