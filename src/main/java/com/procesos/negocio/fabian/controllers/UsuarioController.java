package com.procesos.negocio.fabian.controllers;

import com.procesos.negocio.fabian.models.Usuario;
import com.procesos.negocio.fabian.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
      Optional<Usuario> usuario = usuarioRepository.findById(id);

       /* Usuario usuario= new Usuario();
        usuario.setId(id);
        usuario.setNombre("fabian");
        usuario.setApellidos("Rincón Chinchilla");
        usuario.setDocumento("1064836389");
        usuario.setFechaNacimiento(new Date(2004,7,14));
        usuario.setDireccion("kdx-o10-310");
        usuario.setTelefono("3144454761");*/
        return usuario;
    }
    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return  usuario;
    }
    @GetMapping(value="/usuarios")
    public List<Usuario> listarUsuarios(){
    return  usuarioRepository.findAll();
    }

    @GetMapping(value="/usuarios/{nombre}/{apellidos}")
    public List<Usuario> listarPorNombreApellidos(@PathVariable  String nombre, @PathVariable String apellidos ){
        return usuarioRepository.findAllByNombreAndApellidos(nombre,apellidos);
    }
    @GetMapping(value="/usuarios/nombre/{nombre}")
    public List<Usuario> listarPorNombre(@PathVariable  String nombre ){
        return usuarioRepository.findAllByNombre(nombre);
    }
    @GetMapping(value="/usuarios/apellidos/{apellidos}")
    public List<Usuario> listarPorapellidos(@PathVariable  String apellidos ){
        return usuarioRepository.findAllByApellidos(apellidos);
    }
    @PutMapping(value = "/usuario/{id}")
    public Usuario editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario){
        Usuario usuarioBD =usuarioRepository.findById(id).get();
        try{
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellidos(usuario.getApellidos());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioRepository.save(usuarioBD);
            return usuarioBD;
        }catch (Exception e){
            return null;

        }
    }
    @DeleteMapping (value = "/usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id){
        Usuario usuarioBD =usuarioRepository.findById(id).get();
        try{
          usuarioRepository.delete(usuarioBD);
          return usuarioBD;
        }catch (Exception e){
            return null;
        }
    }
}
