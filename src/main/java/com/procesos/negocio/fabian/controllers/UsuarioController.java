package com.procesos.negocio.fabian.controllers;

import com.procesos.negocio.fabian.models.Usuario;
import com.procesos.negocio.fabian.repository.UsuarioRepository;
import com.procesos.negocio.fabian.services.UsuarioService;
import com.procesos.negocio.fabian.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class UsuarioController {

    @Autowired
   private UsuarioService usuarioService;
    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        return usuarioService.getUserById(id);

       /* Usuario usuario= new Usuario();
        usuario.setId(id);
        usuario.setNombre("fabian");
        usuario.setApellidos("Rincón Chinchilla");
        usuario.setDocumento("1064836389");
        usuario.setFechaNacimiento(new Date(2004,7,14));
        usuario.setDireccion("kdx-o10-310");
        usuario.setTelefono("3144454761");*/

    }
    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@Valid @RequestBody  Usuario usuario){
        return usuarioService.createUser(usuario);
    }
    @GetMapping(value="/usuarios")
    public ResponseEntity listarUsuarios(){
        return usuarioService.allUsers();
    }

    @GetMapping(value="/usuarios/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellidos(@PathVariable  String nombre, @PathVariable String apellidos ){
        return usuarioService.allUsersByNameAndLastName(nombre,apellidos);
    }
    @GetMapping(value="/usuarios/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable  String nombre ){
        return usuarioService.allUsersByName(nombre);
    }
    @GetMapping(value="/usuarios/apellidos/{apellidos}")
    public ResponseEntity listarPorapellidos(@PathVariable  String apellidos ){
        return usuarioService.allUsersByLastName(apellidos);
    }
    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id,@RequestBody @Valid Usuario usuario){
        return usuarioService.editUser(id,usuario);
    }
    @DeleteMapping (value = "/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        return usuarioService.deleteUser(id);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
