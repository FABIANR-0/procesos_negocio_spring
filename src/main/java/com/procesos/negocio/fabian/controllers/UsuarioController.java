package com.procesos.negocio.fabian.controllers;

import com.procesos.negocio.fabian.models.Usuario;
import com.procesos.negocio.fabian.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
      Optional<Usuario> usuario = usuarioRepository.findById(id);
      if(usuario.isPresent())
      {
          return new ResponseEntity(usuario, HttpStatus.OK);
      }
      return  ResponseEntity.notFound().build();

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
    public ResponseEntity crearUsuario(@RequestBody Usuario usuario){
        try {
            usuarioRepository.save(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(value="/usuarios")
    public ResponseEntity listarUsuarios(){
    List<Usuario> usuarios =usuarioRepository.findAll();
    if(usuarios.isEmpty()){
        return ResponseEntity.notFound().build();
    }
    return  new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @GetMapping(value="/usuarios/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellidos(@PathVariable  String nombre, @PathVariable String apellidos ){
        List<Usuario> usuarios= usuarioRepository.findAllByNombreAndApellidos(nombre,apellidos);;
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity(usuarios, HttpStatus.OK);
    }
    @GetMapping(value="/usuarios/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable  String nombre ){
        List<Usuario> usuarios= usuarioRepository.findAllByNombre(nombre);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity(usuarios, HttpStatus.OK);
    }
    @GetMapping(value="/usuarios/apellidos/{apellidos}")
    public ResponseEntity listarPorapellidos(@PathVariable  String apellidos ){
        List<Usuario> usuarios= usuarioRepository.findAllByApellidos(apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity(usuarios, HttpStatus.OK);
    }
    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario){
            Optional<Usuario> usuarioBD =usuarioRepository.findById(id);
        if(usuarioBD.isPresent()){
            try{
                usuarioBD.get().setNombre(usuario.getNombre());
                usuarioBD.get().setApellidos(usuario.getApellidos());
                usuarioBD.get().setDireccion(usuario.getDireccion());
                usuarioBD.get().setDocumento(usuario.getDocumento());
                usuarioBD.get().setTelefono(usuario.getTelefono());
                usuarioBD.get().setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioRepository.save(usuarioBD.get());
                return new ResponseEntity(usuarioBD,HttpStatus.OK);
            }catch (Exception e){
                return  ResponseEntity.badRequest().build();

            }
        }
        return  ResponseEntity.notFound().build();


    }
    @DeleteMapping (value = "/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioBD =usuarioRepository.findById(id);
        if(usuarioBD.isPresent()){
            usuarioRepository.delete(usuarioBD.get());
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }
}
