package com.proyectointegrador.proyectointegrador.Repository;

import com.proyectointegrador.proyectointegrador.Model.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientoDineroRepository extends JpaRepository<MovimientoDinero, Integer> {

    @Query(value = "select * from movimiento_dinero where empleado_id= ?1", nativeQuery = true)
    ArrayList<MovimientoDinero> findByEmpleado(Integer id);

    @Query(value = "select * from movimiento_dinero where empleado_id in (select id from empleado where empresa_id= ?1)", nativeQuery = true)
    ArrayList<MovimientoDinero> findByEmpresa(Integer id);

    @Query(value = "SELECT SUM(monto) from movimiento_dinero", nativeQuery = true)
    Long SumarMonto();

    @Query(value = "SELECT SUM(monto) from movimiento_dinero where empleado_id=?1", nativeQuery = true)
    Long MontosPorEmpleado(Integer id);

    @Query(value = "select sum(monto) from movimiento_dinero where empleado_id in (select id from empleado where empresa_id= ?1)", nativeQuery = true)
    Long MontosPorEmpresa(Integer id);
}
