package com.procesos.negocio.fabian.controllers;

import com.procesos.negocio.fabian.models.Usuario;
import com.procesos.negocio.fabian.repository.UsuarioRepository;
import com.procesos.negocio.fabian.services.UsuarioService;
import com.procesos.negocio.fabian.services.UsuarioServiceImpl;
import com.procesos.negocio.fabian.utils.JWTUtil;
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
    @Autowired
    private JWTUtil jwtUtil;
    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id, @RequestHeader(value="Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.getUserById(id);
    }
    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@Valid @RequestBody  Usuario usuario){
        return usuarioService.createUser(usuario);
    }
    @GetMapping(value="/usuarios")
    public ResponseEntity listarUsuarios(@RequestHeader(value="Authorization") String token){
        if(jwtUtil.getKey(token) != null){
            return usuarioService.allUsers();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");

    }

    @GetMapping(value="/usuarios/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellidos(@PathVariable  String nombre, @PathVariable String apellidos, @RequestHeader(value="Authorization") String token ){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.allUsersByNameAndLastName(nombre,apellidos);
    }
    @GetMapping(value="/usuarios/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable  String nombre , @RequestHeader(value="Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.allUsersByName(nombre);
    }
    @GetMapping(value="/usuarios/apellidos/{apellidos}")
    public ResponseEntity listarPorapellidos(@PathVariable  String apellidos , @RequestHeader(value="Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.allUsersByLastName(apellidos);
    }
    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id,@RequestBody @Valid Usuario usuario, @RequestHeader(value="Authorization") String token){
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.editUser(id,usuario);
    }
    @DeleteMapping (value = "/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id, @RequestHeader(value="Authorization") String token) {
        if(jwtUtil.getKey(token) == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
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
