package com.proyectointegrador.proyectointegrador.Repository;
import com.proyectointegrador.proyectointegrador.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
