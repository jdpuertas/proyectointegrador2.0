package com.proyectointegrador.proyectointegrador.Services;

import com.proyectointegrador.proyectointegrador.Model.Usuario;
import com.proyectointegrador.proyectointegrador.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void crearUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public void editarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public List<Usuario> verUsuario(){
        List<Usuario> usuarios= new ArrayList<Usuario>();
        usuarios.addAll(usuarioRepository.findAll());
        return usuarios;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
