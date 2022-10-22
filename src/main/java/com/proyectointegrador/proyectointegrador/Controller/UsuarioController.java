package com.proyectointegrador.proyectointegrador.Controller;

import com.proyectointegrador.proyectointegrador.Model.Usuario;
import com.proyectointegrador.proyectointegrador.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuario")
    private List<Usuario> verUsuario() {
        return usuarioService.verUsuario();
    }

    @PostMapping("/usuario")
    private void crearUsuario(@RequestBody Usuario usuario){
        usuarioService.crearUsuario(usuario);
    }

    @PutMapping("/editarUsuario")
    private void editarUsuario(@RequestBody Usuario usuario){
        usuarioService.editarUsuario(usuario);
    }

    @DeleteMapping("/usuario/{id}")
    private void eliminarUsuario(@PathVariable("id") Long id){
        usuarioService.eliminarUsuario(id);
    }
}
