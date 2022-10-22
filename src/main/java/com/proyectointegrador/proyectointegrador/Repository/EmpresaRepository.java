package com.proyectointegrador.proyectointegrador.Repository;

import com.proyectointegrador.proyectointegrador.Model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
