package com.proyectointegrador.proyectointegrador.Repository;

import com.proyectointegrador.proyectointegrador.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
